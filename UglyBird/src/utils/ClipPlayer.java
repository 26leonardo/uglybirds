package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class ClipPlayer {

    private final static int DEFAULT_NUM_CLIPS = 1;

    private int clipArrayIndex = 0;
    private Clip[] audioClipArray;

    public ClipPlayer(String file) {
        try{
            File audioFile = new File("../media/audio/" + file + ".wav");
            AudioFileFormat aff = AudioSystem.getAudioFileFormat(audioFile);
            AudioFormat af = aff.getFormat();

            int numClips = computeNumClips();
            this.audioClipArray = new Clip[numClips];
            for (int i = 0; i < this.audioClipArray.length; i++)
                this.audioClipArray[i] = getClip(audioFile, af);
        }catch(FileNotFoundException fnfe) {
            showErrorMessage("The specified file does not exist, the application will be closed.");
            System.exit(-1);
        }
        catch(UnsupportedAudioFileException uafe) {
            showErrorMessage("The specified audio file is not supported, the application will be closed.");
            System.exit(-1);
        }
        catch(IOException ioe) {
            showErrorMessage("An unexpected I/O error has occurred occurred, the application will be closed.");
            System.exit(-1);
        }
    }

    private static void showErrorMessage(String errStr) {
        JOptionPane.showMessageDialog(null, errStr, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private int computeNumClips() { //prova mettendolo a 1
        return DEFAULT_NUM_CLIPS;
    }

    private Clip getClip(File audioFile, AudioFormat af) throws UnsupportedAudioFileException, IOException {
        Clip audioClip = null;
        AudioInputStream ais = null;

        try {
            ais = AudioSystem.getAudioInputStream(audioFile);
            int bufferSize = (int)ais.getFrameLength() * af.getFrameSize();
            DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, ais.getFormat(), bufferSize);

            if (!AudioSystem.isLineSupported(dataLineInfo))
                throw new IOException("Error: the AudioSystem does not support the specified DataLine.Info object");

            try {
                audioClip = (Clip)AudioSystem.getLine(dataLineInfo);
                audioClip.open(ais);
                audioClip.setFramePosition(audioClip.getFrameLength());
            }
            catch(LineUnavailableException lue) {
                throw new IOException("Error: a LineUnavailableException exception was thrown");
            }

        }
        catch(UnsupportedAudioFileException uafe) {
            uafe.printStackTrace();
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
        finally {
            if (ais != null)
                ais.close();
        }
        return audioClip;
    } 
    public void play() {
        this.audioClipArray[this.clipArrayIndex].loop(1);
        this.clipArrayIndex++;
        this.clipArrayIndex %= this.audioClipArray.length; 
    }

    public void loop() {
        this.audioClipArray[this.clipArrayIndex].loop(Clip.LOOP_CONTINUOUSLY);
        this.clipArrayIndex++;
        this.clipArrayIndex %= this.audioClipArray.length; 
    }

    public void stop() {
        for (int i = 0; i < this.audioClipArray.length; i++){
            this.audioClipArray[i].stop();
            this.audioClipArray[i].setMicrosecondPosition(0);
        }
    }

    public boolean isPlaying(){
        boolean playing=false;

        for (int i = 0; i < this.audioClipArray.length; i++){
            if(this.audioClipArray[i].isRunning()){
                playing=true;
            }
        }
        return playing;
    }
}
