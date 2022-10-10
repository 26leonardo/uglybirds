package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Images{
    public static final int TITLE=0;
    public static final int LOGO=1;
    
    public static final int RED_BIRD=2;
    public static final int GREEN_PIG=3;
    public static final int PINK_PIG=4;
    public static final int BLUE_PIG=5;
    public static final int YELLOW_PIG=6;
    public static final int CATAPULT=7;
    public static final int WOOD_BLOCK=8;
    public static final int ROCK_BLOCK=9;
    public static final int GLASS_BLOCK=10;
    public static final int EXPLOSION=11;

    public static final int NIGHT=12;
    public static final int SUNRISE=13;
    public static final int DAY=14;
    public static final int DUSK=15;
    public static final int SUNSET=16;

    public static final int YELLOW_STAR=17;
    public static final int BLACK_STAR=18;
    public static final int GAME_OVER=19;

    public static final int NUMBER_OF_IMAGES=20;
    
    static Image[] imagesArray=null;

    public static void loadImages(){
        try{
            imagesArray=new Image[NUMBER_OF_IMAGES];
            imagesArray[TITLE]=ImageIO.read(new File("../media/img/nome-logo.png")).getScaledInstance(600, 150, Image.SCALE_AREA_AVERAGING);
            imagesArray[LOGO]=ImageIO.read(new File("../media/img/logo.png")).getScaledInstance(70, 70, Image.SCALE_AREA_AVERAGING);
            imagesArray[RED_BIRD]=ImageIO.read(new File("../media/img/red-bird-50.png")).getScaledInstance(MainGUI.BIRD_SIZE, MainGUI.BIRD_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[GREEN_PIG]=ImageIO.read(new File("../media/img/green-pig.png")).getScaledInstance(MainGUI.PIG_SIZE, MainGUI.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[PINK_PIG]=ImageIO.read(new File("../media/img/pink-pig.png")).getScaledInstance(MainGUI.PIG_SIZE, MainGUI.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[BLUE_PIG]=ImageIO.read(new File("../media/img/blue-pig.png")).getScaledInstance(MainGUI.PIG_SIZE, MainGUI.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[YELLOW_PIG]=ImageIO.read(new File("../media/img/yellow-pig.png")).getScaledInstance(MainGUI.PIG_SIZE, MainGUI.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            //imagesArray[CATAPULT]=ImageIO.read(new File("../media/img/lightning.png")).getScaledInstance(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, Image.SCALE_AREA_AVERAGING);
            //imagesArray[WOOD_BLOCK]=ImageIO.read(new File("../media/img/lightning.png")).getScaledInstance(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, Image.SCALE_AREA_AVERAGING);
            //imagesArray[ROCK_BLOCK]=ImageIO.read(new File("../media/img/lightning.png")).getScaledInstance(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, Image.SCALE_AREA_AVERAGING);
            //imagesArray[GLASS_BLOCK]=ImageIO.read(new File("../media/img/lightning.png")).getScaledInstance(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, Image.SCALE_AREA_AVERAGING);
            //imagesArray[EXPLOSION]=ImageIO.read(new File("../media/img/lightning.png")).getScaledInstance(GamePanel.TILE_SIZE, GamePanel.TILE_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[NIGHT]=ImageIO.read(new File("../media/img/night.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[SUNRISE]=ImageIO.read(new File("../media/img/sunrise.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[DAY]=ImageIO.read(new File("../media/img/day.png")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[DUSK]=ImageIO.read(new File("../media/img/dusk.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[SUNSET]=ImageIO.read(new File("../media/img/sunset.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[YELLOW_STAR]=ImageIO.read(new File("../media/img/star.png")).getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING);
            imagesArray[BLACK_STAR]=ImageIO.read(new File("../media/img/black-star.png")).getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING);
            imagesArray[GAME_OVER]=ImageIO.read(new File("../media/img/game-over.png")).getScaledInstance(1000, 330, Image.SCALE_AREA_AVERAGING);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento immagini", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
