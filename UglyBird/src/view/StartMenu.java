package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalTime;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import logic.ILogic;
import utils.ClipManager;

public class StartMenu extends JPanel implements ActionListener, KeyListener, MouseListener{

    private Image wallpaper;            //background
    private LocalTime time = LocalTime.now();
    private JTextField nameField;       //name
    private ButtonGroup difficultyButtons;   //difficulty
    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JRadioButton difficultButton;
    private ButtonGroup levelButtons;   //level
    private JRadioButton level1Button;
    private JRadioButton level2Button;
    private JRadioButton level3Button;
    private JRadioButton level4Button;
    private JRadioButton level5Button;
    private ImageIcon redBirdIcon;      //bird
    private ImageIcon yellowBirdIcon; 
    private ImageIcon blueBirdIcon;
    private ImageIcon whiteBirdIcon;
    private ButtonGroup birdButtons;
    private JRadioButton redBirdButton;
    private JRadioButton yellowBirdButton;
    private JRadioButton blueBirdButton;
    private JRadioButton whiteBirdButton;
    private JLabel redBirdLabel;
    private JLabel yellowBirdLabel;
    private JLabel blueBirdLabel;
    private JLabel whiteBirdLabel;
    private ImageIcon greenPigIcon;     //pig
    private ImageIcon pinkPigIcon;
    private ImageIcon yellowPigIcon;
    private ImageIcon bluePigIcon;
    private ButtonGroup pigButtons;
    private JRadioButton greenPigButton;
    private JRadioButton pinkPigButton;
    private JRadioButton bluePigButton;
    private JRadioButton yellowPigButton;
    private JLabel greenPigLabel;
    private JLabel pinkPigLabel;
    private JLabel bluePigLabel;
    private JLabel yellowPigLabel;
    private ButtonGroup soundButtons;   //sound
    private JRadioButton soundOnButton;
    private JRadioButton soundOffButton;
    private JButton confirm;        //start game
    private JLabel errorMessage;    
    
    private String playerName;
    private String difficulty;
    private String level;
    private String birdColour;
    private String pigColour;
    private String sound;
    private boolean soundState=true;

