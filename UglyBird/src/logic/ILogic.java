package logic;

import java.awt.Point;
import java.awt.Rectangle;

public interface ILogic {
    //costanti statiche non modificabili
    public static final int REFRESH_TIME=3;

    public void startGame();
    public int[] calculateTrajectoryX();
    public int[] calculateTrajectoryY(int x, int y);
    public void birdIsFlying(boolean i);
    public void isExploding(boolean i);
    public Point getBirdPoint();
    public Rectangle[] getPigsRect();
    public Rectangle[] getBlocksRect();     
    public Point getExplosionPoint();
    public void updateGame();
    public void updateRect();
    public Point[] getBirdsPositions(int i);        //levalo e passa un array di rect
    //public Point[] getPigsPositions(int i);
    //public Point[] getBlocksPositions(int i);
    public int getLevel();
    public int getDifficulty();
    public int getNumberOfBirds(int i); //quelli iniziali
    public int getNumberOfPigs(int i);
    public int getNumberOfBlocks(int i);
    public String getPlayerName();
    public boolean gameEnded();
    public boolean getWin();
    public void updateScore(int points);
    public int getScore();
    public void restartSettings();
    // public int NumPodium(); //se 0 o >4 non sta sul podio, se 1 è primo, se 2 secondo, se 3 terzo

    public static ILogic getILogic(){
        return Logic.getInstance();
    }
}
