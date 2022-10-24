package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

//import logic.ILogic;

import javax.swing.ImageIcon;

import utils.FontReader;


public class MainGUI extends JFrame implements IView{
    private static MainGUI instance=null;

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    public final static int BIRD_SIZE = 50;
    public final static int PIG_SIZE = 50;
    public final static int BLOCK_SIZE = 50;
    public final static int CATAPULT_SIZE = 60;
    public final static int EXPLOSION_SIZE = 70;
    public final static int STAR_SIZE = 200;
    public final static int CATAPULT_POSITION_X = 125;
    public final static int CATAPULT_POSITION_Y = 525;
    public final static int CATAPULT_CENTER_X = CATAPULT_POSITION_X + (CATAPULT_SIZE)/2;
    public final static int CATAPULT_CENTER_Y = CATAPULT_POSITION_Y + (CATAPULT_SIZE)/2 -8;
    public final static double MAX_INITIAL_VELOCITY = 1.75; 
    public final static int NUMBER_OF_SAMPLES = 600; 
    public static int SCORE = 0000;
    public final static Font ANGRY_BIRDS_FONT = FontReader.readFont("angry-birds");
    
    JPanel cards;
    StartMenu startMenuLayer;
    Canva canvaLayer;
    EndMenu endMenuLayer;
    HashMap<String, JPanel> panelMap;

    public MainGUI() {
		super("Ugly Birds");
        Images.loadImages();
        this.createGUI();
    }

    public void flip(String g1){
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, g1);
        panelMap.get(g1).grabFocus();
    }

    private void createGUI() {

        //ILogic.getILogic().doSomething();

        startMenuLayer = new StartMenu();

        canvaLayer = new Canva();

        endMenuLayer = new EndMenu();

        cards = new JPanel();
        cards.setLayout(new CardLayout());

        panelMap = new HashMap<String, JPanel>(); 
       
        cards.add(startMenuLayer, "START_MENU");
        panelMap.put("START_MENU", startMenuLayer);
        cards.add(canvaLayer, "CANVA"); 
        panelMap.put("CANVA", canvaLayer);
        cards.add(endMenuLayer, "END_MENU");  
        panelMap.put("END_MENU", endMenuLayer);  

        this.add(cards);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setVisible(true);

        ImageIcon logo = new ImageIcon(Images.imagesArray[Images.LOGO]);
        this.setIconImage(logo.getImage());

    }

    public static MainGUI getInstance() {
        if(instance==null)
            instance=new MainGUI();        
        return instance;
    }

    @Override
    public String getPlayerName() {
        return startMenuLayer.getPlayerName();
    }

    @Override
    public int getDifficulty() {
        return startMenuLayer.getDifficulty(); //0 easy, 1 medium, 2 hard
    }

    @Override
    public int getLevel() {
        return (startMenuLayer.getLevel() + 1); 
    }

    public String getPigColour() {
        return startMenuLayer.getPigColour(); //green, pink, blue, yellow
    }

    @Override
    public String getSoundState() {
        return startMenuLayer.getSoundState(); //On, Off
    }

    @Override
    public int getXReleased() {
        return canvaLayer.getXReleased();
    }

    @Override
    public int getYReleased() {
        return canvaLayer.getYReleased();
    }

}