    public StartMenu(){
        super();
        this.setPreferredSize(new Dimension(IView.WIDTH, IView.HEIGHT));
        this.setSize(IView.WIDTH, IView.HEIGHT); 

        this.addKeyListener(this);
        this.setFocusable(true);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gb = new GridBagConstraints(); 

        ClipManager.getInstance().loop(ClipManager.ELEVATOR_CLIP); 

        //name
        JLabel insertName = new JLabel("What's your name? ");       
        insertName.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 0;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(insertName, gb);

        nameField = new JTextField(20);
        nameField.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 1;
        gb.gridy = 0;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(nameField, gb);
        nameField.addKeyListener(this);
        nameField.setOpaque(false);

        //difficulty
        JLabel chooseDifficulty = new JLabel("Choose the difficulty: ");
        chooseDifficulty.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 1;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(chooseDifficulty, gb);

        easyButton = new JRadioButton("Easy");
        mediumButton = new JRadioButton("Medium");
        difficultButton = new JRadioButton("Difficult");

        easyButton.setSelected(true);
        easyButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        easyButton.setBackground(null);
        easyButton.addActionListener(this);
        this.easyButton.setActionCommand("0");
        easyButton.addKeyListener(this);
        easyButton.setOpaque(false);

        mediumButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        mediumButton.setBackground(null);
        mediumButton.addActionListener(this);
        this.mediumButton.setActionCommand("1");
        mediumButton.addKeyListener(this);
        mediumButton.setOpaque(false);

        difficultButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        difficultButton.setBackground(null);
        difficultButton.addActionListener(this);
        this.difficultButton.setActionCommand("2");
        difficultButton.addKeyListener(this);
        difficultButton.setOpaque(false);

        difficultyButtons = new ButtonGroup();
        difficultyButtons.add(easyButton);
        difficultyButtons.add(mediumButton);
        difficultyButtons.add(difficultButton);

        gb.gridx = 1;
        gb.gridy = 1;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(easyButton, gb);

        gb.gridx = 2;
        gb.gridy = 1;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(mediumButton, gb);

        gb.gridx = 3;
        gb.gridy = 1;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(difficultButton, gb);

        //level
        JLabel chooseLevelText = new JLabel("Choose the level: ");
        chooseLevelText.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(chooseLevelText, gb);

        level1Button = new JRadioButton("1");
        level2Button = new JRadioButton("2      ");
        level3Button = new JRadioButton("3      ");
        level4Button = new JRadioButton("4            ");
        level5Button = new JRadioButton("5      ");

        level1Button.setSelected(true);
        level1Button.setFont(MainGUI.ANGRY_BIRDS_FONT);
        level1Button.setBackground(null);
        level1Button.addActionListener(this);
        this.level1Button.setActionCommand("1");
        level1Button.addKeyListener(this);
        level1Button.setOpaque(false);

        level2Button.setFont(MainGUI.ANGRY_BIRDS_FONT);
        level2Button.setBackground(null);
        level2Button.addActionListener(this);
        this.level2Button.setActionCommand("2");
        level2Button.addKeyListener(this);
        level2Button.setOpaque(false);

        level3Button.setFont(MainGUI.ANGRY_BIRDS_FONT);
        level3Button.setBackground(null);
        level3Button.addActionListener(this);
        this.level3Button.setActionCommand("3");
        level3Button.addKeyListener(this);
        level3Button.setOpaque(false);

        level4Button.setFont(MainGUI.ANGRY_BIRDS_FONT);
        level4Button.setBackground(null);
        level4Button.addActionListener(this);
        this.level4Button.setActionCommand("4");
        level4Button.addKeyListener(this);
        level4Button.setOpaque(false);

        level5Button.setFont(MainGUI.ANGRY_BIRDS_FONT);
        level5Button.setBackground(null);
        level5Button.addActionListener(this);
        this.level5Button.setActionCommand("5");
        level5Button.addKeyListener(this);
        level5Button.setOpaque(false);

        levelButtons = new ButtonGroup();
        levelButtons.add(level1Button);
        levelButtons.add(level2Button);
        levelButtons.add(level3Button);
        levelButtons.add(level4Button);
        levelButtons.add(level5Button);

        gb.gridx = 1;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(level1Button, gb);

        gb.gridx = 2;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(level2Button, gb);

        gb.gridx = 3;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(level3Button, gb);

        gb.gridx = 4;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(level4Button, gb);

        gb.gridx = 5;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(level5Button, gb);

        //bird
        JLabel chooseBird = new JLabel("Choose which bird you want to use: ");
        chooseBird.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(chooseBird, gb);

        redBirdButton = new JRadioButton("Carmelo");
        yellowBirdButton = new JRadioButton("Gonzalo      ");
        blueBirdButton = new JRadioButton("Sabrinuccia      ");
        whiteBirdButton = new JRadioButton("Antonio");

        redBirdButton.setSelected(true);
        redBirdButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        redBirdButton.setBackground(null);
        redBirdButton.addActionListener(this);
        this.redBirdButton.setActionCommand("red");
        redBirdButton.addKeyListener(this);
        redBirdButton.setOpaque(false);

        yellowBirdButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        yellowBirdButton.setBackground(null);
        yellowBirdButton.addActionListener(this);
        this.yellowBirdButton.setActionCommand("yellow");
        yellowBirdButton.addKeyListener(this);
        yellowBirdButton.setOpaque(false);

        blueBirdButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        blueBirdButton.setBackground(null);
        blueBirdButton.addActionListener(this);
        this.blueBirdButton.setActionCommand("blue"); 
        blueBirdButton.addKeyListener(this);
        blueBirdButton.setOpaque(false);

        whiteBirdButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        whiteBirdButton.setBackground(null);
        whiteBirdButton.addActionListener(this);        
        this.whiteBirdButton.setActionCommand("white");
        whiteBirdButton.addKeyListener(this);
        whiteBirdButton.setOpaque(false);

        birdButtons = new ButtonGroup();
        birdButtons.add(redBirdButton);
        birdButtons.add(yellowBirdButton);
        birdButtons.add(blueBirdButton);
        birdButtons.add(whiteBirdButton);

        gb.gridx = 1;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(redBirdButton, gb);

        gb.gridx = 2;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(yellowBirdButton, gb);

        gb.gridx = 3;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(blueBirdButton, gb);

        gb.gridx = 4;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(whiteBirdButton, gb);

        redBirdLabel = new JLabel();
        redBirdIcon = new ImageIcon(Images.imagesArray[Images.RED_BIRD]);
        redBirdLabel.setIcon(redBirdIcon);
        redBirdLabel.addMouseListener(this);
        gb.gridx = 1;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(redBirdLabel, gb);

        yellowBirdLabel = new JLabel();
        yellowBirdIcon = new ImageIcon(Images.imagesArray[Images.YELLOW_BIRD]);
        yellowBirdLabel.setIcon(yellowBirdIcon);
        yellowBirdLabel.addMouseListener(this);
        gb.gridx = 2;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(yellowBirdLabel, gb);

        blueBirdLabel = new JLabel();
        blueBirdIcon = new ImageIcon(Images.imagesArray[Images.BLUE_BIRD]);
        blueBirdLabel.setIcon(blueBirdIcon);
        blueBirdLabel.addMouseListener(this);
        gb.gridx = 3;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(blueBirdLabel, gb);

        whiteBirdLabel = new JLabel();
        whiteBirdIcon = new ImageIcon(Images.imagesArray[Images.WHITE_BIRD]);
        whiteBirdLabel.setIcon(whiteBirdIcon);
        whiteBirdLabel.addMouseListener(this);
        gb.gridx = 4;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(whiteBirdLabel, gb);

        //pig
        JLabel choosePig = new JLabel("Choose the colour of the pig: ");
        choosePig.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 5;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(choosePig, gb);

        greenPigButton = new JRadioButton("green");
        pinkPigButton = new JRadioButton("pink      ");
        bluePigButton = new JRadioButton("blue      ");
        yellowPigButton = new JRadioButton("yellow");

        greenPigButton.setSelected(true);
        greenPigButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        greenPigButton.setBackground(null);
        greenPigButton.addActionListener(this);
        this.greenPigButton.setActionCommand("green");
        greenPigButton.addKeyListener(this);
        greenPigButton.setOpaque(false);

        pinkPigButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        pinkPigButton.setBackground(null);
        pinkPigButton.addActionListener(this);
        this.pinkPigButton.setActionCommand("pink");
        pinkPigButton.addKeyListener(this);
        pinkPigButton.setOpaque(false);

        bluePigButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        bluePigButton.setBackground(null);
        bluePigButton.addActionListener(this);
        this.bluePigButton.setActionCommand("blue");
        bluePigButton.addKeyListener(this);
        bluePigButton.setOpaque(false);

        yellowPigButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        yellowPigButton.setBackground(null);
        yellowPigButton.addActionListener(this);        
        this.yellowPigButton.setActionCommand("yellow");
        yellowPigButton.addKeyListener(this);
        yellowPigButton.setOpaque(false);

        pigButtons = new ButtonGroup();
        pigButtons.add(greenPigButton);
        pigButtons.add(pinkPigButton);
        pigButtons.add(bluePigButton);
        pigButtons.add(yellowPigButton);

        gb.gridx = 1;
        gb.gridy = 5;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(greenPigButton, gb);

        gb.gridx = 2;
        gb.gridy = 5;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(pinkPigButton, gb);

        gb.gridx = 3;
        gb.gridy = 5;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(bluePigButton, gb);

        gb.gridx = 4;
        gb.gridy = 5;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(yellowPigButton, gb);

        greenPigLabel = new JLabel();
        greenPigIcon = new ImageIcon(Images.imagesArray[Images.GREEN_PIG]);
        greenPigLabel.setIcon(greenPigIcon);
        greenPigLabel.addMouseListener(this);
        gb.gridx = 1;
        gb.gridy = 6;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(greenPigLabel, gb);

        pinkPigLabel = new JLabel();
        pinkPigIcon = new ImageIcon(Images.imagesArray[Images.PINK_PIG]);
        pinkPigLabel.setIcon(pinkPigIcon);
        pinkPigLabel.addMouseListener(this);
        gb.gridx = 2;
        gb.gridy = 6;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(pinkPigLabel, gb);

        bluePigLabel = new JLabel();
        bluePigIcon = new ImageIcon(Images.imagesArray[Images.BLUE_PIG]);
        bluePigLabel.setIcon(bluePigIcon);
        bluePigLabel.addMouseListener(this);
        gb.gridx = 3;
        gb.gridy = 6;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(bluePigLabel, gb);

        yellowPigLabel = new JLabel();
        yellowPigIcon = new ImageIcon(Images.imagesArray[Images.YELLOW_PIG]);
        yellowPigLabel.setIcon(yellowPigIcon);
        yellowPigLabel.addMouseListener(this);
        gb.gridx = 4;
        gb.gridy = 6;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(yellowPigLabel, gb);

        //sound
        JLabel chooseSound = new JLabel("Sound: ");
        chooseSound.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 7;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(chooseSound, gb);

        soundOnButton = new JRadioButton("On");
        soundOffButton = new JRadioButton("Off");

        soundOnButton.setSelected(true);
        soundOnButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        soundOnButton.setBackground(null);
        soundOnButton.addActionListener(this);
        this.soundOnButton.setActionCommand("On");
        soundOnButton.addKeyListener(this);
        soundOnButton.setOpaque(false);
        
        soundOffButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        soundOffButton.setBackground(null);
        soundOffButton.addActionListener(this);
        this.soundOffButton.setActionCommand("Off");
        soundOffButton.addKeyListener(this);
        soundOffButton.setOpaque(false);

        soundButtons = new ButtonGroup();
        soundButtons.add(soundOffButton);
        soundButtons.add(soundOnButton);

        gb.gridx = 1;
        gb.gridy = 7;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(soundOnButton, gb);

        gb.gridx = 2;
        gb.gridy = 7;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(soundOffButton, gb);

        //check name as been inserted
        errorMessage = new JLabel();
        errorMessage.setText("");
        errorMessage.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 1;
        gb.gridy = 8;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(errorMessage, gb);

        //button to start game
        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        confirm.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 1;
        gb.gridy = 9;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(confirm, gb);
        confirm.setOpaque(false);
    }

