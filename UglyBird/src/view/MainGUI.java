package view;

//import java.awt.Dimension;
//import java.awt.image.BufferedImage;
//import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Font;

import utils.FontReader;

import javax.swing.ImageIcon;

public class MainGUI extends JFrame{

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    public static int SCORE = 1000;
    public static boolean START_GAME = false;
    public static boolean END_GAME = false;
    public static boolean START_MENU = false;
    public static final Font ANGRY_BIRDS_FONT = FontReader.readFont("angry-birds");
    
    public MainGUI() {
		super("Ugly Birds");
        this.createGUI();
    }

    private void createGUI() {
       
        StartMenu startMenuLayer = new StartMenu();

        Canva canvaLayer = new Canva();

        Background backgroundLayer = new Background();  

        EndMenu endMenuLayer = new EndMenu();

    //JFrame layout 
        this.add(startMenuLayer);

//andrà messo sulla parte logica (tutto dentro un while(true)), ora è per provare, però in qualche modo andrà anche aggiunto tutto nel costruttore, vediamo poi come
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setVisible(true);

        ImageIcon logo = new ImageIcon("../media/img/logo.png");
        this.setIconImage(logo.getImage());

        if(START_GAME){
            this.remove(startMenuLayer);
            this.add(canvaLayer);
            this.add(backgroundLayer);
            if(END_GAME){
                this.remove(canvaLayer);
                this.remove(backgroundLayer);
                this.add(endMenuLayer);
                if(START_MENU){
                    this.remove(endMenuLayer);
                    this.add(startMenuLayer);
                    START_GAME = false;
                    END_GAME = false;
                    START_MENU = false;
                }
            }
        }
    }


    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
}



/*
import java.awt.Dimension;
//import java.awt.image.BufferedImage;
//import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
//import javax.swing.Timer;
import javax.swing.ImageIcon;

public class MainGUI extends JFrame{

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 700;
    public static int SCORE = 0;
    public static boolean START_MENU_VISIBLE = false;
    public static boolean END_MENU_VISIBLE = true; 
    public static boolean BACKGROUND_VISIBLE = true;

    public MainGUI() {
		super("Ugly Birds");
        this.createGUI();
    }

    private void createGUI() {
       
        StartMenu startMenuLayer = new StartMenu();

        Canva canvaLayer = new Canva();

        Background backgroundLayer = new Background();  

        EndMenu endMenuLayer = new EndMenu();
        
    //JLayeredPane layout
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        layeredPane.add(startMenuLayer, Integer.valueOf(3)); 
        layeredPane.add(endMenuLayer, Integer.valueOf(2));
        layeredPane.add(canvaLayer, Integer.valueOf(1));
        layeredPane.add(backgroundLayer, Integer.valueOf(0)); 

    //JFrame layout 
        this.add(layeredPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setVisible(true);
       
        ImageIcon logo = new ImageIcon("../html/img/logo.png");
		this.setIconImage(logo.getImage());

        /*Timer timer=new Timer(500, (e) ->{
            canvaLayer.repaint(); 
        });
        javax.swing.SwingWorker<Void,Void> worker=new javax.swing.SwingWorker<Void,Void>(){
            @Override
            public Void doInBackground(){
                timer.start();
                return null;
            }
        };
        worker.execute(); //commento timer doveva essere fin qui
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainGUI();
            }
        });
    }
} */