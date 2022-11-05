package logic;

import view.IView;

public class StartSetting {
    private String playerName; 
    private int difficulty;
    private int level;
    private String birdColour;

    public StartSetting(){
        playerName = null;
        difficulty = 0;
        level = 0;
        birdColour = null;
    }

    protected String getPlayerName(){
        playerName = IView.getIView().getPlayerName();  
        if(playerName!=null)          
            return playerName; 
        else
            return "Name not inserted";
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
        birdColour = IView.getIView().getBirdColour();            //colori    
        return birdColour; 
    }
}
