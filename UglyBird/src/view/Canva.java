package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.ILogic;
import utils.ClipManager;

public class Canva extends JPanel implements MouseListener, MouseMotionListener{

    private static final int EXPLOSION_RATE=125/(ILogic.REFRESH_TIME);

    private JLabel score; 
    private LocalTime time = LocalTime.now();
    private Image wallpaper;
    private Image bird;
    private Image pig;
    private Image block;
    private int[] trajectoryX;
    private int[] trajectoryY;
    private int xReleased; 
    private int yReleased;
    private boolean tutorialOn=true;
    private boolean trajectoryIsVisible=false;
    private int explosionRefreshCounter=0;

    public Canva(){
        super();
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(IView.WIDTH, IView.HEIGHT));
        this.setSize(IView.WIDTH, IView.HEIGHT);
        this.setLayout(null);

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        trajectoryX = new int[IView.NUMBER_OF_SAMPLES];
        trajectoryY = new int[IView.NUMBER_OF_SAMPLES];
        for(int i=0; i<trajectoryX.length; i++){
            trajectoryX[i]=0;
            trajectoryY[i]=0;
        }

        score = new JLabel("Score: "); 
        score.setFont(MainGUI.ANGRY_BIRDS_FONT.deriveFont(35f));
        score.setBounds(850, 20, 300, 50);
        this.add(score);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 

        this.drawBackground(g2); 

        if(tutorialOn){
            g2.setFont(MainGUI.ANGRY_BIRDS_FONT.deriveFont(50f));
            g2.drawString("Pull the bird! Defeat them all!", 100, 180);
            g2.setFont(MainGUI.ANGRY_BIRDS_FONT.deriveFont(40f)); 
            g2.drawString("The more you break, the higher the score will be!", 180, 230);
        }

        this.drawPig(MainGUI.getInstance().getPigColour(), g2);
        this.drawBlocks(g2);
        this.drawBird(MainGUI.getInstance().getBirdColour(), g2);
        
        if(ILogic.getILogic().getDifficulty()!=2){      //change visibility of trajectory depending on the difficulty choose
            if(ILogic.getILogic().getDifficulty()==0){
                if(trajectoryIsVisible){
                    this.drawTrajectory(g2);
                }
            }else{
                if(trajectoryIsVisible){
                    this.drawHalfTrajectory(g2);
                }
            }
        }
        
        if(ILogic.getILogic().getIsExploding()){ 
            if(ILogic.getILogic().getExplosionPoint2X()!=0){   
                this.drawDoubleExplosion(ILogic.getILogic().getExplosionPoint1X(), ILogic.getILogic().getExplosionPoint1Y(), 
                    ILogic.getILogic().getExplosionPoint2X(), ILogic.getILogic().getExplosionPoint2Y(), g2);
            }else{
                this.drawExplosion(ILogic.getILogic().getExplosionPoint1X(), ILogic.getILogic().getExplosionPoint1Y(), g2); 
            }
        }

