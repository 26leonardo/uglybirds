package logic;

import java.awt.Point;

import view.IView;

public class Level1 extends Level{
    public Level1(){        
        numberOfBirds = 3;
        numberOfPigs = 3;
        numberOfBlocks = 6;
        birdsPositions = new Point[numberOfBirds];
        pigsPositions = new Point[numberOfPigs];
        blocksPositions = new Point[numberOfBlocks];

        birdsPositions[0]=BIRD_ON_CATAPULT;
        birdsPositions[1]=SECOND_BIRD; 
        birdsPositions[2]=THIRD_BIRD;
        //birdsPositions[3]=FOURTH_BIRD;

        pigsPositions[0]=new Point(FIRST_ROW, FLOOR-IView.PIG_SIZE); 
        pigsPositions[1]=new Point(SECOND_ROW, FLOOR-IView.BLOCK_SIZE-IView.PIG_SIZE);
        pigsPositions[2]=new Point(THIRD_ROW, FLOOR-IView.BLOCK_SIZE-IView.BLOCK_SIZE-IView.PIG_SIZE);

        blocksPositions[0]=new Point(FIRST_ROW,FLOOR);  
        blocksPositions[1]=new Point(SECOND_ROW,FLOOR);
        blocksPositions[2]=new Point(THIRD_ROW,FLOOR);
        blocksPositions[3]=new Point(SECOND_ROW,FLOOR-IView.BLOCK_SIZE);
        blocksPositions[4]=new Point(THIRD_ROW,FLOOR-IView.BLOCK_SIZE);
        blocksPositions[5]=new Point(THIRD_ROW,FLOOR-IView.BLOCK_SIZE-IView.BLOCK_SIZE);
    }
}
