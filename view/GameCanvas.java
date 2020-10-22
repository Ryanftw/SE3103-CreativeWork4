package view;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Font;
import javax.swing.Timer;

import model.Bullet;
import model.IRender;
import model.Turret;


public class GameCanvas extends JPanel {
    
    private GamePanel panel;
    private Turret turret; 
    private ArrayList<Bullet> bullets = new ArrayList<>(); 
    private ArrayList<IRender> enemies = new ArrayList<>();
    private Timer timer;
    private Timer zTimer; 
    private Timer zRunTimer;

    public GameCanvas(GamePanel panel) {
        this.panel = panel; 
        setBackground(Color.black);
        setPreferredSize(new Dimension(750, 750));
    }

    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g; 

        if(panel.getState() == GamePanel.GameState.READY) {
            g2.setColor(Color.red); 
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("Select New Game to start", 160, 375);
        } else {
            if(panel.getState() == GamePanel.GameState.GAMEOVER) {
                g2.setColor(Color.red); 
                g2.setFont(new Font("Arial", Font.BOLD, 36));
                g2.drawString("GAME OVER", 250, 375);
            } else {
                turret.render(g2);
                if(bullets!=null) {
                    for(var b: bullets) {
                        b.render(g2);
                    }
                }
                if(enemies!=null) {
                    for(var e: enemies) {
                        e.render(g2);
                    }
                }
            }
        }
    }

    public void reset() {
        bullets.clear();
        enemies.clear();
    }


    public Turret getTurret() {
        return turret;
    }

    public void setTurret(Turret turret) {
        this.turret = turret;
    }

    public ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(Bullet bullet) {
        bullets.add(bullet);
    }

    public void startGameTimer() {
        timer = new Timer(50, panel.getListener());
        timer.setDelay(50);
        timer.setRepeats(true);
        timer.start();
        
        zTimer = new Timer(1200, panel.getListener()); 
        zTimer.setDelay(1200);
        zTimer.setRepeats(true);
        zTimer.start();

        zRunTimer = new Timer(5, panel.getListener()); 
        zRunTimer.setDelay(45); 
        zRunTimer.setRepeats(true);
        zRunTimer.start();
    }

    public void stopGameTimers() {
        timer.stop();
        zTimer.stop();
        zRunTimer.stop();
        timer.setDelay(50);
        zTimer.setDelay(1200);
        zRunTimer.setDelay(45); 
    }

    public Timer getTimer() {
        return timer;
    }

    public Timer getzTimer() {
        return zTimer;
    }

    public ArrayList<IRender> getEnemies() {
        return enemies;
    }

    public Timer getzRunTimer() {
        return zRunTimer;
    }
}
