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
import utils.ClipManager;

public class EndMenu extends JPanel implements ActionListener, KeyListener{

    private static final int ONE_STAR = 500;
    private static final int TWO_STAR = 1000;
    private static final int THREE_STAR = 1500; 
    private static final int SCORE_FONT_SCALE = 3;

    private JButton buttonBackToStartMenu; 
    private JButton buttonToCancelRankings;
    private Image wallpaper;
    private LocalTime time = LocalTime.now();
    private int maxLenghtName=0;
    
    public EndMenu(){
        super();
        this.setLayout(null);
        this.setPreferredSize(new Dimension(IView.WIDTH, IView.HEIGHT));
        this.setSize(IView.WIDTH,IView.HEIGHT);

        this.addKeyListener(this);
        this.setFocusable(true);

        buttonBackToStartMenu = new JButton("Go back to the Start Menu");   
        buttonBackToStartMenu.setFocusable(false); 
        buttonBackToStartMenu.setFont(MainGUI.ANGRY_BIRDS_FONT);
        buttonBackToStartMenu.setBounds(800, 575, 230, 40);
        buttonBackToStartMenu.addActionListener(this);
        this.add(buttonBackToStartMenu);

        buttonToCancelRankings = new JButton("Delete rankings for this level.");   
        buttonToCancelRankings.setFocusable(false); 
        buttonToCancelRankings.setFont(MainGUI.ANGRY_BIRDS_FONT);
        buttonToCancelRankings.setBounds(60, 575, 250, 40);
        buttonToCancelRankings.addActionListener(this);
        this.add(buttonToCancelRankings);
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
        
            //ranking printing
            g2.setFont(angryBirdsFont.deriveFont((float)(angryBirdsFont.getSize()*2)));
            metrics=g2.getFontMetrics();
            g2.drawString("Ranking level " + ILogic.getILogic().getLevel() + ":", 200, 380);

            if(ILogic.getILogic().getScoreRankings()[0]!=0){

                if(metrics.stringWidth(ILogic.getILogic().getNameRankings()[0])>=metrics.stringWidth(ILogic.getILogic().getNameRankings()[1])){
                    if(metrics.stringWidth(ILogic.getILogic().getNameRankings()[0])>=metrics.stringWidth(ILogic.getILogic().getNameRankings()[2])){
                        maxLenghtName=metrics.stringWidth(ILogic.getILogic().getNameRankings()[0]);
                    }else{
                        maxLenghtName=metrics.stringWidth(ILogic.getILogic().getNameRankings()[2]);
                    }
                }else{
                    if(metrics.stringWidth(ILogic.getILogic().getNameRankings()[1])>=metrics.stringWidth(ILogic.getILogic().getNameRankings()[2])){
                        maxLenghtName=metrics.stringWidth(ILogic.getILogic().getNameRankings()[1]);
                    }else{
                        maxLenghtName=metrics.stringWidth(ILogic.getILogic().getNameRankings()[2]);
                    }
                }

                for(int i=0; i<3; i++){
                    if(ILogic.getILogic().getScoreRankings()[i]!=0){
                        g2.drawString((i+1) + "  " + ILogic.getILogic().getNameRankings()[i], 200, 430+i*50); 
                        g2.drawString("" + ILogic.getILogic().getScoreRankings()[i], (200 + metrics.stringWidth("  ")+ maxLenghtName + 40 ), 430+i*50);
                        g2.drawString("" + ILogic.getILogic().getDiffRankings()[i], (200+ metrics.stringWidth("" + ILogic.getILogic().getScoreRankings()[i])+ metrics.stringWidth("  ")+ maxLenghtName + 200 ), 430+i*50);
                        if(ILogic.getILogic().getBirdRankings()[i].equals("red")){
                            g2.drawImage(Images.imagesArray[Images.RED_BIRD].getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING),  200+ metrics.stringWidth("" + ILogic.getILogic().getScoreRankings()[i])+ metrics.stringWidth("  ")+ maxLenghtName + metrics.stringWidth(ILogic.getILogic().getDiffRankings()[i])+220, 395+i*50, null);
                        }else if(ILogic.getILogic().getBirdRankings()[i].equals("yellow")){
                            g2.drawImage(Images.imagesArray[Images.YELLOW_BIRD].getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING), 200+ metrics.stringWidth("" + ILogic.getILogic().getScoreRankings()[i])+ metrics.stringWidth("  ")+ maxLenghtName + metrics.stringWidth(ILogic.getILogic().getDiffRankings()[i])+220, 395+i*50, null);
                        }
                        else if(ILogic.getILogic().getBirdRankings()[i].equals("blue")){
                            g2.drawImage(Images.imagesArray[Images.BLUE_BIRD].getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING), 200+ metrics.stringWidth("" + ILogic.getILogic().getScoreRankings()[i])+ metrics.stringWidth("  ")+ maxLenghtName + metrics.stringWidth(ILogic.getILogic().getDiffRankings()[i])+220, 395+i*50, null);
                        }
                        else{
                            g2.drawImage(Images.imagesArray[Images.WHITE_BIRD].getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING), 200+ metrics.stringWidth("" + ILogic.getILogic().getScoreRankings()[i])+ metrics.stringWidth("  ")+ maxLenghtName + metrics.stringWidth(ILogic.getILogic().getDiffRankings()[i])+220,395+i*50, null);
                        }

                        if(ILogic.getILogic().getScoreRankings()[i]>=ONE_STAR){
                                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR].getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING), (200 + metrics.stringWidth("  ")+ maxLenghtName + 40  + metrics.stringWidth(""+ ILogic.getILogic().getScoreRankings()[i]) + 20),  395+i*50, null);
                            if(ILogic.getILogic().getScoreRankings()[i]>=TWO_STAR){
                                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR].getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING), (200 + metrics.stringWidth("  ")+ maxLenghtName + 40  + metrics.stringWidth(""+ ILogic.getILogic().getScoreRankings()[i]) + 20 + 40),  395+i*50, null);
                            if(ILogic.getILogic().getScoreRankings()[i]>=THREE_STAR)
                                g2.drawImage(Images.imagesArray[Images.YELLOW_STAR].getScaledInstance(40, 40, Image.SCALE_AREA_AVERAGING), (200 + metrics.stringWidth("  ")+ maxLenghtName + 40  + metrics.stringWidth(""+ ILogic.getILogic().getScoreRankings()[i]) + 20 + 40 + 40),  395+i*50, null);
                            }
                        }
                    }
                }
            }else{
                g2.drawString("Classification is empty.", 200, 430);
            }
            this.buttonToCancelRankings.setVisible(true);
        }else{
            g2.drawImage(Images.imagesArray[Images.GAME_OVER], 40, 130, null);
            this.buttonToCancelRankings.setVisible(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonBackToStartMenu){
            ILogic.getILogic().restartSettings();
            MainGUI.getInstance().flip("START_MENU");   
            ClipManager.getInstance().stop(ClipManager.GAME_OVER_CLIP);
            ClipManager.getInstance().stop(ClipManager.WIN_CLIP);
            ClipManager.getInstance().loop(ClipManager.ELEVATOR_CLIP);
        }
        if(e.getSource()==buttonToCancelRankings){
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
