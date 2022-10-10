package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class StartMenu extends JPanel implements ActionListener{

    JTextField nameField;
    ImageIcon greenPigIcon;
    ImageIcon pinkPigIcon;
    ImageIcon yellowPigIcon;
    ImageIcon bluePigIcon;
    JComboBox<String> difficultyChoose;
    JComboBox<String> chooseLevel;
    ButtonGroup pigButtons;
    JRadioButton greenPigButton;
    JRadioButton pinkPigButton;
    JRadioButton bluePigButton;
    JRadioButton yellowPigButton;
    ButtonGroup soundButtons;
    JRadioButton soundOnButton;
    JRadioButton soundOffButton;
    JButton confirm;
    JLabel errorMessage;

    String playerName;
    int difficulty;
    int level;
    String pigColour;
    String sound;

    public StartMenu(){
        super();
        this.setBackground(new Color(204, 255, 153));
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH,MainGUI.HEIGHT);

        this.setLayout(new GridBagLayout());

        GridBagConstraints gb = new GridBagConstraints(); 
        
        JLabel insertName = new JLabel("What's your name? ");
        //insertName.setFont(FontReader.readFont("angry-birds"));
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

        JLabel chooseDifficulty = new JLabel("Choose the difficulty: ");
        chooseDifficulty.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 1;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(chooseDifficulty, gb);


        String[] difficultyLevel = {"easy", "medium", "difficult"};
        difficultyChoose = new JComboBox<String>(difficultyLevel); 
        difficultyChoose.setSelectedIndex(0);
        difficultyChoose.addActionListener(this);   
        difficultyChoose.setFont(MainGUI.ANGRY_BIRDS_FONT);   
        gb.gridx = 1;
        gb.gridy = 1;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(difficultyChoose, gb);

        JLabel chooseLevelText = new JLabel("Choose the level: ");
        chooseLevelText.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(chooseLevelText, gb);

        String[] level = {"1", "2", "3"};
        chooseLevel = new JComboBox<String>(level); 
        chooseLevel.setSelectedIndex(0);
        chooseLevel.addActionListener(this);   
        chooseLevel.setFont(MainGUI.ANGRY_BIRDS_FONT);   
        gb.gridx = 1;
        gb.gridy = 2;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(chooseLevel, gb);

        JLabel choosePig = new JLabel("Choose the colour of the pig: ");
        choosePig.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 3;
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

        pinkPigButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        pinkPigButton.setBackground(null);
        pinkPigButton.addActionListener(this);
        this.pinkPigButton.setActionCommand("pink");

        bluePigButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        bluePigButton.setBackground(null);
        bluePigButton.addActionListener(this);
        this.bluePigButton.setActionCommand("blue");

        yellowPigButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        yellowPigButton.setBackground(null);
        yellowPigButton.addActionListener(this);        
        this.yellowPigButton.setActionCommand("yellow");

        pigButtons = new ButtonGroup();
        pigButtons.add(greenPigButton);
        pigButtons.add(pinkPigButton);
        pigButtons.add(bluePigButton);
        pigButtons.add(yellowPigButton);

        gb.gridx = 1;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(greenPigButton, gb);

        gb.gridx = 2;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(pinkPigButton, gb);

        gb.gridx = 3;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(bluePigButton, gb);

        gb.gridx = 4;
        gb.gridy = 3;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(yellowPigButton, gb);

        JLabel greenPigLabel = new JLabel();
        greenPigIcon = new ImageIcon(Images.imagesArray[Images.GREEN_PIG]);
        greenPigLabel.setIcon(greenPigIcon);
        gb.gridx = 1;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(greenPigLabel, gb);

        JLabel pinkPigLabel = new JLabel();
        pinkPigIcon = new ImageIcon(Images.imagesArray[Images.PINK_PIG]);
        pinkPigLabel.setIcon(pinkPigIcon);
        gb.gridx = 2;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(pinkPigLabel, gb);

        JLabel bluePigLabel = new JLabel();
        bluePigIcon = new ImageIcon(Images.imagesArray[Images.BLUE_PIG]);
        bluePigLabel.setIcon(bluePigIcon);
        gb.gridx = 3;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(bluePigLabel, gb);

        JLabel yellowPigLabel = new JLabel();
        yellowPigIcon = new ImageIcon(Images.imagesArray[Images.YELLOW_PIG]);
        yellowPigLabel.setIcon(yellowPigIcon);
        gb.gridx = 4;
        gb.gridy = 4;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(yellowPigLabel, gb);

        JLabel chooseSound = new JLabel("Sound: ");
        chooseSound.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 0;
        gb.gridy = 5;
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
        
        soundOffButton.setFont(MainGUI.ANGRY_BIRDS_FONT);
        soundOffButton.setBackground(null);
        soundOffButton.addActionListener(this);
        this.soundOffButton.setActionCommand("Off");

        soundButtons = new ButtonGroup();
        soundButtons.add(soundOffButton);
        soundButtons.add(soundOnButton);

        gb.gridx = 1;
        gb.gridy = 5;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(soundOnButton, gb);

        gb.gridx = 2;
        gb.gridy = 5;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(soundOffButton, gb);

        errorMessage = new JLabel();
        errorMessage.setText("");
        errorMessage.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 1;
        gb.gridy = 6;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(errorMessage, gb);

        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        confirm.setFont(MainGUI.ANGRY_BIRDS_FONT);
        gb.gridx = 1;
        gb.gridy = 7;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(confirm, gb);
    }

    public void actionPerformed(ActionEvent e) { 
        if(e.getSource()==confirm){     //rendi possibile con tasto invio anche
            if(nameField.getText().length() == 0){ 
                System.out.println("Please insert a valid name");
                errorMessage.setText("Please insert a valid name.");
            }else{
                System.out.println("Name: " + nameField.getText());  
                System.out.println("Selected difficulty: " + difficultyChoose.getSelectedItem());  
                System.out.println("Selected level: " + chooseLevel.getSelectedItem());  
                System.out.println("Selected pig colour: " + pigButtons.getSelection().getActionCommand()); 
                System.out.println("Sound: " + soundButtons.getSelection().getActionCommand()); 

                MainGUI.getInstance().flip("CANVA");

                playerName = nameField.getText();
                difficulty = difficultyChoose.getSelectedIndex();
                level = chooseLevel.getSelectedIndex(); 
                pigColour = pigButtons.getSelection().getActionCommand();
                sound = soundButtons.getSelection().getActionCommand();

                //System.out.println("Get pig colour: " + this.getPigColour()); 

                nameField.setText("");
                difficultyChoose.setSelectedIndex(0);
                chooseLevel.setSelectedIndex(0);
                greenPigButton.setSelected(true);
                soundOnButton.setSelected(true);
                errorMessage.setText("");
            }
        }
    }
    public String getPlayerName(){
       return playerName;
    }

    public int getDifficulty(){
        return difficulty;
    }

    public int getLevel(){
        return level;
    }

    public String getPigColour(){
        return pigColour;
    }

    public String getSoundState(){
        return sound;
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
    //FINO A QUI SEMPRE COSì
        g2.drawImage(Images.imagesArray[Images.TITLE], 250, 5, null);
    }
}