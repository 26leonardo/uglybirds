package logic;

public interface ILogic {
    
    public static final int REFRESH_TIME=3;

    public void startGame();    //used in MainGUI
    public void updateRect();   //used in StartSetting
    public int getLevel();      //used in Canva
    public int getDifficulty();
    public int getNumAliveBirds();
    public int getNumberOfPigs(int i);
    public int getNumberOfBlocks(int i);
    public int[] getBirdsPositionsX(int i);  
    public int[] getBirdsPositionsY(int i);  
    public int[] getPigsX();
    public int[] getPigsY();
    public int[] getBlocksX();  
    public int[] getBlocksY();  
    public int[] calculateTrajectoryX();
    public int[] calculateTrajectoryY(int x, int y);
    public void setBirdIsFlying(boolean i);
    public boolean getIsFlying();
    public void setIsExploding(boolean i);
    public boolean getIsExploding();
    public int getExplosionPoint1X();
    public int getExplosionPoint1Y();
    public int getExplosionPoint2X();
    public int getExplosionPoint2Y();
    public void resetExplosionPoints();
    public int getScore();
    public void restartSettings();      //used in EndMenu
    public boolean getWin();
    public int[] getScoreRankings();    
    public String[] getNameRankings();
    public String[] getDiffRankings();
    public String[] getBirdRankings();
    public void deleteFile();

    public static ILogic getILogic(){
        return Logic.getInstance();
    }
}
