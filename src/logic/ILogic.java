package logic;

public interface ILogic {
    //costanti statiche non modificabili

    //altri metodi
    // public int getArcCoord();
    // public int getXBird();
    // public int getYBird();
    // public int[] getXPig();
    // public int[] getYPig();
    // public int[] getXBlock();
    // public int[] getYBlock();
    // public int getScore();
    // public int getLevel();
    // public int getPanel();
    // public int getNumBirds();
    // public boolean ArcIsVisible();
    // public boolean Impact(); //non so come specificare anche quali due oggetti collidono
    // public boolean Win();
    // public void resetSettings();
    // public int NumPodium(); //se 0 o >4 non sta sul podio, se 1 è primo, se 2 secondo, se 3 terzo

    public static ILogic getILogic(){
        return Logic.getInstance();
    }
}
