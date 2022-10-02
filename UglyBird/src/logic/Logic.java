package logic;

import view.IView;

public class Logic implements ILogic{
    private static Logic instance=null;

    //costanti

    //instanziazioni

    //costruttore

    //metodi
/*
    @Override
    public int getArcCoord() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getXBird() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getYBird() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int[] getXPig() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getYPig() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getXBlock() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int[] getYBlock() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getLevel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getPanel() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getNumBirds() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public boolean ArcIsVisible() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Impact() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean Win() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public int NumPodium() {
        // TODO Auto-generated method stub
        return 0;
    }
*/
    public static Logic getInstance(){
        if(instance==null)
            instance=new Logic();        
        return instance;
    }
}
