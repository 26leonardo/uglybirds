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
        if(IView.getIView().getLevel()=="1"){
            level=1;
        }else if(IView.getIView().getLevel()=="2"){
            level=2;
        }else if(IView.getIView().getLevel()=="3"){
            level=3;
        }else if(IView.getIView().getLevel()=="4"){
            level=4;
        }else if(IView.getIView().getLevel()=="5"){
            level=5;
        }
        return level; 
    }

    protected int getDifficulty(){
        if(IView.getIView().getDifficulty()=="0"){  //0 easy, 1 medium, 2 difficult
            difficulty=0;
        }else if(IView.getIView().getDifficulty()=="1"){
            difficulty=1;
        }else if(IView.getIView().getDifficulty()=="2"){
            difficulty=2;
        }
        return difficulty; 
    }

    protected String getBirdColour(){
        birdColour = IView.getIView().getBirdColour();             
        return birdColour; 
    }
}
