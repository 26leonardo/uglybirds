package view;

public interface IView{

    public String getPlayerName();
    public int getDifficulty();
    public int getLevel();
    //public String getPigColour();
    public String getSoundState();
    public int getXReleased();
    public int getYReleased();
    public static IView getIView(){
        return MainGUI.getInstance();
    }
}