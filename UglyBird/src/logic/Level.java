package logic;

import java.awt.Point;

import view.MainGUI;

public class Level {
    public static final int ZERO_ROW = 600;
    public static final int FIRST_ROW = 680;
    public static final int SECOND_ROW = 830;
    public static final int THIRD_ROW = 980;
    public static final int FLOOR = 530;
    public static final Point BIRD_ON_CATAPULT = new Point(MainGUI.CATAPULT_CENTER_X-MainGUI.BIRD_SIZE/2, MainGUI.CATAPULT_CENTER_Y-MainGUI.BIRD_SIZE/2);
    public static final Point SECOND_BIRD = new Point(10,600);    
    public static final Point THIRD_BIRD = new Point(SECOND_BIRD.x + MainGUI.BIRD_SIZE + SECOND_BIRD.x, SECOND_BIRD.y);    
    public static final Point FOURTH_BIRD = new Point(SECOND_BIRD.x + MainGUI.BIRD_SIZE + SECOND_BIRD.x + MainGUI.BIRD_SIZE + SECOND_BIRD.x, SECOND_BIRD.y);    
    public static final Point FIFTH_BIRD = new Point(SECOND_BIRD.x + MainGUI.BIRD_SIZE + SECOND_BIRD.x + MainGUI.BIRD_SIZE + SECOND_BIRD.x + MainGUI.BIRD_SIZE + SECOND_BIRD.x, SECOND_BIRD.y);    
    
    int numberOfBirds;
    int numberOfPigs;
    int numberOfBlocks;

    Point birdsPositions[];
    Point pigsPositions[];
    Point blocksPositions[];

    public Point[] getBirdsPositions(){
        return birdsPositions;
    }

    public Point[] getPigsPositions(){
        return pigsPositions;
    }

    public Point[] getBlocksPositions(){
        return blocksPositions;
    }

}
