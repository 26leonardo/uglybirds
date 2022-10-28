package logic;

import java.awt.Point;
import java.awt.Rectangle;

public interface ILogic {
    
    public static final int REFRESH_TIME=3;

    public void startGame();
    public int[] calculateTrajectoryX();
    public int[] calculateTrajectoryY(int x, int y);
    public void setBirdIsFlying(boolean i);
    public void setIsExploding(boolean i);
    public boolean getIsFlying();
    public boolean getIsExploding();
    public int getNumAliveBirds();
    public Point getBirdPoint();
    public Rectangle[] getPigsRect();
    public Rectangle[] getBlocksRect();     
    public Point getExplosionPoint1();
    public Point getExplosionPoint2();
    public void resetExplosionPoints();
    public void updateGame();
    public void updateRect();
    public Point[] getBirdsPositions(int i);       
    public int getLevel();
    public int getDifficulty();
    public int getNumberOfBirds(int i); //the starting ones
    public int getNumberOfPigs(int i);
    public int getNumberOfBlocks(int i);
    public String getPlayerName();
    public boolean gameEnded();
    public boolean getWin();
    public void updateScore(int points);
    public int getScore();
    public void restartSettings();

    public static ILogic getILogic(){
        return Logic.getInstance();
    }
}
