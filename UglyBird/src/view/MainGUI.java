package view;

import java.awt.CardLayout;
import java.awt.Font;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.FontReader;

import logic.ILogic;

public class MainGUI extends JFrame implements IView{
    private static MainGUI instance=null;

    protected final static int CATAPULT_SIZE = 80;
    protected final static int EXPLOSION_SIZE = 70;
    protected final static int STAR_SIZE = 200;
    protected final static int CATAPULT_POSITION_X = 125;
    protected final static int CATAPULT_POSITION_Y = 525;
    protected final static Font ANGRY_BIRDS_FONT = FontReader.readFont("angry-birds");
    
    private JPanel cards;
    private StartMenu startMenuLayer;
    private Canva canvaLayer;
    private EndMenu endMenuLayer;
    private HashMap<String, JPanel> panelMap;

    private MainGUI() {
		super("Ugly Birds");
        Images.loadImages();
        this.createGUI();
    }

    private void createGUI() {

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
        this.setSize(IView.WIDTH, IView.HEIGHT);
        this.setResizable(false);
        this.setVisible(true);

        ImageIcon logo = new ImageIcon(Images.imagesArray[Images.LOGO]);
        this.setIconImage(logo.getImage());

    }

    //starts the game (called in the main)
    public void startGame(){
        ILogic.getILogic().startGame();
    }

    //changes the visibility of panels
    public void flip(String g1){
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, g1);
        panelMap.get(g1).grabFocus();
    }

    //start settings
    @Override
    public String getPlayerName() {
        return startMenuLayer.getPlayerName();
    }

    @Override
    public String getDifficulty() {
        return startMenuLayer.getDifficulty(); //0 easy, 1 medium, 2 hard
    }

    @Override
    public String getLevel() {
        return startMenuLayer.getLevel(); //1, 2, 3, 4, 5
    }

    @Override
    public String getSoundState() {
        return startMenuLayer.getSoundState(); //On, Off
    }

    @Override
    public String getBirdColour() {
        return startMenuLayer.getBirdColour(); //red, yellow, blue, white
    }

    protected String getPigColour() {
        return startMenuLayer.getPigColour(); //green, pink, blue, yellow
    }

    //repaint called in logic
    @Override
    public void updateView(){
        repaint();
    }

    //start position of flying bird
    @Override
    public int getXReleased() {
        return canvaLayer.getXReleased();
    }

    @Override
    public int getYReleased() {
        return canvaLayer.getYReleased();
    }

    //explain how the game works
    protected void resetTutorial(){
        canvaLayer.resetTutorial();
    }

    //create a single istance of MainGUI
    public static MainGUI getInstance() {
        if(instance==null)
            instance=new MainGUI();        
        return instance;
    }
}