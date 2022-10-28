package logic;

import view.IView;

public class StartSetting {
    private String playerName; 
    private int difficulty;
    private int level;
    //private String soundState;

    public StartSetting(){
        playerName = null;
        difficulty = 0;
        level = 0;
        //soundState = IView.getIView().getSoundState();  //On, Off
    }

    public String getPlayerName(){
        playerName = IView.getIView().getPlayerName();  
        if((playerName!=null) /*&& (playerName!="Name")*/)          
            return playerName; 
        else
            return "Name not inserted";
    }

    public int getLevel(){
        level = IView.getIView().getLevel();            //1, 2, 3, 4, 5
        return level; 
    }

    public int getDifficulty(){
        difficulty = IView.getIView().getDifficulty();            //0, 1, 2     
        return difficulty; 
    }

}