    public void actionPerformed(ActionEvent e) { 
        if(e.getSource()==confirm){     
            if(nameField.getText().trim().length() == 0){ 
                errorMessage.setText("Please insert a valid name");
            }else if(nameField.getText().contains(";")){
                errorMessage.setText("Please remove ';'");
            }else{
                MainGUI.getInstance().resetTutorial();
                MainGUI.getInstance().flip("CANVA");
                ClipManager.getInstance().stop(ClipManager.ELEVATOR_CLIP); 

                playerName = nameField.getText().trim();
                difficulty = difficultyButtons.getSelection().getActionCommand();
                level = levelButtons.getSelection().getActionCommand();
                birdColour = birdButtons.getSelection().getActionCommand();
                pigColour = pigButtons.getSelection().getActionCommand();
                sound = soundButtons.getSelection().getActionCommand();

                if(sound=="On"){
                    soundState=true;
                }else{
                    soundState=false;
                }
                ClipManager.getInstance().setSoundState(soundState);
                ClipManager.getInstance().play(ClipManager.START_GAME_CLIP);

                ILogic.getILogic().updateRect();

                nameField.setText("");
                easyButton.setSelected(true);
                level1Button.setSelected(true);
                redBirdButton.setSelected(true);
                greenPigButton.setSelected(true);
                soundOnButton.setSelected(true);
                errorMessage.setText("");
            }
        }
    }

