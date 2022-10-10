package view;

public interface IView{
    public String getPlayerName();
    public int getDifficulty();
    public int getLevel();
    public String getPigColour();
    public String getSoundState();
    // public int getXCatapult();
    // public int getYCatapult();
    public static IView getIView(){
        return MainGUI.getInstance();
    }
}