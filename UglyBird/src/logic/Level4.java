package logic;

import java.awt.Point;
import view.MainGUI;

public class Level4 extends Level {
    public Level4(){
        numberOfBirds = 4;
        numberOfPigs = 4;
        numberOfBlocks = 6;
        birdsPositions = new Point[numberOfBirds];
        pigsPositions = new Point[numberOfPigs];
        blocksPositions = new Point[numberOfBlocks];

        birdsPositions[0]=BIRD_ON_CATAPULT;
        birdsPositions[1]=SECOND_BIRD; 
        birdsPositions[2]=THIRD_BIRD;
        birdsPositions[3]=FOURTH_BIRD;

        pigsPositions[0]=new Point(FIRST_ROW, FLOOR-MainGUI.PIG_SIZE); 
        pigsPositions[1]=new Point(SECOND_ROW, FLOOR-MainGUI.BLOCK_SIZE-MainGUI.PIG_SIZE);
        pigsPositions[2]=new Point(THIRD_ROW, FLOOR-MainGUI.BLOCK_SIZE-MainGUI.PIG_SIZE);
        pigsPositions[3]=new Point(FIRST_ROW, FLOOR-MainGUI.PIG_SIZE-MainGUI.BLOCK_SIZE-MainGUI.PIG_SIZE);

        blocksPositions[0]=new Point(FIRST_ROW,FLOOR);  
        blocksPositions[1]=new Point(FIRST_ROW,FLOOR-MainGUI.PIG_SIZE-MainGUI.BLOCK_SIZE);
        blocksPositions[2]=new Point(SECOND_ROW,FLOOR);
        blocksPositions[3]=new Point(SECOND_ROW,FLOOR-MainGUI.BLOCK_SIZE);
        blocksPositions[4]=new Point(THIRD_ROW,FLOOR);
        blocksPositions[5]=new Point(THIRD_ROW,FLOOR-MainGUI.BLOCK_SIZE);
    }
    
}
