package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.ILogic;

import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Canva extends JPanel implements ActionListener{

    JButton skipButton;
    JLabel score;
    Image wallpaper;
    Image pig;
    LocalTime time = LocalTime.now();
    ILogic iLogic;
    
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
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==skipButton){  
            MainGUI.getInstance().flip("END_MENU");
        }
    }

    public void setILogic(ILogic iLogic){
        this.iLogic = iLogic;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
    //FINO A QUI SEMPRE COSì
        g2.drawImage(wallpaper, 0, 0, null);
        g2.drawImage(Images.imagesArray[Images.RED_BIRD], 50, 550, null);

        if(MainGUI.getInstance().getPigColour() == "green")
            this.pig = Images.imagesArray[Images.GREEN_PIG];
        else if(MainGUI.getInstance().getPigColour() == "pink")
            this.pig = Images.imagesArray[Images.PINK_PIG];
        else if(MainGUI.getInstance().getPigColour() == "blue")
            this.pig = Images.imagesArray[Images.BLUE_PIG];
        else if(MainGUI.getInstance().getPigColour() == "yellow")
            this.pig = Images.imagesArray[Images.YELLOW_PIG];
        g2.drawImage(pig, 680, 530, null);
        g2.drawImage(pig, 830, 530, null);
        g2.drawImage(pig, 980, 530, null);
    }
}