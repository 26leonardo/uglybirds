package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JButton;
import javax.swing.JPanel;

public class EndMenu extends JPanel implements ActionListener {

    private static boolean LIVELLO_SUPERATO = true; //poi toglilo
    private static final int ONE_STAR = 500;
    private static final int TWO_STAR = 1000;
    private static final int THREE_STAR = 2000;
    private static final int SCORE_FONT_SCALE = 3;

    JButton buttonBackToStartMenu; 
    
    EndMenu(){
        super();
        this.setLayout(null);
        this.setBackground(new Color(255, 204, 153));
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH,MainGUI.HEIGHT);

        buttonBackToStartMenu = new JButton("Go back to the Start Menu");   
        buttonBackToStartMenu.setPreferredSize(new Dimension( 250, 40));
        buttonBackToStartMenu.setFocusable(false); 
        buttonBackToStartMenu.setFont(MainGUI.ANGRY_BIRDS_FONT);
        buttonBackToStartMenu.setBounds(800, 575, 230, 40);
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
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 200, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 450, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 700, 50, null); 
            } else if(MainGUI.SCORE<TWO_STAR){
                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR], 200, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 450, 50, null);
                g2.drawImage(Images.imagesArray[Images.BLACK_STAR], 700, 50, null);
            } else if(MainGUI.SCORE<THREE_STAR){
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
            g2.drawString("SCORE: " + MainGUI.SCORE, (MainGUI.WIDTH - metrics.stringWidth(("SCORE: " + MainGUI.SCORE)))/2, 325);
        }else{
            g2.drawImage(Images.imagesArray[Images.GAME_OVER], 40, 130, null);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonBackToStartMenu){
//poi togli la stampa di prova
            System.out.println("Restarted"); 
            MainGUI.getInstance().flip("START_MENU");
        }
    }
}
