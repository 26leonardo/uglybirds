package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
//import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canva extends JPanel{

    public final static int BIRD_SIZE = 50;
    Image redBird;
    //Random rand;

    public Canva(){
        super();
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH, MainGUI.HEIGHT);
        //this.rand = new Random();
        try {
            this.redBird = ImageIO.read(new File("../media/img/red-bird-50.png")).getScaledInstance(BIRD_SIZE, BIRD_SIZE, Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
    //FINO A QUI SEMPRE COSì
        g2.drawImage(redBird, 50, 550, null);
    }
}