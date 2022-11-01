package utils;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class SoundPlayer {

    public static final int START_GAME=0;
    public static final int GAME_OVER=1;
    public static final int NUM_CLIP=2;
    private static SoundPlayer soundPlayer=null;

    private Clip[] clips;

    private SoundPlayer(){
        this.clips=new Clip[2];
        try{
            for(int i=0; i<NUM_CLIP; i++){
                clips[i]=AudioSystem.getClip();
            }
            clips[START_GAME].open(AudioSystem.getAudioInputStream(new File("..\\media\\audio\\let's-go.wav")));
            clips[GAME_OVER].open(AudioSystem.getAudioInputStream(new File("..\\media\\audio\\game-over.wav")));
        }catch(LineUnavailableException|UnsupportedAudioFileException|IOException e){
            JOptionPane.showMessageDialog(null, "Errore nel caricamento audio", "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static SoundPlayer getSoundPlayer(){
        if(SoundPlayer.soundPlayer==null)
            SoundPlayer.soundPlayer=new SoundPlayer();
        return SoundPlayer.soundPlayer;
    }

    // public void playGameOver(){
    //     //clips[START_GAME].close();
    //     dispose();
    //     clips[GAME_OVER].start();
    // }

    public void playGameOver(){
        javax.swing.SwingWorker<Void,Void> worker= new javax.swing.SwingWorker<Void,Void>(){
            @Override
            public Void doInBackground(){      
                
                clips[GAME_OVER].start();   
                
                // if((clips[GAME_OVER].isOpen())&&(!(clips[GAME_OVER].isActive()))){  //entra e esce a ciclo (perché è chiamato nel thread in logic... non va bene)
                //     clips[GAME_OVER].stop();
                //     clips[GAME_OVER].flush();
                // }
                return null;
            }
        };
        worker.execute();
    }

    // public void playStartGame(){
    //     dispose();
    //     clips[START_GAME].setFramePosition(0);
    //     clips[START_GAME].start();
    // }

    public void playStartGame(){
        javax.swing.SwingWorker<Void,Void> worker= new javax.swing.SwingWorker<Void,Void>(){
            @Override
            public Void doInBackground(){
                clips[START_GAME].setFramePosition(0);  //meno problemi di efficienza
                clips[START_GAME].start();
                System.out.println("apro clip");
                System.out.println("aperta: " + clips[START_GAME].isOpen());
                System.out.println("attiva: " + clips[START_GAME].isActive());
                // if((clips[START_GAME].isOpen())&&(!(clips[START_GAME].isActive()))){
                //     clips[START_GAME].stop();
                //     clips[START_GAME].flush();
                //     System.out.println("chiudo clip");
                // }
                return null;
            }
        };
        worker.execute();
    }

    // private void dispose(){
    //     for(int i=0;i<clips.length;i++)
    //         clips[i].close();
    // }
    
}
