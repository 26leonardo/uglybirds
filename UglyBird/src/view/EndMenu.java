package view;

//import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.time.LocalTime;

import javax.swing.JButton;
import javax.swing.JPanel;

import logic.ILogic;

public class EndMenu extends JPanel implements ActionListener, KeyListener{

    private static final int ONE_STAR = 500;
    private static final int TWO_STAR = 1000;
    private static final int THREE_STAR = 1500; 
    private static final int SCORE_FONT_SCALE = 3;

    JButton buttonBackToStartMenu; 
    Image wallpaper;
    LocalTime time = LocalTime.now();
    
    EndMenu(){
        super();
        this.setLayout(null);
        //this.setBackground(new Color(255, 204, 153));
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH,MainGUI.HEIGHT);

        this.addKeyListener(this);
        this.setFocusable(true);

        buttonBackToStartMenu = new JButton("Go back to the Start Menu");   
        buttonBackToStartMenu.setPreferredSize(new Dimension( 250, 40));
        buttonBackToStartMenu.setFocusable(false); 
        buttonBackToStartMenu.setFont(MainGUI.ANGRY_BIRDS_FONT);
        buttonBackToStartMenu.setBounds(800, 575, 230, 40);
        buttonBackToStartMenu.addActionListener(this);
        buttonBackToStartMenu.setMnemonic(KeyEvent.VK_ENTER);
        //alternativa a implementare il metodo action performed: buttonBackToStartMenu.addActionListener(e -> System.out.println("Button pressed"));
        this.add(buttonBackToStartMenu);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 

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
            
        g2.drawImage(this.wallpaper, 0, 0, null);

        if(ILogic.getILogic().getWin()){
//TO DO: stampa classifica 
            if(ILogic.getILogic().getScore()<ONE_STAR) {
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 200, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 450, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 700, 50, null); 
            } else if(ILogic.getILogic().getScore()<TWO_STAR){
                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR], 200, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 450, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 700, 50, null);
            } else if(ILogic.getILogic().getScore()<THREE_STAR){
                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR], 200, 50, null);
                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR], 450, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 700, 50, null);
            } else {
                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR], 200, 50, null);
                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR], 450, 50, null);
                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR], 700, 50, null);
            }
            Font angryBirdsFont=MainGUI.ANGRY_BIRDS_FONT;
            g.setFont(angryBirdsFont.deriveFont((float)(angryBirdsFont.getSize()*SCORE_FONT_SCALE)));//cast esplicito per rendere float
            FontMetrics metrics = g2.getFontMetrics();
            g2.drawString("SCORE: " + ILogic.getILogic().getScore(), (MainGUI.WIDTH - metrics.stringWidth(("SCORE: " + ILogic.getILogic().getScore())))/2, 325);
        }else{
            g2.drawImage(Images.imagesArray[Images.GAME_OVER], 40, 130, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonBackToStartMenu){
            MainGUI.getInstance().flip("START_MENU");
            ILogic.getILogic().restartSettings();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
           buttonBackToStartMenu.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
