package logic;

import java.awt.Point;
import view.MainGUI;

public class Level5 extends Level{
    public Level5(){
        numberOfBirds = 5;
        numberOfPigs = 3;
        numberOfBlocks = 7;
        birdsPositions = new Point[numberOfBirds];
        pigsPositions = new Point[numberOfPigs];
        blocksPositions = new Point[numberOfBlocks];

        birdsPositions[0]=BIRD_ON_CATAPULT;
        birdsPositions[1]=SECOND_BIRD; 
        birdsPositions[2]=THIRD_BIRD;
        birdsPositions[3]=FOURTH_BIRD;
        birdsPositions[4]=FIFTH_BIRD;

        pigsPositions[0]=new Point(FIRST_ROW, FLOOR-MainGUI.BLOCK_SIZE-MainGUI.PIG_SIZE); 
        pigsPositions[1]=new Point(SECOND_ROW, FLOOR);
        pigsPositions[2]=new Point(THIRD_ROW, FLOOR);

        blocksPositions[0]=new Point(ZERO_ROW,FLOOR);  
        blocksPositions[1]=new Point(ZERO_ROW,FLOOR-MainGUI.BLOCK_SIZE);
        blocksPositions[2]=new Point(ZERO_ROW,FLOOR-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE);
        blocksPositions[3]=new Point(FIRST_ROW,FLOOR);
        blocksPositions[4]=new Point(FIRST_ROW,FLOOR-MainGUI.BLOCK_SIZE);
        blocksPositions[5]=new Point(ZERO_ROW,FLOOR-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE);
        blocksPositions[6]=new Point(ZERO_ROW,FLOOR-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE-MainGUI.BLOCK_SIZE);
    }
    
}
