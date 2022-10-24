package logic;

import java.awt.Point;
import java.awt.Rectangle;

public interface ILogic {
    //costanti statiche non modificabili

    public int[] calculateTrajectoryX();
    public int[] calculateTrajectoryY(int x, int y);
    public void birdIsFlying(boolean i);
    public void isExploding(boolean i);
    public Point getBirdPoint();
    public Rectangle[] getPigsRect();
    public Rectangle[] getBlocksRect();     //è meglio passare punti?
    public Point getExplosionPoint();
    public void updateBird();
    public void updateRect();
    public Point[] getBirdsPositions(int i);        //levalo e passa un array di rect
    //public Point[] getPigsPositions(int i);
    //public Point[] getBlocksPositions(int i);
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
