package view;

import java.awt.CardLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.ImageIcon;

import utils.FontReader;

import logic.ILogic;

public class MainGUI extends JFrame implements IView{
    private static MainGUI instance=null;
    private ILogic iLogic;

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    public final static int BIRD_SIZE = 50;
    public final static int PIG_SIZE = 50;
    public static int SCORE = 0000;
    public static final Font ANGRY_BIRDS_FONT = FontReader.readFont("angry-birds");
    
    JPanel cards;
    StartMenu startMenuLayer;
    Canva canvaLayer;
    EndMenu endMenuLayer;

    public MainGUI() {
		super("Ugly Birds");
        Images.loadImages();
        this.createGUI();
    }

    public void flip(String g1){
        CardLayout cl = (CardLayout) cards.getLayout();
        cl.show(cards, g1);
    }

    private void createGUI() {

        startMenuLayer = new StartMenu();

        canvaLayer = new Canva();

        endMenuLayer = new EndMenu();

        cards = new JPanel();
        cards.setLayout(new CardLayout());
       
        cards.add(startMenuLayer, "START_MENU");
        cards.add(canvaLayer, "CANVA"); 
        cards.add(endMenuLayer, "END_MENU");    

        this.add(cards);

        //Solo qui va aggiunto logic? non posso aggiungerlo direttamente nel costruttore di canva?
        canvaLayer.setILogic(iLogic);

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

    @Override
    public String getPigColour() {
        return startMenuLayer.getPigColour(); //green, pink, blue, yellow
    }

    @Override
    public String getSoundState() {
        return startMenuLayer.getSoundState(); //On, Off
    }
}