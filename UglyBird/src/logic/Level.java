package logic;

import view.IView;

public class Level {
    protected static final int ZERO_ROW = 600;
    protected static final int FIRST_ROW = 680;
    protected static final int SECOND_ROW = 830;
    protected static final int THIRD_ROW = 980;
    protected static final int FLOOR = 530;
    protected static final Point BIRD_ON_CATAPULT = new Point(IView.CATAPULT_CENTER_X-IView.BIRD_SIZE/2, IView.CATAPULT_CENTER_Y-IView.BIRD_SIZE/2);
    protected static final Point SECOND_BIRD = new Point(10,600);    
    protected static final Point THIRD_BIRD = new Point(SECOND_BIRD.x + IView.BIRD_SIZE + SECOND_BIRD.x, SECOND_BIRD.y);    
    protected static final Point FOURTH_BIRD = new Point(SECOND_BIRD.x + IView.BIRD_SIZE + SECOND_BIRD.x + IView.BIRD_SIZE + SECOND_BIRD.x, SECOND_BIRD.y);    
    //protected static final Point FIFTH_BIRD = new Point(SECOND_BIRD.x + IView.BIRD_SIZE + SECOND_BIRD.x + IView.BIRD_SIZE + SECOND_BIRD.x + IView.BIRD_SIZE + SECOND_BIRD.x, SECOND_BIRD.y);    
    
    protected int numberOfBirds;
    protected int numberOfPigs;
    protected int numberOfBlocks;

    protected Point birdsPositions[];
    protected Point pigsPositions[];
    protected Point blocksPositions[];

    protected Point[] getBirdsPositions(){
        return birdsPositions;
    }

    protected Point[] getPigsPositions(){
        return pigsPositions;
    }

    protected Point[] getBlocksPositions(){
        return blocksPositions;
    }
}
