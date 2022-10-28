package logic;

import java.awt.Point;

import view.IView;

public class Level2 extends Level{
    public Level2(){
        numberOfBirds = 4;
        numberOfPigs = 4;
        numberOfBlocks = 5;
        birdsPositions = new Point[numberOfBirds];
        pigsPositions = new Point[numberOfPigs];
        blocksPositions = new Point[numberOfBlocks];

        birdsPositions[0]=BIRD_ON_CATAPULT;
        birdsPositions[1]=SECOND_BIRD; 
        birdsPositions[2]=THIRD_BIRD;
        birdsPositions[3]=FOURTH_BIRD;
        //birdsPositions[4]=FIFTH_BIRD;

        pigsPositions[0]=new Point(FIRST_ROW, FLOOR-IView.BLOCK_SIZE-IView.PIG_SIZE); 
        pigsPositions[1]=new Point(SECOND_ROW, FLOOR-IView.PIG_SIZE);
        pigsPositions[2]=new Point(THIRD_ROW, FLOOR-IView.BLOCK_SIZE-IView.PIG_SIZE);
        pigsPositions[3]=new Point(ZERO_ROW, FLOOR);

        blocksPositions[0]=new Point(FIRST_ROW,FLOOR);  
        blocksPositions[1]=new Point(FIRST_ROW,FLOOR-IView.BLOCK_SIZE);
        blocksPositions[2]=new Point(SECOND_ROW,FLOOR);
        blocksPositions[3]=new Point(THIRD_ROW,FLOOR);
        blocksPositions[4]=new Point(THIRD_ROW,FLOOR-IView.BLOCK_SIZE);
    }
}
