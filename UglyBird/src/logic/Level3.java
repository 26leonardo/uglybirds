package logic;

import java.awt.Point;

import view.MainGUI;

public class Level3 extends Level {
    public Level3(){
        numberOfBirds = 4;
        numberOfPigs = 4;
        numberOfBlocks = 9;
        birdsPositions = new Point[numberOfBirds];
        pigsPositions = new Point[numberOfPigs];
        blocksPositions = new Point[numberOfBlocks];

        birdsPositions[0]=BIRD_ON_CATAPULT;
        birdsPositions[1]=SECOND_BIRD; 
        birdsPositions[2]=THIRD_BIRD;
        birdsPositions[3]=FOURTH_BIRD;
        //birdsPositions[4]=FIFTH_BIRD;

        pigsPositions[0]=new Point(ZERO_ROW, FLOOR-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE-MainGUI.PIG_SIZE);
        pigsPositions[1]=new Point(FIRST_ROW, FLOOR-MainGUI.BLOCK_SIZE-MainGUI.PIG_SIZE); 
        pigsPositions[2]=new Point(SECOND_ROW, FLOOR-MainGUI.PIG_SIZE);
        pigsPositions[3]=new Point(THIRD_ROW, FLOOR-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE-MainGUI.PIG_SIZE);

        blocksPositions[0]=new Point(ZERO_ROW,FLOOR);  
        blocksPositions[1]=new Point(ZERO_ROW,FLOOR-MainGUI.BLOCK_SIZE);
        blocksPositions[2]=new Point(ZERO_ROW,FLOOR-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE);
        blocksPositions[3]=new Point(FIRST_ROW,FLOOR);
        blocksPositions[4]=new Point(FIRST_ROW,FLOOR-MainGUI.BLOCK_SIZE);
        blocksPositions[5]=new Point(SECOND_ROW,FLOOR);
        blocksPositions[6]=new Point(THIRD_ROW,FLOOR);
        blocksPositions[7]=new Point(THIRD_ROW,FLOOR-MainGUI.BLOCK_SIZE);
        blocksPositions[8]=new Point(THIRD_ROW,FLOOR-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE);
    }
}
