package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
//import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canva extends JPanel implements ActionListener{

    public final static int BIRD_SIZE = 50;
    JButton skipButton;
    JLabel score;
    Image redBird;
    Image wallpaper;
    LocalTime time = LocalTime.now();
    
    //Random rand;

    public Canva(){
        super();
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH, MainGUI.HEIGHT);
        this.setLayout(null);
        //this.rand = new Random();

        skipButton = new JButton("Skip level");
        skipButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        skipButton.addActionListener(this);
        skipButton.setBounds(900, 610, 100, 30);
        this.add(skipButton);

        score = new JLabel("Score: 00000");
        score.setFont(MainGUI.ANGRY_BIRDS_FONT.deriveFont(35f));
        score.setBounds(850, 20, 300, 50);
        this.add(score);

        try {
            if(time.isAfter(LocalTime.MIDNIGHT) && time.isBefore(LocalTime.of(6, 0, 0)))
                this.wallpaper = ImageIO.read(new File("../media/img/night.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            else if(time.isAfter(LocalTime.of(6, 0, 0))&& time.isBefore(LocalTime.of(10, 0, 0)))
                this.wallpaper = ImageIO.read(new File("../media/img/sunrise.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            else if(time.isAfter(LocalTime.of(10, 0, 0))&& time.isBefore(LocalTime.of(18, 0, 0)))
                this.wallpaper = ImageIO.read(new File("../media/img/day.png")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            else if(time.isAfter(LocalTime.of(18, 0, 0))&& time.isBefore(LocalTime.of(21, 0, 0)))
                this.wallpaper = ImageIO.read(new File("../media/img/dusk.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
            else if(time.isAfter(LocalTime.of(21, 0, 0))&& time.isBefore(LocalTime.MAX))
                this.wallpaper = ImageIO.read(new File("../media/img/sunset.jpg")).getScaledInstance(MainGUI.WIDTH, MainGUI.HEIGHT, Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }  

        try {
            this.redBird = ImageIO.read(new File("../media/img/red-bird-50.png")).getScaledInstance(BIRD_SIZE, BIRD_SIZE, Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==skipButton){  
            //skipButton.setEnabled(false); 
            MainGUI.getInstance().flip("END_MENU");
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
    //FINO A QUI SEMPRE COSì
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(redBird, 50, 550, null);
    }
}