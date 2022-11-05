package utils;

public class ClipManager {
    private static ClipManager instance=null;

    public static final int START_GAME_CLIP = 0;
    public static final int GAME_OVER_CLIP = 1;
    public static final int WIN_CLIP = 2;
    public static final int EXPLOSION_CLIP = 3;
    public static final int ROOSTER_CLIP = 4;
    public static final int POW_CLIP = 5;
    public static final int UI_CLIP = 6;
    public static final int DOLPHIN_CLIP = 7;
    public static final int ELEVATOR_CLIP = 8;
    private static final int NUM_CLIPS = 9;

    ClipPlayer[] clips;
    boolean soundState=true;

    private ClipManager(){
        clips= new ClipPlayer[NUM_CLIPS];
        clips[START_GAME_CLIP]=new ClipPlayer("let's-go");
        clips[GAME_OVER_CLIP]=new ClipPlayer("game-over");
        clips[WIN_CLIP]=new ClipPlayer("win");
        clips[EXPLOSION_CLIP]=new ClipPlayer("explosion");
        clips[ROOSTER_CLIP]=new ClipPlayer("rooster");
        clips[POW_CLIP]=new ClipPlayer("pow");
        clips[UI_CLIP]=new ClipPlayer("ui-1");
        clips[DOLPHIN_CLIP]=new ClipPlayer("dolphin");
        clips[ELEVATOR_CLIP]=new ClipPlayer("elevator");
    }

    public void play(int i){
        if(!(clips[i].isPlaying())&&soundState)
            clips[i].play();
    }

    public void loop(int i){
        if(!(clips[i].isPlaying())&&soundState)
            clips[i].loop();
    }

    public void stop(int i){
        clips[i].stop(); 
    }

    public void setSoundState(boolean soundChoice){
        this.soundState=soundChoice;
    }

    public static ClipManager getInstance(){
        if(instance==null)
            instance=new ClipManager();   
        return instance;
    }
}
