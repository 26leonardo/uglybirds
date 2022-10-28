package logic;

import java.awt.Point;
import java.awt.Rectangle;
import java.lang.Math;
import java.lang.Thread;
import java.util.LinkedList;
import javax.swing.SwingWorker;

import view.IView;

public class Logic implements ILogic{
    private static Logic instance=null;
    
    private static boolean BIRD_IS_FLYING = false;
    private static boolean IS_EXPLODING = false;
    private static final int GRAVITY_FALLING_RATE = 1;
    private static final double MAX_INITIAL_VELOCITY = 1.75; 

    private Level1 level1;
    private Level2 level2;
    private Level3 level3;
    private Level4 level4;
    private Level5 level5;
    private StartSetting startSetting;
    private Rectangle birdRect;
    private Rectangle[] pigRect;
    private Rectangle[] blockRect;
    private Point birdPoint;
    private Point explosionPoint1;
    private Point explosionPoint2;
    private Point fallingPoint;
    private LinkedList<Rectangle> fallingRect;
    private int countAliveBirds;
    private int countAlivePigs;
    private int flightCounter=0;
    private int score=0; 
    private boolean gameEnded=false;
    private boolean win=false;
    private SwingWorker<Void,Void> workero;

    private Logic(){
        level1 = new Level1();
        level2 = new Level2();
        level3 = new Level3();
        level4 = new Level4();
        level5 = new Level5();
        startSetting = new StartSetting();

        birdRect = new Rectangle(Level.BIRD_ON_CATAPULT.x, Level.BIRD_ON_CATAPULT.y, IView.BIRD_SIZE, IView.BIRD_SIZE);
        birdPoint = new Point(birdRect.x,birdRect.y);
        explosionPoint1 = new Point(0,0);
        explosionPoint2 = new Point(0,0);
        fallingPoint= new Point(0,0);
        fallingRect = new LinkedList<>();

        pigRect = new Rectangle[4]; //4 is n max of pigs
        for(int i=0; i<pigRect.length; i++){
            pigRect[i] = new Rectangle(); 
            pigRect[i].x=0;
            pigRect[i].y=0;
            pigRect[i].width=0;
            pigRect[i].height=0;
        }
        
        blockRect = new Rectangle[9]; //9 is n max of blocks
        for(int i=0; i<blockRect.length; i++){
            blockRect[i] = new Rectangle(); 
            blockRect[i].x=0;
            blockRect[i].y=0;
            blockRect[i].width=0;
            blockRect[i].height=0;
        }

        workero=new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() {
                while(true){
                    IView.getIView().updateView();
                    updateGame();
                    if(gameEnded() && (!IS_EXPLODING)){       
                            IView.getIView().flip("END_MENU");
                    }
                    try {
                        Thread.sleep(REFRESH_TIME);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    
    }

    @Override
    public void startGame(){
        workero.execute();
    }

    @Override
    public int[] calculateTrajectoryX(){       
        double xDiscrete = IView.CATAPULT_CENTER_X;
        double[] trajectoryDouble = new double[IView.NUMBER_OF_SAMPLES];  //use double to avoid error in approximating

        for(int i=0; i<trajectoryDouble.length; i++){
            trajectoryDouble[i] = xDiscrete;
            xDiscrete = xDiscrete + ((IView.WIDTH*1.0) - (IView.CATAPULT_CENTER_X*1.0))/((IView.NUMBER_OF_SAMPLES*1.0)-1);   
        }
        int[] trajectory=new int[trajectoryDouble.length];
        for(int i=0;i<trajectory.length;i++){
            trajectory[i]=(int)Math.round(trajectoryDouble[i]);     //gives an int
        }
        return trajectory;
    }

    @Override
    public int[] calculateTrajectoryY(int x, int y){       
        int lenghtX = IView.CATAPULT_CENTER_X - x;        
        int lenghtY = y - IView.CATAPULT_CENTER_Y;      
        double percentageVelocity = ( Math.sqrt(Math.pow(lenghtX, 2) + Math.pow(lenghtY, 2))*100
            /(Math.sqrt(Math.pow(IView.CATAPULT_CENTER_X, 2)+ Math.pow(IView.HEIGHT-IView.CATAPULT_CENTER_Y, 2)))); //percentuale di lunghezza del tiro rispetto a lunghezza massima possibile (usando pitagora)
        double initialVelocity = percentageVelocity*MAX_INITIAL_VELOCITY;
        double angle = Math.atan2(lenghtY, lenghtX);
        double iniVelX = Math.cos(angle)*initialVelocity;
        double iniVelY = -Math.sin(angle)*initialVelocity;
        int[] trajectoryX=calculateTrajectoryX();
        int[] trajectory = new int[IView.NUMBER_OF_SAMPLES];

        for(int i=0; i<trajectory.length; i++){
            trajectory[i]=(int) (+0.5*9.81*Math.pow((trajectoryX[i]-IView.CATAPULT_CENTER_X)/iniVelX, 2) + iniVelY*(trajectoryX[i]-IView.CATAPULT_CENTER_X)/iniVelX + IView.CATAPULT_CENTER_Y);   //formula traiettoria moto parabolico
        }
        return trajectory;
    }

    @Override
    public void updateRect(){
        countAliveBirds=getNumberOfBirds(getLevel());
        countAlivePigs=getNumberOfPigs(getLevel());
        
        for(int i=0; i<this.getNumberOfPigs(this.getLevel()); i++){
            pigRect[i].x = this.getPigsPositions(this.getLevel())[i].x;
            pigRect[i].y = this.getPigsPositions(this.getLevel())[i].y;
            pigRect[i].width = IView.PIG_SIZE;
            pigRect[i].height = IView.PIG_SIZE;
        }

        for(int i=0; i<this.getNumberOfBlocks(this.getLevel()); i++){      
            blockRect[i].x = this.getBlocksPositions(this.getLevel())[i].x;
            blockRect[i].y = this.getBlocksPositions(this.getLevel())[i].y;
            blockRect[i].width = IView.BLOCK_SIZE;
            blockRect[i].height = IView.BLOCK_SIZE;
        }
    }

    @Override
    public Rectangle[] getPigsRect(){
        return pigRect;
    }

    @Override
    public Rectangle[] getBlocksRect(){
        return blockRect;
    }

    @Override
    public void setBirdIsFlying(boolean i){  
        BIRD_IS_FLYING = i;
    }

    @Override
    public boolean getIsFlying(){
        return BIRD_IS_FLYING;
    }

    @Override
    public void setIsExploding(boolean i){
        IS_EXPLODING = i;
    }

    @Override
    public boolean getIsExploding(){
        return IS_EXPLODING;
    }

    @Override
    public void updateScore(int points){
        score += points;
    }

    @Override
    public int getScore(){
        return score;
    }

    @Override
    public void updateGame(){  
        if(BIRD_IS_FLYING){
            birdRect.x = this.calculateTrajectoryX()[flightCounter]-IView.BIRD_SIZE/2;
            birdRect.y = this.calculateTrajectoryY(IView.getIView().getXReleased(), IView.getIView().getYReleased())[flightCounter]-IView.BIRD_SIZE/2; 
            for(int j=0; j<pigRect.length; j++){        //check if bird intersect pig or blocks
                if(birdRect.intersects(pigRect[j])){
                    IS_EXPLODING=true;
                    explosionPoint1.x = pigRect[j].x;    
                    explosionPoint1.y = pigRect[j].y;    
                    pigRect[j].x=0;   
                    pigRect[j].y=0; 
                    updateScore(300);
                    if(countAlivePigs>0)
                        countAlivePigs=countAlivePigs-1;  
                }
            }
            for(int j=0; j<blockRect.length; j++){
                if(birdRect.intersects(blockRect[j])){
                    IS_EXPLODING=true;
                    if(explosionPoint1.x==0){
                        explosionPoint1.x = blockRect[j].x;    
                        explosionPoint1.y = blockRect[j].y;    
                    }else{
                        explosionPoint2.x = blockRect[j].x;    
                        explosionPoint2.y = blockRect[j].y;    
                    }
                    blockRect[j].x=0;
                    blockRect[j].y=0;
                    updateScore(150);
                }
            }

            for(int j=0; j<pigRect.length; j++){    //check if there's need of gravity, it adds to a list which objects have to fall
                fallingPoint.x=pigRect[j].x;
                fallingPoint.y=pigRect[j].y;
                if(isOver(fallingPoint, explosionPoint1)){
                    fallingRect.add(pigRect[j]);
                    updateScore(200);   
                    countAlivePigs= countAlivePigs-1;
                }
            }
            for(int j=0; j<blockRect.length; j++){
                fallingPoint.x=blockRect[j].x;
                fallingPoint.y=blockRect[j].y;
                if(isOver(fallingPoint, explosionPoint1)){      
                    fallingRect.add(blockRect[j]);
                    updateScore(50);
                }
            }

            flightCounter=flightCounter+1;    

            if(IS_EXPLODING || (birdRect.x>IView.WIDTH) || (birdRect.y>IView.HEIGHT) || (flightCounter>=IView.NUMBER_OF_SAMPLES)){   //stops the flight         
                setBirdIsFlying(false);   
                flightCounter=0;
                birdRect.x= Level.BIRD_ON_CATAPULT.x;  //we use only one rect "bird" to fly
                birdRect.y= Level.BIRD_ON_CATAPULT.y;

                if(countAliveBirds>0)                   //check if the game has ended
                    countAliveBirds=countAliveBirds-1;      
                if(countAlivePigs==0){
                    updateScore(countAliveBirds*500); 
                    win=true;
                    gameEnded=true;
                }else if(countAliveBirds==0){
                    win=false;
                    gameEnded=true;
                }
            }
        }else if(IS_EXPLODING){     //cicle until all the upper blocks has touched earth 
            updateGravity();
        }
    }    

    private void updateGravity(){
        if(fallingRect.size()!=0){   
            for(int j=0; j<fallingRect.size(); j++){        
                fallingRect.get(j).y=fallingRect.get(j).y+GRAVITY_FALLING_RATE; 
                if(fallingRect.get(j).y==explosionPoint1.y){
                    fallingRect.get(j).x=0;
                    fallingRect.get(j).y=0; 
                    fallingRect.remove(j);
                }
            }
        }
    }

    private boolean isOver(Point p1, Point p2){  //p1 over p2, also not touching
        if((p1.x==p2.x)&&(p1.y<p2.y)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getNumAliveBirds(){
        return countAliveBirds;
    }

    @Override
    public void restartSettings(){  
        gameEnded=false;
        win=false;
        score=0;
    }

    @Override
    public boolean gameEnded(){    
        return gameEnded;
    }

    @Override
    public boolean getWin(){
        return win;
    }

    @Override
    public Point getBirdPoint(){
        birdPoint.x=birdRect.x;
        birdPoint.y=birdRect.y;
        return birdPoint;
    }

    @Override
    public Point getExplosionPoint1(){
        return explosionPoint1;
    }

    @Override
    public Point getExplosionPoint2(){
        return explosionPoint2;
    }

    @Override
    public void resetExplosionPoints(){
        explosionPoint1.x=0;
        explosionPoint1.y=0;
        explosionPoint2.x=0;
        explosionPoint2.y=0;
    }

    @Override
    public Point[] getBirdsPositions(int i){
        if(i==1)
            return level1.getBirdsPositions(); 
        else if(i==2)
            return level2.getBirdsPositions();
        else if(i==3)
            return level3.getBirdsPositions();
        else if(i==4)
            return level4.getBirdsPositions();
        else if(i==5)
            return level5.getBirdsPositions();
        else 
            return null;
    }

    private Point[] getPigsPositions(int i){
        if(i==1)
            return level1.getPigsPositions(); 
        else if(i==2)
            return level2.getPigsPositions();
        else if(i==3)
            return level3.getPigsPositions();
        else if(i==4)
            return level4.getPigsPositions();
        else if(i==5)
            return level5.getPigsPositions();
        else 
            return null;
    }

    private Point[] getBlocksPositions(int i){
        if(i==1)
            return level1.getBlocksPositions(); 
        else if(i==2)
            return level2.getBlocksPositions();
        else if(i==3)
            return level3.getBlocksPositions();
        else if(i==4)
            return level4.getBlocksPositions();
        else if(i==5)
            return level5.getBlocksPositions();
        else 
            return null; 
    }

    @Override
    public int getLevel(){
        return startSetting.getLevel();
    }

    @Override
    public int getDifficulty(){
        return startSetting.getDifficulty();
    }

    @Override
    public String getPlayerName(){
        return startSetting.getPlayerName();
    }

    @Override
    public int getNumberOfBirds(int l){
        if(l==1){
            return level1.getBirdsPositions().length;
        }else if(l==2){
            return level2.getBirdsPositions().length;
        }else if(l==3){
            return level3.getBirdsPositions().length;
        }else if(l==4){
            return level4.getBirdsPositions().length;
        }else if(l==5){
            return level5.getBirdsPositions().length;
        }else
            return 0;
    }

    @Override
    public int getNumberOfPigs(int l){
        if(l==1){
            return level1.getPigsPositions().length;
        }else if(l==2){
            return level2.getPigsPositions().length;
        }else if(l==3){
            return level3.getPigsPositions().length;
        }else if(l==4){
            return level4.getPigsPositions().length;
        }else if(l==5){
            return level5.getPigsPositions().length;
        }else{
            return 0;
        }
    }

    @Override
    public int getNumberOfBlocks(int l){
        if(l==1){
            return level1.getBlocksPositions().length;
        }else if(l==2){
            return level2.getBlocksPositions().length;
        }else if(l==3){
            return level3.getBlocksPositions().length;
        }else if(l==4){
            return level4.getBlocksPositions().length;
        }else if(l==5){
            return level5.getBlocksPositions().length;
        }else{
            return 0;
        }
    }

    public static Logic getInstance(){
        if(instance==null)
            instance=new Logic();   
        return instance;
    }
}