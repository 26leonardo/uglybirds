package logic;

import view.IView;

public class StartSetting {
    private String playerName; 
    private int difficulty;
    private int level;
    private String pigColour;
    private String soundState;

    public StartSetting(){

        playerName = IView.getIView().getPlayerName();
        difficulty = IView.getIView().getDifficulty();  //0, 1, 2
        level = IView.getIView().getLevel();            //1, 2, 3
        pigColour = IView.getIView().getPigColour();    //green, pink, blue, yellow
        soundState = IView.getIView().getSoundState();  //On, Off
    }

}
