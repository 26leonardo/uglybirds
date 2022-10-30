package view;

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

    private JButton buttonToCancelReankings;
    private JButton buttonBackToStartMenu; 
    private Image wallpaper;
    private LocalTime time = LocalTime.now();

    
    
    public EndMenu(){
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(IView.WIDTH, IView.HEIGHT));
        this.setSize(IView.WIDTH,IView.HEIGHT);

        this.addKeyListener(this);
        this.setFocusable(true);

        buttonToCancelReankings = new JButton("Delete     Reankings");   
        buttonToCancelReankings.setPreferredSize(new Dimension( 250, 40));
        buttonToCancelReankings.setFocusable(false); 
        buttonToCancelReankings.setFont(MainGUI.ANGRY_BIRDS_FONT);
        buttonToCancelReankings.setBounds(60, 575, 230, 40);
        buttonToCancelReankings.addActionListener(this);
        this.add(buttonToCancelReankings);

        buttonBackToStartMenu = new JButton("Go back to the Start Menu");   
        buttonBackToStartMenu.setPreferredSize(new Dimension( 250, 40));
        buttonBackToStartMenu.setFocusable(false); 
        buttonBackToStartMenu.setFont(MainGUI.ANGRY_BIRDS_FONT);
        buttonBackToStartMenu.setBounds(800, 575, 230, 40);
        buttonBackToStartMenu.addActionListener(this);
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
            g.setFont(angryBirdsFont.deriveFont((float)(angryBirdsFont.getSize()*SCORE_FONT_SCALE)));
            FontMetrics metrics = g2.getFontMetrics();
            g2.drawString("SCORE: " + ILogic.getILogic().getScore(), (IView.WIDTH - metrics.stringWidth(("SCORE: " + ILogic.getILogic().getScore())))/2, 325);
            g2.drawString(" " +ILogic.getILogic().getNameReankings()[0]+ "    "+ILogic.getILogic().getScoreReankings()[0], (IView.WIDTH - metrics.stringWidth(" " +ILogic.getILogic().getNameReankings()[0]+ "    "+ILogic.getILogic().getScoreReankings()[0]))/2, 375);
            g2.drawString(" " +ILogic.getILogic().getNameReankings()[1]+ "    "+ILogic.getILogic().getScoreReankings()[1], (IView.WIDTH - metrics.stringWidth(" " +ILogic.getILogic().getNameReankings()[1]+ "    "+ILogic.getILogic().getScoreReankings()[1]))/2, 425);
            g2.drawString(" " +ILogic.getILogic().getNameReankings()[2]+ "    "+ILogic.getILogic().getScoreReankings()[2], (IView.WIDTH - metrics.stringWidth(" " +ILogic.getILogic().getNameReankings()[2]+ "    "+ILogic.getILogic().getScoreReankings()[2]))/2, 475);
            
            
        }else{
            g2.drawImage(Images.imagesArray[Images.GAME_OVER], 40, 130, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonBackToStartMenu){
            ILogic.getILogic().restartSettings();
            MainGUI.getInstance().flip("START_MENU");   
        }
        if(e.getSource()==buttonToCancelReankings){
            ILogic.getILogic().deleteFile(); 
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
