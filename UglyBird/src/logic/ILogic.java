package logic;

import java.awt.Point;

public interface ILogic {
    //costanti statiche non modificabili

    public int[] calculateTrajectoryX();
    public int[] calculateTrajectoryY(int x, int y);
    public Point[] getBirdsPositions(int i);
    public Point[] getPigsPositions(int i);
    public Point[] getBlocksPositions(int i);
    public int getLevel();
    public int getDifficulty();
    public int getNumberOfBirds(int i);
    public int getNumberOfPigs(int i);
    public int getNumberOfBlocks(int i);
    public String getPlayerName();
    // public int getScore();
    // public int getLevel();
    // public int getPanel();
    // public int getNumBirds();
    // public boolean ArcIsVisible();
    // public boolean Impact(); //non so come specificare anche quali due oggetti collidono
    // public boolean Win();
    // public void resetSettings();
    // public int NumPodium(); //se 0 o >4 non sta sul podio, se 1 è primo, se 2 secondo, se 3 terzo

    public static ILogic getILogic(){
        return Logic.getInstance();
    }
}
