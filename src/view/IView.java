package view;

public interface IView{
    // public String getplayerName();
    // public int getdifficultyLevel();
    // public int getXCatapult();
    // public int getYCatapult();
    public static IView getIView(){
        return View.getInstance();
    }
}