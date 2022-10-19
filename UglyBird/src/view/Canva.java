package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.lang.Thread;

import logic.ILogic;

import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canva extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

    JButton skipButton;
    JButton explosionButton;
    JLabel score;       //renderla visibile dopo repaint
    Image wallpaper;
    Image pig;
    Image block;
    LocalTime time = LocalTime.now();
    int[] trajectoryX;
    int[] trajectoryY;
    int xReleased; //utili?
    int yReleased; //utili?
    int xBird;
    int yBird;

    public Canva(){
        super();
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH, MainGUI.HEIGHT);
        this.setLayout(null);

        xBird=MainGUI.CATAPULT_CENTER_X-MainGUI.BIRD_SIZE/2;
        yBird=MainGUI.CATAPULT_CENTER_Y-MainGUI.BIRD_SIZE/2;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        trajectoryX = new int[MainGUI.NUMBER_OF_SAMPLES];
        trajectoryY = new int[MainGUI.NUMBER_OF_SAMPLES];
        for(int i=0; i<MainGUI.NUMBER_OF_SAMPLES; i++){
            trajectoryX[i]=0;
            trajectoryY[i]=0;
        }

        skipButton = new JButton("Skip level");
        skipButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        skipButton.addActionListener(this);
        skipButton.setBounds(900, 610, 100, 30);
        this.add(skipButton);

        score = new JLabel("Score: 00000");
        score.setFont(MainGUI.ANGRY_BIRDS_FONT.deriveFont(35f));
        score.setBounds(850, 20, 300, 50);
        this.add(score);

        explosionButton = new JButton("Explode");
        explosionButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        explosionButton.addActionListener(this);
        explosionButton.setBounds(900, 410, 100, 30);
        this.add(explosionButton);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==skipButton){  
            MainGUI.getInstance().flip("END_MENU");
        }
        if(e.getSource()==explosionButton){
            this.drawExplosion((Graphics2D)getGraphics());
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 

        this.drawBackground(g2); 
        //senza prox riga non si muove uccellino
        g2.drawImage(Images.imagesArray[Images.RED_BIRD], xBird, yBird, null);
        this.drawPig(MainGUI.getInstance().getPigColour(), g2);
        this.drawBlocks(g2);
        this.drawBird(g2);

        this.drawTrajectory(g2);
    }

    public void drawBackground(Graphics2D g2){
        if(time.isAfter(LocalTime.MIDNIGHT) && time.isBefore(LocalTime.of(6, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.NIGHT];
        else if(time.isAfter(LocalTime.of(6, 0, 0))&& time.isBefore(LocalTime.of(10, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.SUNRISE];
        else if(time.isAfter(LocalTime.of(10, 0, 0))&& time.isBefore(LocalTime.of(18, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.DAY];
        else if(time.isAfter(LocalTime.of(18, 0, 0))&& time.isBefore(LocalTime.of(21, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.DUSK];
        else if(time.isAfter(LocalTime.of(21, 0, 0))&& time.isBefore(LocalTime.MAX))
            this.wallpaper = Images.imagesArray[Images.SUNSET];  
            
        super.paintComponent(g2);
        g2.drawImage(this.wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.CATAPULT], MainGUI.CATAPULT_POSITION_X, MainGUI.CATAPULT_POSITION_Y, null);
    }

    public void drawPig(String s, Graphics2D g2){
        if(s == "green")
            this.pig = Images.imagesArray[Images.GREEN_PIG];
        else if(s == "pink")
            this.pig = Images.imagesArray[Images.PINK_PIG];
        else if(s == "blue")
            this.pig = Images.imagesArray[Images.BLUE_PIG];
        else if(s == "yellow")
            this.pig = Images.imagesArray[Images.YELLOW_PIG];
            
        super.paintComponent(g2);
        for(int i=0; i<ILogic.getILogic().getNumberOfPigs(ILogic.getILogic().getLevel()); i++){
            g2.drawImage(this.pig, ILogic.getILogic().getPigsPositions(ILogic.getILogic().getLevel())[i].x, 
                ILogic.getILogic().getPigsPositions(ILogic.getILogic().getLevel())[i].y, null);      //metti posizioni in array diversi in base al livello, cicla for con una variabile numer of pigs, dipendente dal livello
        }
    }

    public void drawBlocks(Graphics2D g2){  //mischia i vari blocchi
        super.paintComponent(g2);
        if(ILogic.getILogic().getDifficulty()==0)
            this.block = Images.imagesArray[Images.GLASS_BLOCK];
        else if(ILogic.getILogic().getDifficulty()==1)
            this.block = Images.imagesArray[Images.WOOD_BLOCK];
        else if(ILogic.getILogic().getDifficulty()==2)
            this.block = Images.imagesArray[Images.METAL_BLOCK];
        
        for(int i=0; i<ILogic.getILogic().getNumberOfBlocks(ILogic.getILogic().getLevel()); i++){
            g2.drawImage(block, 
                ILogic.getILogic().getBlocksPositions(ILogic.getILogic().getLevel())[i].x, 
                    ILogic.getILogic().getBlocksPositions(ILogic.getILogic().getLevel())[i].y, null);      
        }
    }

    public void drawBird(Graphics2D g2){
        super.paintComponent(g2);
        for(int i=0; i<ILogic.getILogic().getNumberOfBirds(ILogic.getILogic().getLevel()); i++){
            g2.drawImage(Images.imagesArray[Images.RED_BIRD], ILogic.getILogic().getBirdsPositions(ILogic.getILogic().getLevel())[i].x, 
                ILogic.getILogic().getBirdsPositions(ILogic.getILogic().getLevel())[i].y, null);      //metti posizioni in array diversi in base al livello, cicla for con una variabile numer of birds, dipendente dal livello
        }
    }

    public void drawExplosion(Graphics2D g2){
        super.paintComponent(g2);

        int width=Images.imagesArray[Images.EXPLOSION_1].getWidth(null)+10;
        int height=Images.imagesArray[Images.EXPLOSION_1].getHeight(null)+10;
        
        g2.setClip(650, 510, width, height);

        g2.drawImage(Images.imagesArray[Images.EXPLOSION_1], 650, 510, null);
        try{Thread.sleep(125);}catch(InterruptedException e){System.out.println(e);}
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.EXPLOSION_2], 650, 510, null);
        try{Thread.sleep(125);}catch(InterruptedException e){System.out.println(e);}
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.EXPLOSION_3], 650, 510, null);
        try{Thread.sleep(125);}catch(InterruptedException e){System.out.println(e);}
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.EXPLOSION_4], 650, 510, null);
        try{Thread.sleep(125);}catch(InterruptedException e){System.out.println(e);}
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.EXPLOSION_5], 650, 510, null);
        try{Thread.sleep(125);}catch(InterruptedException e){System.out.println(e);}
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.EXPLOSION_6], 650, 510, null);
        try{Thread.sleep(125);}catch(InterruptedException e){System.out.println(e);}
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.EXPLOSION_7], 650, 510, null);
        try{Thread.sleep(125);}catch(InterruptedException e){System.out.println(e);}
        g2.drawImage(wallpaper, 0, 0, null);
    }

    public void drawTrajectory(Graphics2D g2){
        super.paintComponent(g2);
        g2.drawPolyline(trajectoryX, trajectoryY, trajectoryX.length);        
    }

    public void drawFlyingBird(Graphics2D g2){      //poi cancella uccellino, o quando tocca maiale o quando esce, non renderlo rilanciabile
        super.paintComponent(g2);
        for(int i=0; i<MainGUI.NUMBER_OF_SAMPLES; i++){
            xBird=trajectoryX[i]-MainGUI.BIRD_SIZE/2;
            yBird=trajectoryY[i]-MainGUI.BIRD_SIZE/2;
            try {Thread.sleep(3);} catch (InterruptedException e) {e.printStackTrace();}
            paintComponent(g2);                     //repainta senza tremolii
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {       //fa partire uccellino //cancella la traiettoria dopo il rilascio
        if((e.getX()<MainGUI.CATAPULT_CENTER_X) && (e.getY()>MainGUI.CATAPULT_CENTER_Y)){
            System.out.println("mouse released");
            xReleased = e.getX();
            yReleased = e.getY();
            drawFlyingBird((Graphics2D) getGraphics());
        }
    } 

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if((e.getX()<MainGUI.CATAPULT_CENTER_X) && (e.getY()>MainGUI.CATAPULT_CENTER_Y)){
            repaint();      
            trajectoryX = ILogic.getILogic().calculateTrajectoryX();
            trajectoryY = ILogic.getILogic().calculateTrajectoryY(e.getX(), e.getY());  
        } 
    }

    @Override
    public void mouseMoved(MouseEvent e){}

    public int getXReleased(){      //usalo per calcolare traiettoria in logic e vedere se incappa in altri oggetti
        return xReleased;
    }

    public int getYReleased(){
        return yReleased;
    }
}