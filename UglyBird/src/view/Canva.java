package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.ILogic;
//import logic.Logic;

import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Canva extends JPanel implements ActionListener, MouseListener, MouseMotionListener{

    private static final int EXPLOSION_RATE=125/(ILogic.REFRESH_TIME);

    JButton skipButton;
    JLabel score;       
    Image wallpaper;
    Image pig;
    Image block;
    LocalTime time = LocalTime.now();
    int[] trajectoryX;
    int[] trajectoryY;
    int xReleased; 
    int yReleased;
    boolean trajectoryIsVisible;
    int explosionRefreshCounter=0;

    public Canva(){
        super();
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH, MainGUI.HEIGHT);
        this.setLayout(null);

        trajectoryIsVisible=false;

        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        trajectoryX = new int[MainGUI.NUMBER_OF_SAMPLES];
        trajectoryY = new int[MainGUI.NUMBER_OF_SAMPLES];
        for(int i=0; i<trajectoryX.length; i++){
            trajectoryX[i]=0;
            trajectoryY[i]=0;
        }

        skipButton = new JButton("Skip level");
        skipButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        skipButton.addActionListener(this);
        skipButton.setBounds(900, 610, 100, 30);
        this.add(skipButton);

        score = new JLabel("Score: "); //aggiorna score 
        score.setFont(MainGUI.ANGRY_BIRDS_FONT.deriveFont(35f));
        score.setBounds(850, 20, 300, 50);
        this.add(score);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 

        this.drawBackground(g2); 
        this.drawPig(MainGUI.getInstance().getPigColour(), g2);
        this.drawBlocks(g2);
        this.drawBird(g2);
        
        if(ILogic.getILogic().getDifficulty()!=2){      //cambia visuale traiettoria in base al livello
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
            if(ILogic.getILogic().getExplosionPoint2().x!=0){   //vuol dire che è null
                this.drawDoubleExplosion(ILogic.getILogic().getExplosionPoint1(), ILogic.getILogic().getExplosionPoint2(), g2);
            }else{
                this.drawExplosion(ILogic.getILogic().getExplosionPoint1(), g2); 
            }
        }

        this.score.setText("Score: " + ILogic.getILogic().getScore());
    }


    public void drawBackground(Graphics2D g2){
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

    public void drawPig(String s, Graphics2D g2){
        if(s == "green")
            this.pig = Images.imagesArray[Images.GREEN_PIG];
        else if(s == "pink")
            this.pig = Images.imagesArray[Images.PINK_PIG];
        else if(s == "blue")
            this.pig = Images.imagesArray[Images.BLUE_PIG];
        else if(s == "yellow")
            this.pig = Images.imagesArray[Images.YELLOW_PIG];
    
        for(int i=0; i<ILogic.getILogic().getNumberOfPigs(ILogic.getILogic().getLevel()); i++){
            if(ILogic.getILogic().getPigsRect()[i].x!=0){
                g2.drawImage(this.pig, ILogic.getILogic().getPigsRect()[i].x,         
                    ILogic.getILogic().getPigsRect()[i].y, null);      
            }
        }
    }

    public void drawBlocks(Graphics2D g2){  
        if(ILogic.getILogic().getDifficulty()==0)
            this.block = Images.imagesArray[Images.GLASS_BLOCK];
        else if(ILogic.getILogic().getDifficulty()==1)
            this.block = Images.imagesArray[Images.WOOD_BLOCK];
        else if(ILogic.getILogic().getDifficulty()==2)
            this.block = Images.imagesArray[Images.METAL_BLOCK];
        
        for(int i=0; i<ILogic.getILogic().getNumberOfBlocks(ILogic.getILogic().getLevel()); i++){
            if(ILogic.getILogic().getBlocksRect()[i].x!=0){
                g2.drawImage(block, 
                    ILogic.getILogic().getBlocksRect()[i].x, 
                        ILogic.getILogic().getBlocksRect()[i].y, null);     
            } 
        }
    }

    public void drawBird(Graphics2D g2){    
        if(ILogic.getILogic().getNumAliveBirds()!=0)
            g2.drawImage(Images.imagesArray[Images.RED_BIRD], ILogic.getILogic().getBirdPoint().x, ILogic.getILogic().getBirdPoint().y, null);
        
        for(int i=1; i<ILogic.getILogic().getNumAliveBirds(); i++){ 
            g2.drawImage(Images.imagesArray[Images.RED_BIRD], ILogic.getILogic().getBirdsPositions(ILogic.getILogic().getLevel())[i].x, 
                ILogic.getILogic().getBirdsPositions(ILogic.getILogic().getLevel())[i].y, null);      //metti posizioni in array diversi in base al livello, cicla for con una variabile numer of birds, dipendente dal livello
        }
    }

    public void drawExplosion(Point p, Graphics2D g2){
        //int width=Images.imagesArray[Images.EXPLOSION_1].getWidth(null)+10;
        //int height=Images.imagesArray[Images.EXPLOSION_1].getHeight(null)+10;
        //Shape oldClip=g2.getClip();
        //g2.setClip(650, 510, width, height);

        int x = p.x-(MainGUI.EXPLOSION_SIZE-MainGUI.BLOCK_SIZE)/2;  //per centrare l'esplosione
        int y = p.y-(MainGUI.EXPLOSION_SIZE-MainGUI.BLOCK_SIZE)/2;

        switch(explosionRefreshCounter/EXPLOSION_RATE){     //le prime 41 volte disegno la prima immagine, le seconde 41 la seconda, ... 
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
        //g2.setClip(oldClip);
        explosionRefreshCounter=(explosionRefreshCounter+1)%(EXPLOSION_RATE*7);
        if(explosionRefreshCounter==0){
            ILogic.getILogic().setIsExploding(false);
            ILogic.getILogic().resetExplosionPoints();
        } 
    }

    public void drawDoubleExplosion(Point p1, Point p2, Graphics2D g2){
        int x1 = (p1.x+p2.x)/2-(MainGUI.EXPLOSION_SIZE-MainGUI.BLOCK_SIZE)/2;  //per centrare l'esplosione
        int y1 = (p1.y+p2.y)/2-(MainGUI.EXPLOSION_SIZE-MainGUI.BLOCK_SIZE)/2;    

        switch(explosionRefreshCounter/EXPLOSION_RATE){     //le prime 41 volte disegno la prima immagine, le seconde 41 la seconda, ... 
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

    public void drawTrajectory(Graphics2D g2){
        g2.drawPolyline(trajectoryX, trajectoryY, trajectoryX.length);  
    }

    public void drawHalfTrajectory(Graphics2D g2){
        g2.drawPolyline(trajectoryX, trajectoryY, trajectoryX.length/2);  
    }

    //poi togli questo bottone o metti che esce game over in caso
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==skipButton){  
           MainGUI.getInstance().flip("END_MENU");
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {       //mi permette di lanciare l'uccellino anche quando è in volo, devo impedirlo
        if(!(ILogic.getILogic().getIsFlying())){
            if((e.getX()<MainGUI.CATAPULT_CENTER_X) && (e.getY()>MainGUI.CATAPULT_CENTER_Y)){
                xReleased = e.getX();
                yReleased = e.getY();    
                trajectoryIsVisible=false;  
                ILogic.getILogic().setBirdIsFlying(true);      
            }
        }
    } 

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        if(!(ILogic.getILogic().getIsFlying())){
            if((e.getX()<MainGUI.CATAPULT_CENTER_X) && (e.getY()>MainGUI.CATAPULT_CENTER_Y)){
                trajectoryIsVisible=true;    
                trajectoryX = ILogic.getILogic().calculateTrajectoryX();
                trajectoryY = ILogic.getILogic().calculateTrajectoryY(e.getX(), e.getY());  
            } 
        }
    }

    @Override
    public void mouseMoved(MouseEvent e){}

    public int getXReleased(){      //usalo per calcolare traiettoria in logic e vedere se incappa in altri oggetti
        return xReleased;
    }

    public int getYReleased(){
        return yReleased;
    }
}