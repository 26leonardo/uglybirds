package view;

public interface IView{

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    public final static int BIRD_SIZE = 50;
    public final static int PIG_SIZE = 50;
    public final static int BLOCK_SIZE = 50;
    public final static int CATAPULT_CENTER_X = MainGUI.CATAPULT_POSITION_X + (MainGUI.CATAPULT_SIZE)/2;
    public final static int CATAPULT_CENTER_Y = MainGUI.CATAPULT_POSITION_Y + (MainGUI.CATAPULT_SIZE)/2 -8;
    public final static int NUMBER_OF_SAMPLES = 600; 

    public String getPlayerName();  //from StartMenu
    public String getDifficulty();
    public String getLevel();
    public String getBirdColour();
    public String getSoundState();
    public int getXReleased();      //from Canva
    public int getYReleased();
    public void updateView();       
    public void flip(String g1);    //from MainGUI
    
    public static IView getIView(){
        return MainGUI.getInstance();
    }
}