        this.score.setText("Score: " + ILogic.getILogic().getScore());
    }

    private void drawBackground(Graphics2D g2){
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
        g2.drawImage(Images.imagesArray[Images.CATAPULT], MainGUI.CATAPULT_POSITION_X, MainGUI.CATAPULT_POSITION_Y, null);
    }

    private void drawPig(String s, Graphics2D g2){
        if(s == "green")
            this.pig = Images.imagesArray[Images.GREEN_PIG];
        else if(s == "pink")
            this.pig = Images.imagesArray[Images.PINK_PIG];
        else if(s == "blue")
            this.pig = Images.imagesArray[Images.BLUE_PIG];
        else if(s == "yellow")
            this.pig = Images.imagesArray[Images.YELLOW_PIG];
    
        for(int i=0; i<ILogic.getILogic().getNumberOfPigs(ILogic.getILogic().getLevel()); i++){
            if(ILogic.getILogic().getPigsX()[i]!=0){
                g2.drawImage(this.pig, ILogic.getILogic().getPigsX()[i],         
                    ILogic.getILogic().getPigsY()[i], null);      
            }
        }
    }

    private void drawBlocks(Graphics2D g2){  
        if(ILogic.getILogic().getDifficulty()==0)
            this.block = Images.imagesArray[Images.GLASS_BLOCK];
        else if(ILogic.getILogic().getDifficulty()==1)
            this.block = Images.imagesArray[Images.WOOD_BLOCK];
        else if(ILogic.getILogic().getDifficulty()==2)
            this.block = Images.imagesArray[Images.METAL_BLOCK];
        
        for(int i=0; i<ILogic.getILogic().getNumberOfBlocks(ILogic.getILogic().getLevel()); i++){
            if(ILogic.getILogic().getBlocksX()[i]!=0){
                g2.drawImage(block, 
                    ILogic.getILogic().getBlocksX()[i], 
                        ILogic.getILogic().getBlocksY()[i], null);     
            } 
        }
    }

    private void drawBird(String s, Graphics2D g2){    
        if(s == "red")
            this.bird = Images.imagesArray[Images.RED_BIRD];
        else if(s == "yellow")
            this.bird = Images.imagesArray[Images.YELLOW_BIRD];
        else if(s == "blue")
            this.bird = Images.imagesArray[Images.BLUE_BIRD];
        else if(s == "white")
            this.bird = Images.imagesArray[Images.WHITE_BIRD];

        if(ILogic.getILogic().getNumAliveBirds()!=0){
            for(int i=0; i<ILogic.getILogic().getNumAliveBirds(); i++){ 
                g2.drawImage(this.bird, ILogic.getILogic().getBirdsPositionsX(ILogic.getILogic().getLevel())[i], 
                    ILogic.getILogic().getBirdsPositionsY(ILogic.getILogic().getLevel())[i], null);     
            }
        }
    }

    private void drawExplosion(int px, int py, Graphics2D g2){
        int x = px-(MainGUI.EXPLOSION_SIZE-IView.BLOCK_SIZE)/2;  //to center the explosion
        int y = py-(MainGUI.EXPLOSION_SIZE-IView.BLOCK_SIZE)/2;

        switch(explosionRefreshCounter/EXPLOSION_RATE){     //every 41 times it changes the image seen (we've replicated a gif manually)
            case 0:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_1], x, y, null);
            break;
            case 1:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_2], x, y, null);
            break;
            case 2:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_3], x, y, null);
            break;
            case 3:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_4], x, y, null);
            break;
            case 4:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_5], x, y, null);
            break;
            case 5:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_6], x, y, null);
            break;
            case 6:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_7], x, y, null);
            break;
        }
        explosionRefreshCounter=(explosionRefreshCounter+1)%(EXPLOSION_RATE*7);
        if(explosionRefreshCounter==0){
            ILogic.getILogic().setIsExploding(false);
            ILogic.getILogic().resetExplosionPoints();
        } 
    }

    private void drawDoubleExplosion(int p1x, int p1y, int p2x, int p2y, Graphics2D g2){     //draws a centered explosion between two items
        int x1 = (p1x+p2x)/2-(MainGUI.EXPLOSION_SIZE-IView.BLOCK_SIZE)/2;  
        int y1 = (p1y+p2y)/2-(MainGUI.EXPLOSION_SIZE-IView.BLOCK_SIZE)/2;    

        switch(explosionRefreshCounter/EXPLOSION_RATE){     
            case 0:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_1], x1, y1, null);
            break;
            case 1:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_2], x1, y1, null);
            break;
            case 2:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_3], x1, y1, null);
            break;
            case 3:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_4], x1, y1, null);
            break;
            case 4:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_5], x1, y1, null);
            break;
            case 5:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_6], x1, y1, null);
            break;
            case 6:
                g2.drawImage(Images.imagesArray[Images.EXPLOSION_7], x1, y1, null);
            break;
        }
    
        explosionRefreshCounter=(explosionRefreshCounter+1)%(EXPLOSION_RATE*7);
        if(explosionRefreshCounter==0){
            ILogic.getILogic().setIsExploding(false);
            ILogic.getILogic().resetExplosionPoints();
        } 
    }

    private void drawTrajectory(Graphics2D g2){
        g2.drawPolyline(trajectoryX, trajectoryY, trajectoryX.length);  
    }

    private void drawHalfTrajectory(Graphics2D g2){
        g2.drawPolyline(trajectoryX, trajectoryY, trajectoryX.length/2);  
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {       
        if(!(ILogic.getILogic().getIsFlying()) && !(ILogic.getILogic().getIsExploding())){
            if((e.getX()<IView.CATAPULT_CENTER_X) && (e.getY()>IView.CATAPULT_CENTER_Y)){
                xReleased = e.getX();
                yReleased = e.getY();    
                trajectoryIsVisible=false;  
                ILogic.getILogic().setBirdIsFlying(true);   
                if(MainGUI.getInstance().getBirdColour()=="red"){    
                    ClipManager.getInstance().play(ClipManager.UI_CLIP);
                }else if(MainGUI.getInstance().getBirdColour()=="yellow"){
                    ClipManager.getInstance().play(ClipManager.ROOSTER_CLIP);
                }else if(MainGUI.getInstance().getBirdColour()=="blue"){
                    ClipManager.getInstance().play(ClipManager.DOLPHIN_CLIP);
                }else if(MainGUI.getInstance().getBirdColour()=="white"){
                    ClipManager.getInstance().play(ClipManager.POW_CLIP);
                }
            }
        }
    } 

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        tutorialOn=false;
        if(!(ILogic.getILogic().getIsFlying())){
            if((e.getX()<IView.CATAPULT_CENTER_X) && (e.getY()>IView.CATAPULT_CENTER_Y)){
                trajectoryIsVisible=true;    
                trajectoryX = ILogic.getILogic().calculateTrajectoryX();
                trajectoryY = ILogic.getILogic().calculateTrajectoryY(e.getX(), e.getY());  
            } else {
                trajectoryIsVisible=false;    
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){}

    protected int getXReleased(){     
        return xReleased;
    }

    protected int getYReleased(){
        return yReleased;
    }

    protected void resetTutorial(){
        tutorialOn=true;
    }
}