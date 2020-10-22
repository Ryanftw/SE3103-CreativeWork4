package view;

import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import controller.GameEventListener;
import model.Turret;
import model.Images.ImageStore;
import java.awt.BorderLayout;

import javax.swing.JButton;

public class GamePanel {

    public enum GameState {
		READY, PLAYING, GAMEOVER
    }
    
    private int spd;
    private int diff;
    private JLabel hitCount = new JLabel("Kills : 0");
    private GameEventListener listener; 
    private GameState state = GameState.PLAYING;
    private JFrame window; 
    private GameCanvas canvas; 
    private JButton newGame = new JButton("New Game");


    public GamePanel(JFrame window, int diff, int spd) {
        this.window = window; 
        this.diff = diff; 
        this.spd = spd; 
    }
    
    public void init() {
        
        Container cp = window.getContentPane();

        canvas = new GameCanvas(this);
        cp.add(BorderLayout.CENTER, canvas); 
        JPanel southPanel = new JPanel(); 
        JPanel northPanel = new JPanel(); 
        northPanel.add(hitCount); 
        cp.add(BorderLayout.NORTH, northPanel); 
        southPanel.add(newGame);
        cp.add(BorderLayout.SOUTH, southPanel);

        listener = new GameEventListener(this, diff, spd); 
        newGame.addActionListener(listener);
        canvas.addMouseListener(listener);

        setTurret();
        canvas.repaint();
        canvas.startGameTimer();

    }

    public void setTurret() {
        Turret turret = canvas.getTurret();
        turret = new Turret(330, 325, Color.red);
        turret.setImage(ImageStore.turret);
        canvas.setTurret(turret);
    }

    public GameState getState() {
        return state;
    }

    public GameCanvas getCanvas() {
        return canvas;
    }

    public JButton getNewGame() {
        return newGame;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameEventListener getListener() {
        return listener;
    }

    public void setHits(int hits) {
        hitCount.setText("Kills : " + hits);
    }

    public int getDiff() {
        return diff;
    }

    public int getSpd() {
        return spd;
    }
}
