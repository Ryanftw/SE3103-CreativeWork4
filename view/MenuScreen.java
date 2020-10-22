package view;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class MenuScreen {

    private JFrame window;
    private int diff;
    private int spd; 

    public MenuScreen(JFrame window) {
        this.window = window; 
    }

    public void init() {
        Container cp = window.getContentPane();

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400, 200));
        panel.setLayout(new GridLayout(3, 1));
        JPanel difficulty = new JPanel();
        JRadioButton easy = new JRadioButton("Easy");
        JRadioButton normal = new JRadioButton("Normal");
        JRadioButton hard = new JRadioButton("Hard");
        difficulty.add(easy);
        difficulty.add(normal);
        difficulty.add(hard);
        ButtonGroup difficultyGroup = new ButtonGroup(); 
        difficultyGroup.add(easy); 
        difficultyGroup.add(normal); 
        difficultyGroup.add(hard); 
        panel.add(difficulty);
        JPanel speed = new JPanel();
        JRadioButton slow = new JRadioButton("Slow"); 
        JRadioButton norm = new JRadioButton("Normal"); 
        JRadioButton fast = new JRadioButton("Fast"); 
        speed.add(slow);
        speed.add(norm); 
        speed.add(fast); 
        ButtonGroup speedGroup = new ButtonGroup();
        speedGroup.add(slow);
        speedGroup.add(norm);
        speedGroup.add(fast); 
        panel.add(speed); 
        easy.setSelected(true);
        slow.setSelected(true);

        JButton newGame = new JButton("New Game");
        panel.add(newGame); 

        cp.add(panel);

        newGame.addActionListener(event -> {
            if(easy.isSelected()) diff = 1;
            else if(normal.isSelected()) diff = 2; 
            else diff = 3; 
            if(slow.isSelected()) spd = 2; 
            else if(norm.isSelected()) spd = 5;
            else spd = 8; 
            window.getContentPane().removeAll();
            var gPanel = new GamePanel(window, diff, spd);
            gPanel.init();
            window.pack();
            window.revalidate();
        });


        
    }
}