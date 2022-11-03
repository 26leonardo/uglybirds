package logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Rankings {
    // private static final int RED_BIRD = 0;
    // private static final int YELLOW_BIRD = 1;
    // private static final int BLUE_BIRD = 2;
    // private static final int WHITE_BIRD = 3;

    private String nameFile;
    private int[] n;
    private String[] s;
    //private int[] bird;

    private int temp;


    Rankings(){
        nameFile = "File.txt";
        n = new int[3];     
        s = new String[3];
        //bird = new int[3];
    }


    public void resetArray(){
        
        for(int i = 0; i<3; i++){
            n[i] = 0;
            s[i] = "Name" + i;
        }
    }

    public void writeFile(int scorePlayer,String namePlayer) {
        try (FileWriter f = new FileWriter(nameFile);
            PrintWriter out = new PrintWriter(f, true);) {
                temp = 0;
                String sTemp = null;
                for(int i = 0; i<3; i++){
                    if(scorePlayer > n[i]){
                        temp = n[i];
                        sTemp = s[i];
                        n[i]=scorePlayer;
                        s[i]=namePlayer;
                        if(i == 0){
                            n[2] = n[1];
                            s[2] = s[1];
                            n[1] = temp;
                            s[1] = sTemp;
                        }
                        else if(i== 1){
                            n[2] = temp;
                            s[2] = sTemp;
                        }else{}
                        break;
                    }
                }
                for(int i = 0; i<3; i++){
                    out.println(n[i]);
                    out.println(s[i]);
                }
        } catch (IOException e) {}
    }
    
    public void readFile() {
        File f = new File(nameFile);
        int j =0;
        try (Scanner in = new Scanner(f);) {
            while(true){
                if (in.hasNextInt()) {
                    n[j] = in.nextInt();
                    in.nextLine();
                } else {
                    if (in.hasNextLine()) {
                        s[j] = in.nextLine();
                        j++;
                    } else {
                        break;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("file non trovato");
        }
    }

    public void deleteFile(){
        resetArray();
        try (FileWriter f = new FileWriter(nameFile);
            PrintWriter out = new PrintWriter(f, true);) {
                for(int i = 0; i<3; i++){
                    out.println(n[i]);
                    out.println(s[i]);
                }
        } catch (IOException e) {}
    }

    protected int[] getScores(){
        return n;
    }

    protected String[] getNames(){
        return s;
    }


}
