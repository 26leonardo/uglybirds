package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Rankings {

    private String nameFile1;
    private String nameFile2;
    private String nameFile3;
    private String nameFile4;
    private String nameFile5;
    private int[] nScore;
    private String[] sName;
    private String[] bird;
    private String[] difficulty;
    private String[] stringFile;


    Rankings(){
        nameFile1 = "../media/file/File1.csv";
        nameFile2 = "../media/file/File2.csv";
        nameFile3 = "../media/file/File3.csv";
        nameFile4 = "../media/file/File4.csv";
        nameFile5 = "../media/file/File5.csv";
        nScore = new int[3];     
        sName = new String[3];
        bird = new String[3];
        difficulty = new String[3];
        stringFile = new String[3];
        
    }

    public void getInformation(){
      System.err.println("prob 0");
        String []stringTemp = new String[4];
        for(int i = 0; i<3;i++){
          System.err.println("prob 0.5");
          if(stringFile[i]== null){
            sName[i]= "name"+i;
            nScore[i] = 0;
            bird[i] = "none";
            difficulty[i] = "diff: ";
            System.err.println("prob 1");
          }
          else{
            stringTemp = stringFile[i].split(",");
            sName[i]=stringTemp[0];
            nScore[i] = Integer.parseInt(stringTemp[1]);
            bird[i] = stringTemp[2];
            difficulty[i] = stringTemp[3];
            System.err.println("prob 2");
          }
        }
        
        System.err.println("prob 3");
    }

    public void resetArray(){
        
        for(int i = 0; i<5; i++){
            if (i<3){
                nScore[i] = 0;
                sName[i] = "Name" + i;
                bird[i] = "none";
                difficulty[i] = "diff: ";
            }
        }
    }

    public void writeFile(int scorePlayer,String namePlayer, String bird, String difficulty, int level) {
        String nameFile = "";
        switch(level) {
            case 1:
              nameFile = nameFile1;
              break;
            case 2:
              nameFile = nameFile2;
              break;
            case 3:
              nameFile = nameFile3;
              break;
            case 4:
              nameFile = nameFile4;
              break;
            default:
              nameFile = nameFile5;
          }
        try (FileWriter f = new FileWriter(nameFile);                   
            PrintWriter out = new PrintWriter(f, true);) {
                for(int i = 0; i<3; i++){
                    if(scorePlayer > nScore[i]){
                        if(i == 0){                              
                            stringFile[2] = stringFile[1];
                            stringFile[1] = stringFile[0];
                            stringFile[0] = namePlayer+","+scorePlayer+","+bird+","+difficulty;
                        }
                        else if(i== 1){
                            stringFile[2] = stringFile[1];
                            stringFile[1] = namePlayer+","+scorePlayer+","+bird+","+difficulty;
                        }else{
                            stringFile[2] = namePlayer+","+scorePlayer+","+bird+","+difficulty;
                        }
                        break; 
                    }
                }
                for(int i = 0; i<3; i++){
                  out.println(stringFile[i]);
                }
        } catch (IOException e) {}
    }
    
    public void readFile(int level) {
        String nameFile = "";
        switch(level) {
            case 1:
              nameFile = nameFile1;
              break;
            case 2:
              nameFile = nameFile2;
              break;
            case 3:
              nameFile = nameFile3;
              break;
            case 4:
              nameFile = nameFile4;
              break;
            default:
              nameFile = nameFile5;
          }

        try (FileReader fileR= new FileReader(nameFile); 
                  BufferedReader in= new BufferedReader(fileR)) {
            int i=0;
            while((stringFile[i++] = in.readLine())!= null){                               //<---------------
                if(i<=3){
                  break;
                }
            }
            System.err.println("FILE LETTP");
            //getInformation();                               //<---------------
            System.err.println("FILE fuck");
        } catch (FileNotFoundException ex) {
            System.err.println("FILE NOT FOUND");
        }catch (IOException ex) {
            System.err.println("IOException");
        }
    }

    public void deleteFile(int level){
        resetArray();
        String nameFile = "";
        switch(level) {
            case 1:
              nameFile = nameFile1;
              break;
            case 2:
              nameFile = nameFile2;
              break;
            case 3:
              nameFile = nameFile3;
              break;
            case 4:
              nameFile = nameFile4;
              break;
            default:
              nameFile = nameFile5;
          }
        try (FileWriter f = new FileWriter(nameFile);
            PrintWriter out = new PrintWriter(f, true);) {
                for(int i = 0; i<3; i++){
                    out.println(" ");
                }
        } catch (IOException e) {}
    }

    protected int[] getScores(){
        return nScore;
    }

    protected String[] getNames(){
        return sName;
    }

    protected String[] getbird(){
        return bird;
    }

    protected String[] getDiff(){
      return difficulty;
  }

}
