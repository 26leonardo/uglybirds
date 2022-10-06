package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
//import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

//import utils.FontReader;

public class EndMenu extends JPanel implements ActionListener {

    private static boolean LIVELLO_SUPERATO = true;
    private static final int ONE_STAR = 500;
    private static final int TWO_STAR = 1000;
    private static final int THREE_STAR = 2000;
    private static final int SCORE_FONT_SCALE = 3;

    Image star;
    Image blackStar;
    Image gameOver;
    JButton buttonBackToStartMenu; 
    
    EndMenu(){
        super();
        this.setLayout(null);
        //this.setOpaque(MainGUI.END_MENU_VISIBLE);
        this.setBackground(new Color(255, 204, 153));
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH,MainGUI.HEIGHT);

        try {
            this.star = ImageIO.read(new File("../media/img/star.png")).getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.blackStar = ImageIO.read(new File("../media/img/black-star.png")).getScaledInstance(200, 200, Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            this.gameOver = ImageIO.read(new File("../media/img/game-over.png")).getScaledInstance(1000, 330, Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        buttonBackToStartMenu = new JButton("Go back to the Start Menu");   
        buttonBackToStartMenu.setPreferredSize(new Dimension( 250, 40));
        buttonBackToStartMenu.setFocusable(false); 
        //buttonBackToStartMenu.setFont(FontReader.readFont("angry-birds"));
        buttonBackToStartMenu.setFont(MainGUI.ANGRY_BIRDS_FONT);
        //buttonBackToStartMenu.setBackground(new Color(255, 128, 0));   
        //buttonBackToStartMenu.setBorder(BorderFactory.createEtchedBorder());
        buttonBackToStartMenu.setBounds(800, 575, 230, 40);
        //buttonBackToStartMenu.setForeground(new Color(255, 204, 153));
        buttonBackToStartMenu.addActionListener(this);
        //alternativa a implementare il metodo action performed: buttonBackToStartMenu.addActionListener(e -> System.out.println("Button pressed"));
        this.add(buttonBackToStartMenu);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
    //FINO A QUI SEMPRE COSì
        if(LIVELLO_SUPERATO){
//TO DO: stampa classifica 
            if(MainGUI.SCORE<ONE_STAR) {
                g2.drawImage(blackStar, 200, 50, null);
                g2.drawImage(blackStar, 450, 50, null);
                g2.drawImage(blackStar, 700, 50, null); 
            } else if(MainGUI.SCORE<TWO_STAR){
                g2.drawImage(star, 200, 50, null);
                g2.drawImage(blackStar, 450, 50, null);
                g2.drawImage(blackStar, 700, 50, null);
            } else if(MainGUI.SCORE<THREE_STAR){
                g2.drawImage(star, 200, 50, null);
                g2.drawImage(star, 450, 50, null);
                g2.drawImage(blackStar, 700, 50, null);
            } else {
                g2.drawImage(star, 200, 50, null);
                g2.drawImage(star, 450, 50, null);
                g2.drawImage(star, 700, 50, null);
            }
            Font angryBirdsFont=MainGUI.ANGRY_BIRDS_FONT;
            g.setFont(angryBirdsFont.deriveFont((float)(angryBirdsFont.getSize()*SCORE_FONT_SCALE)));//cast esplicito per rendere float
            FontMetrics metrics = g2.getFontMetrics();
            g2.drawString("SCORE: " + MainGUI.SCORE, (MainGUI.WIDTH - metrics.stringWidth(("SCORE: " + MainGUI.SCORE)))/2, 325);
        }else{
            g2.drawImage(gameOver, 40, 130, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if(e.getSource()==buttonBackToStartMenu){
//poi togli la stampa di prova
            System.out.println("Restarted"); 
            //MainGUI.START_MENU = true;
            //buttonBackToStartMenu.setEnabled(false);
            MainGUI.getInstance().flip("START_MENU");
        }
    }
}
