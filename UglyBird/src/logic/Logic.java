package logic;

import java.awt.Point;
import java.awt.Rectangle;
import java.lang.Math;

import javax.swing.SwingWorker;
import java.lang.Thread;

import view.MainGUI; 

public class Logic implements ILogic{
    private static Logic instance=null;

    //costanti
    public static boolean BIRD_IS_FLYING = false;
    public static boolean IS_EXPLODING = false;

    //instanziazioni
    Level1 level1;
    Level2 level2;
    Level3 level3;
    StartSetting startSetting;
    //boolean isBirdExploding=false;
    boolean isPigExploding=false;
    boolean isBlockExploding=false;
    int countAliveBirds;
    int countAlivePigs;
    Rectangle birdRect;
    Rectangle[] pigRect;
    Rectangle[] blockRect;
    Point birdPoint;
    Point explosionPoint;
    int flightCounter=0;
    boolean gameEnded=false;
    boolean win=false;
    private int score=0; 
    SwingWorker<Void,Void> workero;

    public Logic(){
        level1 = new Level1();
        level2 = new Level2();
        level3 = new Level3();
        startSetting = new StartSetting();

        birdRect = new Rectangle(Level.BIRD_ON_CATAPULT.x, Level.BIRD_ON_CATAPULT.y, MainGUI.BIRD_SIZE, MainGUI.BIRD_SIZE);
        birdPoint = new Point(birdRect.x,birdRect.y);
        explosionPoint = new Point(0,0);

        pigRect = new Rectangle[4]; //4 è il n max di maialini
        for(int i=0; i<pigRect.length; i++){
            pigRect[i] = new Rectangle(); 
            pigRect[i].x=0;
            pigRect[i].y=0;
            pigRect[i].width=0;
            pigRect[i].height=0;
        }
        
        blockRect = new Rectangle[9]; //9 è il n max di blocchi
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
                    MainGUI.getInstance().updateView();
                    updateGame();
                    if(gameEnded()){
                        MainGUI.getInstance().flip("END_MENU"); //non fa vedere l'ultima esplosione
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

    public void startGame(){
        workero.execute();
    }

    public int[] calculateTrajectoryX(){       
        double xDiscrete = MainGUI.CATAPULT_CENTER_X;
        double[] trajectoryDouble = new double[MainGUI.NUMBER_OF_SAMPLES];  //uso double per evitare errori di arrotondamento

        for(int i=0; i<trajectoryDouble.length; i++){
            trajectoryDouble[i] = xDiscrete;
            xDiscrete = xDiscrete + ((MainGUI.WIDTH*1.0) - (MainGUI.CATAPULT_CENTER_X*1.0))/((MainGUI.NUMBER_OF_SAMPLES*1.0)-1);   
        }
        int[] trajectory=new int[trajectoryDouble.length];
        for(int i=0;i<trajectory.length;i++){
            trajectory[i]=(int)Math.round(trajectoryDouble[i]);     //restituisco un int
        }
        return trajectory;
    }

    public int[] calculateTrajectoryY(int x, int y){       
        int lenghtX = MainGUI.CATAPULT_CENTER_X - x;        //metti costanti che servono su logic su iview
        int lenghtY = y - MainGUI.CATAPULT_CENTER_Y;      
        double percentageVelocity = ( Math.sqrt(Math.pow(lenghtX, 2) + Math.pow(lenghtY, 2))*100
            /(Math.sqrt(Math.pow(MainGUI.CATAPULT_CENTER_X, 2)+ Math.pow(MainGUI.HEIGHT-MainGUI.CATAPULT_CENTER_Y, 2)))); //percentuale di lunghezza del tiro rispetto a lunghezza massima possibile (usando pitagora)
        double initialVelocity = percentageVelocity*MainGUI.MAX_INITIAL_VELOCITY;
        double angle = Math.atan2(lenghtY, lenghtX);
        double iniVelX = Math.cos(angle)*initialVelocity;
        double iniVelY = -Math.sin(angle)*initialVelocity;
        int[] trajectoryX=calculateTrajectoryX();
        int[] trajectory = new int[MainGUI.NUMBER_OF_SAMPLES];

        for(int i=0; i<trajectory.length; i++){
            trajectory[i]=(int) (+0.5*9.81*Math.pow((trajectoryX[i]-MainGUI.CATAPULT_CENTER_X)/iniVelX, 2) + iniVelY*(trajectoryX[i]-MainGUI.CATAPULT_CENTER_X)/iniVelX + MainGUI.CATAPULT_CENTER_Y);   //formula traiettoria moto parabolico
        }
        return trajectory;
    }

    public void updateRect(){
        countAliveBirds=getNumberOfBirds(getLevel());
        countAlivePigs=getNumberOfPigs(getLevel());
        
        for(int i=0; i<this.getNumberOfPigs(this.getLevel()); i++){
            pigRect[i].x = this.getPigsPositions(this.getLevel())[i].x;
            pigRect[i].y = this.getPigsPositions(this.getLevel())[i].y;
            pigRect[i].width = MainGUI.PIG_SIZE;
            pigRect[i].height = MainGUI.PIG_SIZE;
        }

        for(int i=0; i<this.getNumberOfBlocks(this.getLevel()); i++){      
            blockRect[i].x = this.getBlocksPositions(this.getLevel())[i].x;
            blockRect[i].y = this.getBlocksPositions(this.getLevel())[i].y;
            blockRect[i].width = MainGUI.BLOCK_SIZE;
            blockRect[i].height = MainGUI.BLOCK_SIZE;
        }
    }

    public Rectangle[] getPigsRect(){
        return pigRect;
    }

    public Rectangle[] getBlocksRect(){
        return blockRect;
    }

    public void birdIsFlying(boolean i){
        BIRD_IS_FLYING = i;
    }

    public void isExploding(boolean i){
        IS_EXPLODING = i;
    }

    public void updateScore(int points){
        score += points;
    }

    public int getScore(){
        return score;
    }

    public void updateGame(){  //poi devo mettere bene condizioni di quando vola, quando esplode etc
        if(BIRD_IS_FLYING){
            birdRect.x = this.calculateTrajectoryX()[flightCounter]-MainGUI.BIRD_SIZE/2;
            birdRect.y = this.calculateTrajectoryY(MainGUI.getInstance().getXReleased(), MainGUI.getInstance().getYReleased())[flightCounter]-MainGUI.BIRD_SIZE/2; 
            for(int j=0; j<pigRect.length; j++){
                if(birdRect.intersects(pigRect[j])){
                    //isPigExploding=true;    //servirà per rendere null un elemento dell'array 
                    IS_EXPLODING=true;
                    explosionPoint.x = pigRect[j].x;    //devi farlo valere per più di un esplosione in contemporanea
                    explosionPoint.y = pigRect[j].y;    //devi farlo valere per più di un esplosione in contemporanea
                    pigRect[j].x=0;   
                    pigRect[j].y=0; 
                    updateScore(300);
                    if(countAlivePigs>0)
                        countAlivePigs=countAlivePigs-1;  
                }
            }
            for(int j=0; j<blockRect.length; j++){
                if(birdRect.intersects(blockRect[j])){
                    //isBlockExploding=true;  //(poi devi far esplodere anche i blocchi sopra, magari facendoli cadere)
                    IS_EXPLODING=true;
                    explosionPoint.x = blockRect[j].x;    //devi farlo valere per più di un esplosione in contemporanea
                    explosionPoint.y = blockRect[j].y;    //devi farlo valere per più di un esplosione in contemporanea
                    blockRect[j].x=0;
                    blockRect[j].y=0;
                    updateScore(150);
                }
            }
            flightCounter=flightCounter+1;
            if(IS_EXPLODING || (birdRect.x>MainGUI.WIDTH) || (birdRect.y>MainGUI.HEIGHT) || (flightCounter>=MainGUI.NUMBER_OF_SAMPLES)){  //attenzione! su flight counter dava errore perché mettendo solo > giravo un array da 500 posizioni 501 volte
                birdIsFlying(false);        
                flightCounter=0;
                birdRect.x= Level.BIRD_ON_CATAPULT.x;  //uso sempre lo stesso uccellino per volare, poi devo eliminare gli altri
                birdRect.y= Level.BIRD_ON_CATAPULT.y;
                if(countAliveBirds>0)
                    countAliveBirds=countAliveBirds-1;
            }
            if(countAlivePigs==0){
                gameEnded=true;
                win=true;
            }else if(countAliveBirds==0){
                gameEnded=true;
                win=false;
            }
        }
    }    

    public void restartSettings(){  //reinizializza le variabili 
        gameEnded=false;
        win=false;
        score=0;
    }

    public boolean gameEnded(){    //usala per cambiare pannello, salvare score finale, ...
        return gameEnded;
    }

    public boolean getWin(){
        return win;
    }

    public Point getBirdPoint(){
        birdPoint.x=birdRect.x;
        birdPoint.y=birdRect.y;
        return birdPoint;
    }

    public Point getExplosionPoint(){
        return explosionPoint;
    }

    public Point[] getBirdsPositions(int i){
        if(i==1)
            return level1.getBirdsPositions(); //per specificare il livello basta solo chiamarla su l1 vero?
        else if(i==2)
            return level2.getBirdsPositions();
        else if(i==3)
            return level3.getBirdsPositions();
        else 
            return null;
    }

    public Point[] getPigsPositions(int i){
        if(i==1)
            return level1.getPigsPositions(); //per specificare il livello basta solo chiamarla su l1 vero?
        else if(i==2)
            return level2.getPigsPositions();
        else if(i==3)
            return level3.getPigsPositions();
        else 
            return null;
    }

    public Point[] getBlocksPositions(int i){
        if(i==1){
            return level1.getBlocksPositions(); 
        }else if(i==2){
            return level2.getBlocksPositions();
        }else if(i==3){
            return level3.getBlocksPositions();
        }else 
            return null; 
    }

    public int getLevel(){
        return startSetting.getLevel();
    }

    public int getDifficulty(){
        return startSetting.getDifficulty();
    }

    public String getPlayerName(){
        return startSetting.getPlayerName();
    }

    public int getNumberOfBirds(int l){
        if(l==1){
            return level1.getBirdsPositions().length;
        }else if(l==2){
            return level2.getBirdsPositions().length;
        }else if(l==3){
            return level3.getBirdsPositions().length;
        }else
            return 0;
    }


    public int getNumberOfPigs(int l){
        if(l==1){
            return level1.getPigsPositions().length;
        }else if(l==2){
            return level2.getPigsPositions().length;
        }else if(l==3){
            return level3.getPigsPositions().length;
        }else{
            return 0;
        }
    }

    public int getNumberOfBlocks(int l){
        if(l==1){
            return level1.getBlocksPositions().length;
        }else if(l==2){
            return level2.getBlocksPositions().length;
        }else if(l==3){
            return level3.getBlocksPositions().length;
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