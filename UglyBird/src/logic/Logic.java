package logic;

import java.awt.Point;
import java.lang.Math;

import view.MainGUI; 

//import view.IView;

public class Logic implements ILogic{
    private static Logic instance=null;
    //private IView iView;

    //costanti

    //instanziazioni
    Level1 level1;
    Level2 level2;
    Level3 level3;
    StartSetting startSetting;

    //costruttore
    public Logic(){
        level1 = new Level1();
        level2 = new Level2();
        level3 = new Level3();
        startSetting = new StartSetting();
    }

    //metodi
/*
    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getPanel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumBirds() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean ArcIsVisible() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Impact() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Win() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int NumPodium() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void resetSettings(){
        //TODO
    }

*/

    public int[] calculateTrajectoryX(){       
        int xDiscrete = MainGUI.CATAPULT_CENTER_X;
        int[] trajectory = new int[MainGUI.NUMBER_OF_SAMPLES];

        for(int i=0; i<MainGUI.NUMBER_OF_SAMPLES; i++){
            trajectory[i] = xDiscrete;
            xDiscrete = xDiscrete + (MainGUI.WIDTH-MainGUI.CATAPULT_CENTER_X)/(MainGUI.NUMBER_OF_SAMPLES-50);
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
        int xDiscrete = MainGUI.CATAPULT_CENTER_X;
        int[] trajectory = new int[MainGUI.NUMBER_OF_SAMPLES];

        for(int i=0; i<MainGUI.NUMBER_OF_SAMPLES; i++){
            trajectory[i]=(int) (+0.5*9.81*Math.pow((xDiscrete-MainGUI.CATAPULT_CENTER_X)/iniVelX, 2) + iniVelY*(xDiscrete-MainGUI.CATAPULT_CENTER_X)/iniVelX + MainGUI.CATAPULT_CENTER_Y);   //formula traiettoria moto parabolico, in x e y
            xDiscrete= xDiscrete + (MainGUI.WIDTH-MainGUI.CATAPULT_CENTER_X)/(MainGUI.NUMBER_OF_SAMPLES-50);
        }
        return trajectory;
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