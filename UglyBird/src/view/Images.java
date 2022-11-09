package view;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Images{
    protected static final int TITLE=0;
    protected static final int LOGO=1;
    
    protected static final int RED_BIRD=2;
    protected static final int GREEN_PIG=3;
    protected static final int PINK_PIG=4;
    protected static final int BLUE_PIG=5;
    protected static final int YELLOW_PIG=6;
    protected static final int CATAPULT=7;
    protected static final int WOOD_BLOCK=8;
    protected static final int METAL_BLOCK=9;
    protected static final int GLASS_BLOCK=10;

    protected static final int NIGHT=11;
    protected static final int SUNRISE=12;
    protected static final int DAY=13;
    protected static final int DUSK=14;
    protected static final int SUNSET=15;

    protected static final int YELLOW_STAR=16;
    protected static final int BLACK_STAR=17;
    protected static final int GAME_OVER=18;

    protected static final int EXPLOSION_1=19;
    protected static final int EXPLOSION_2=20;
    protected static final int EXPLOSION_3=21;
    protected static final int EXPLOSION_4=22;
    protected static final int EXPLOSION_5=23;
    protected static final int EXPLOSION_6=24;
    protected static final int EXPLOSION_7=25;

    protected static final int YELLOW_BIRD=26;
    protected static final int BLUE_BIRD=27;
    protected static final int WHITE_BIRD=28;

    protected static final int NUMBER_OF_IMAGES=29;
    
    protected static Image[] imagesArray=null;

    protected static void loadImages(){
        try{
            imagesArray=new Image[NUMBER_OF_IMAGES];
            imagesArray[TITLE]=ImageIO.read(new File("../media/img/nome-logo.png")).getScaledInstance(600, 150, Image.SCALE_AREA_AVERAGING);
            imagesArray[LOGO]=ImageIO.read(new File("../media/img/logo.png")).getScaledInstance(70, 70, Image.SCALE_AREA_AVERAGING);
            imagesArray[RED_BIRD]=ImageIO.read(new File("../media/img/red-bird-50.png")).getScaledInstance(IView.BIRD_SIZE, IView.BIRD_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[GREEN_PIG]=ImageIO.read(new File("../media/img/green-pig.png")).getScaledInstance(IView.PIG_SIZE, IView.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[PINK_PIG]=ImageIO.read(new File("../media/img/pink-pig.png")).getScaledInstance(IView.PIG_SIZE, IView.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[BLUE_PIG]=ImageIO.read(new File("../media/img/blue-pig.png")).getScaledInstance(IView.PIG_SIZE, IView.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[YELLOW_PIG]=ImageIO.read(new File("../media/img/yellow-pig.png")).getScaledInstance(IView.PIG_SIZE, IView.PIG_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[CATAPULT]=ImageIO.read(new File("../media/img/catapult.png")).getScaledInstance(MainGUI.CATAPULT_SIZE, MainGUI.CATAPULT_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[WOOD_BLOCK]=ImageIO.read(new File("../media/img/wood-block.png")).getScaledInstance(IView.BLOCK_SIZE, IView.BLOCK_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[METAL_BLOCK]=ImageIO.read(new File("../media/img/metal-block.png")).getScaledInstance(IView.BLOCK_SIZE, IView.BLOCK_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[GLASS_BLOCK]=ImageIO.read(new File("../media/img/glass-block.png")).getScaledInstance(IView.BLOCK_SIZE, IView.BLOCK_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[NIGHT]=ImageIO.read(new File("../media/img/night.jpg")).getScaledInstance(IView.WIDTH, IView.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[SUNRISE]=ImageIO.read(new File("../media/img/sunrise.jpg")).getScaledInstance(IView.WIDTH, IView.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[DAY]=ImageIO.read(new File("../media/img/day.png")).getScaledInstance(IView.WIDTH, IView.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[DUSK]=ImageIO.read(new File("../media/img/dusk.jpg")).getScaledInstance(IView.WIDTH, IView.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[SUNSET]=ImageIO.read(new File("../media/img/sunset.jpg")).getScaledInstance(IView.WIDTH, IView.HEIGHT, Image.SCALE_AREA_AVERAGING);
            imagesArray[YELLOW_STAR]=ImageIO.read(new File("../media/img/star.png")).getScaledInstance(MainGUI.STAR_SIZE, MainGUI.STAR_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[BLACK_STAR]=ImageIO.read(new File("../media/img/black-star.png")).getScaledInstance(MainGUI.STAR_SIZE, MainGUI.STAR_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[GAME_OVER]=ImageIO.read(new File("../media/img/game-over.png")).getScaledInstance(1000, 330, Image.SCALE_AREA_AVERAGING);
            imagesArray[EXPLOSION_1]=ImageIO.read(new File("../media/img/explosion-1.png")).getScaledInstance(MainGUI.EXPLOSION_SIZE, MainGUI.EXPLOSION_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[EXPLOSION_2]=ImageIO.read(new File("../media/img/explosion-2.png")).getScaledInstance(MainGUI.EXPLOSION_SIZE, MainGUI.EXPLOSION_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[EXPLOSION_3]=ImageIO.read(new File("../media/img/explosion-3.png")).getScaledInstance(MainGUI.EXPLOSION_SIZE, MainGUI.EXPLOSION_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[EXPLOSION_4]=ImageIO.read(new File("../media/img/explosion-4.png")).getScaledInstance(MainGUI.EXPLOSION_SIZE, MainGUI.EXPLOSION_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[EXPLOSION_5]=ImageIO.read(new File("../media/img/explosion-5.png")).getScaledInstance(MainGUI.EXPLOSION_SIZE, MainGUI.EXPLOSION_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[EXPLOSION_6]=ImageIO.read(new File("../media/img/explosion-6.png")).getScaledInstance(MainGUI.EXPLOSION_SIZE, MainGUI.EXPLOSION_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[EXPLOSION_7]=ImageIO.read(new File("../media/img/explosion-7.png")).getScaledInstance(MainGUI.EXPLOSION_SIZE, MainGUI.EXPLOSION_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[YELLOW_BIRD]=ImageIO.read(new File("../media/img/yellow-bird.png")).getScaledInstance(IView.BIRD_SIZE, IView.BIRD_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[BLUE_BIRD]=ImageIO.read(new File("../media/img/blue-bird.png")).getScaledInstance(IView.BIRD_SIZE, IView.BIRD_SIZE, Image.SCALE_AREA_AVERAGING);
            imagesArray[WHITE_BIRD]=ImageIO.read(new File("../media/img/white-bird.png")).getScaledInstance(IView.BIRD_SIZE, IView.BIRD_SIZE, Image.SCALE_AREA_AVERAGING);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento immagini", "Errore", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
    }
}
