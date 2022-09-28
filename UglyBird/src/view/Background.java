package view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Background extends JPanel {

    /*
    public final static int NIGHT=0;
    public final static int SUNRISE=1;
    public final static int DAY=2;
    public final static int SUNSET=3;*/

    Image wallpaper;
    LocalTime time = LocalTime.now();
    
    public Background(){
        super();
        //this.setOpaque(MainGUI.BACKGROUND_VISIBLE);
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH,MainGUI.HEIGHT);

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
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
    //FINO A QUI SEMPRE COSì
        g2.drawImage(wallpaper, 0, 0, null);
    }
}
