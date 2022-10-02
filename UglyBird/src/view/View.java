package view;

import logic.ILogic;

public class View implements IView{

    private static View instance=null;

    private ILogic iLogic;

    //costruttore

    //metodi
/* 
    @Override
    public String getplayerName() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getdifficultyLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getXCatapult() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getYCatapult() {
        // TODO Auto-generated method stub
        return 0;
    }
    */

    public static IView getInstance() {
        if(instance==null)
            instance=new View();        
        return instance;
    }
}
