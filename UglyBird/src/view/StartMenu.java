package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Image;
import java.awt.event.ActionEvent;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//import utils.FontReader;

public class StartMenu extends JPanel implements ActionListener{

    Image nameLogo;
    JComboBox difficultyChoose;

    public StartMenu(){
        super();
        //this.setOpaque(MainGUI.START_MENU_VISIBLE);
        this.setBackground(new Color(204, 255, 153));
        this.setPreferredSize(new Dimension(MainGUI.WIDTH, MainGUI.HEIGHT));
        this.setSize(MainGUI.WIDTH,MainGUI.HEIGHT);

        try {
            this.nameLogo = ImageIO.read(new File("../media/img/nome-logo.png")).getScaledInstance(600, 150, Image.SCALE_AREA_AVERAGING);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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

        JTextField nameField = new JTextField(20);
        gb.gridx = 1;
        gb.gridy = 0;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(nameField, gb);

        JLabel chooseDifficulty = new JLabel("Choose the difficulty: ");
        //insertName.setFont(FontReader.readFont("angry-birds"));
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
        gb.gridx = 1;
        gb.gridy = 1;
        gb.weightx = 0.0;
        gb.weighty = 0.0;
        this.add(difficultyChoose, gb);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==difficultyChoose){
            System.out.println(difficultyChoose.getSelectedItem());
            // difficultyChoose.setSelectedIndex(e.getID());
            //difficultyChoose.getSelectedIndex();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 
    //FINO A QUI SEMPRE COSì
        g2.drawImage(nameLogo, 250, 5, null);
    }
}