    protected String getPlayerName(){
       return playerName;
    }

    protected String getDifficulty(){
        return difficulty;
    }

    protected String getLevel(){
        return level;
    }

    protected String getBirdColour(){
        return birdColour;
    }

    protected String getPigColour(){
        return pigColour;
    }

    protected String getSoundState(){
        return sound;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 

        if(time.isAfter(LocalTime.MIDNIGHT) && time.isBefore(LocalTime.of(6, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.NIGHT];
        else if(time.isAfter(LocalTime.of(6, 0, 0))&& time.isBefore(LocalTime.of(10, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.SUNRISE];
        else if(time.isAfter(LocalTime.of(10, 0, 0))&& time.isBefore(LocalTime.of(18, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.DAY];
        else if(time.isAfter(LocalTime.of(18, 0, 0))&& time.isBefore(LocalTime.of(21, 0, 0)))
            this.wallpaper = Images.imagesArray[Images.DUSK];
        else if(time.isAfter(LocalTime.of(21, 0, 0))&& time.isBefore(LocalTime.MAX))
            this.wallpaper = Images.imagesArray[Images.SUNSET];  
            
        g2.drawImage(this.wallpaper, 0, 0, null);
        
        g2.drawImage(Images.imagesArray[Images.TITLE], 250, 5, null);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {  
        if(e.getKeyCode()==KeyEvent.VK_ENTER){
            confirm.doClick();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent()==redBirdLabel){     //birds
            redBirdButton.doClick();
        } 
        if(e.getComponent()==yellowBirdLabel){
            yellowBirdButton.doClick();
        }
        if(e.getComponent()==blueBirdLabel){
            blueBirdButton.doClick();
        } 
        if(e.getComponent()==whiteBirdLabel){
            whiteBirdButton.doClick();
        }

        if(e.getComponent()==greenPigLabel){        //pigs
            greenPigButton.doClick();
        }
        if(e.getComponent()==pinkPigLabel){
            pinkPigButton.doClick();
        }
        if(e.getComponent()==bluePigLabel){
            bluePigButton.doClick();
        }
        if(e.getComponent()==yellowPigLabel){
            yellowPigButton.doClick();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}