package logic;

import java.lang.Math;
import java.lang.Thread;
import java.util.LinkedList;
import javax.swing.SwingWorker;

import utils.ClipManager;
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
    private Rankings rankings;
    private int[] birdsPositionsX;
    private int[] birdsPositionsY;
    private Rectangle birdRect;
    private Rectangle[] pigRect;
    private int[] pigRectX;
    private int[] pigRectY;
    private Rectangle[] blockRect;
    private int[] blockRectX;
    private int[] blockRectY;
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
    private ClipManager cm;
    private boolean endGameSoundPlayed=false;

    private Logic(){
        level1 = new Level1();
        level2 = new Level2();
        level3 = new Level3();
        level4 = new Level4();
        level5 = new Level5();
        startSetting = new StartSetting();
        rankings = new Rankings();

        birdRect = new Rectangle(Level.BIRD_ON_CATAPULT.x, Level.BIRD_ON_CATAPULT.y, IView.BIRD_SIZE, IView.BIRD_SIZE);
        explosionPoint1 = new Point(0,0);
        explosionPoint2 = new Point(0,0);
        fallingPoint= new Point(0,0);
        fallingRect = new LinkedList<>();
        cm = ClipManager.getInstance();
        birdsPositionsX=new int[5]; //5 is n max of birds
        for(int i=0; i<birdsPositionsX.length; i++){
            birdsPositionsX[i]=0;
        }
        birdsPositionsY=new int[5];
        for(int i=0; i<birdsPositionsY.length; i++){
            birdsPositionsY[i]=0;
        }
        pigRectX=new int[4];
        pigRectY=new int[4];
        blockRectX=new int[9];
        blockRectY=new int[9];

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
                    if(IS_EXPLODING){
                        cm.play(ClipManager.EXPLOSION_CLIP);
                    }
                    if(gameEnded && (!IS_EXPLODING)){  
                        IView.getIView().flip("END_MENU");
                        if(!endGameSoundPlayed){
                            if(win){
                                cm.play(ClipManager.WIN_CLIP);
                                endGameSoundPlayed=true;
                            }else{
                                cm.play(ClipManager.GAME_OVER_CLIP);
                                endGameSoundPlayed=true;
                            }
                        }
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

    //logic of bird flight
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

    private void updateGame(){  
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
                    rankings.readFile(getLevel());
                    rankings.writeFile(getScore(), startSetting.getPlayerName(), startSetting.getBirdColour(), getDifficulty(), getLevel()); 
                }else if(countAliveBirds==0){
                    win=false;
                    gameEnded=true;
                }
            }
        }else if(IS_EXPLODING){     //cicle until all the upper blocks have touched earth 
            updateGravity();
        }
    }    

    //logic of gravity
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

    //set starting positions
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
    
    //get positions to draw the elements
    @Override
    public int[] getPigsX(){
        for(int i=0; i<pigRect.length; i++){
            pigRectX[i]=pigRect[i].x;
        }
        return pigRectX;
    }

    @Override
    public int[] getPigsY(){
        for(int i=0; i<pigRect.length; i++){
            pigRectY[i]=pigRect[i].y;
        }
        return pigRectY;
    }

    @Override
    public int[] getBlocksX(){
        for(int i=0; i<blockRect.length; i++){
            blockRectX[i]=blockRect[i].x;
        }
        return blockRectX;
    }

    @Override
    public int[] getBlocksY(){
        for(int i=0; i<blockRect.length; i++){
            blockRectY[i]=blockRect[i].y;
        }
        return blockRectY;
    }

    @Override
    public int[] getBirdsPositionsX(int i){
        birdsPositionsX[0]=birdRect.x;
        if(i==1){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsX[j]=level1.getBirdsPositions()[j].x;
            }
        }else if(i==2){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsX[j]=level2.getBirdsPositions()[j].x;
            }
        }else if(i==3){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsX[j]=level3.getBirdsPositions()[j].x;
            }
        }else if(i==4){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsX[j]=level4.getBirdsPositions()[j].x;
            }
        }else if(i==5){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsX[j]=level5.getBirdsPositions()[j].x;
            }
        }else{
            return null;
        }
        return birdsPositionsX;
    }

    @Override
    public int[] getBirdsPositionsY(int i){
        birdsPositionsY[0]=birdRect.y;
        if(i==1){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsY[j]=level1.getBirdsPositions()[j].y;
            }
        }else if(i==2){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsY[j]=level2.getBirdsPositions()[j].y;
            }
        }else if(i==3){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsY[j]=level3.getBirdsPositions()[j].y;
            }
        }else if(i==4){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsY[j]=level4.getBirdsPositions()[j].y;
            }
        }else if(i==5){
            for(int j=1; j<getNumberOfBirds(i); j++){
                birdsPositionsY[j]=level5.getBirdsPositions()[j].y;
            }
        }else{
            return null;
        }
        return birdsPositionsY;
    }

    //set or get the state of the game
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
    public int getExplosionPoint1X(){
        return explosionPoint1.x;
    }

    @Override
    public int getExplosionPoint1Y(){
        return explosionPoint1.y;
    }

    @Override
    public int getExplosionPoint2X(){
        return explosionPoint2.x;
    }

    @Override
    public int getExplosionPoint2Y(){
        return explosionPoint2.y;
    }

    @Override
    public void resetExplosionPoints(){
        explosionPoint1.x=0;
        explosionPoint1.y=0;
        explosionPoint2.x=0;
        explosionPoint2.y=0;
    }

    private void updateScore(int points){
        score += points;
    }

    @Override
    public int getScore(){
        return score;
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
        endGameSoundPlayed=false;
    }

    @Override
    public boolean getWin(){
        return win;
    }

    //get start settings
    @Override
    public int getLevel(){
        return startSetting.getLevel();
    }

    @Override
    public int getDifficulty(){
        return startSetting.getDifficulty();
    }

    private int getNumberOfBirds(int l){
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

    //communicate with files
    @Override
    public int[] getScoreRankings(){
        return rankings.getScores();
    }

    @Override
    public String[] getNameRankings(){
        return rankings.getNames();
    }

    @Override
    public String[] getBirdRankings(){
        return rankings.getBird();
    }

    @Override
    public String[] getDiffRankings(){
        return rankings.getDiff();
    }

    @Override
    public void deleteFile(){
        rankings.resetArray();
        rankings.deleteFile(getLevel());
    }

    //create a single istance of Logic
    public static Logic getInstance(){
        if(instance==null)
            instance=new Logic();   
        return instance;
    }
}