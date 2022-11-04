package logic;

import view.IView;

public class StartSetting {
    private String playerName; 
    private int difficulty;
    private int level;
    private String BirdColour;

    public StartSetting(){
        playerName = null;
        difficulty = 0;
        level = 0;
        BirdColour = null;
    }

    protected String getPlayerName(){                                      
        playerName = IView.getIView().getPlayerName();  
        return playerName;
    }

    protected int getLevel(){
        level = IView.getIView().getLevel();            //1, 2, 3, 4, 5
        return level; 
    }

    protected int getDifficulty(){
        difficulty = IView.getIView().getDifficulty();            //0, 1, 2     
        return difficulty; 
    }

    protected String getBirdColour(){
        BirdColour = IView.getIView().getBirdColour();            //colori    
        return BirdColour; 
    }
}
