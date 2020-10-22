package controller;

import view.GameCanvas;
import view.GamePanel;
import view.GamePanel.GameState;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Random;
import model.Bullet;
import model.FastEnemy;
import model.SlowEnemy;
import model.Turret;
import model.Images.ImageStore;

public class GameEventListener implements MouseListener, ActionListener {
    
    private int slowSpeed; 
    private int fastSpeed;
    private int hits = 0; 
    private GamePanel panel; 
    private int radius = 10; 
    private Random rand; 
    private int difficulty;
    public GameEventListener(GamePanel panel, int difficulty, int speed) {
        this.panel = panel; 
        this.difficulty = difficulty; 
        this.slowSpeed = speed;
        this.fastSpeed = speed+5; 
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameCanvas canvas = panel.getCanvas();
        if(e.getSource() == panel.getNewGame()) {
            canvas.reset();
            hits = 0; 
            panel.setHits(hits);
            panel.setTurret();
            panel.setState(GamePanel.GameState.PLAYING);
            canvas.repaint();
            canvas.startGameTimer();

        } else if(e.getSource() == canvas.getTimer()) {
            if(canvas.getBullets()!=null) {
                for(int i = 0; i < canvas.getBullets().size(); i++) {
                    canvas.getBullets().get(i).translate(); 
                }
                canvas.repaint();
            }
        } else if(e.getSource() == canvas.getzTimer()) {
            for(int i=0; i < difficulty; i++) {
                rand = new Random(); 
                switch(rand.nextInt(2)) {
                    case 0 :
                    if(rand.nextInt(2) == 1) {  
                        FastEnemy enemy = new FastEnemy(rand.nextInt(700), 0, fastSpeed, panel);
                        enemy.setImage(ImageStore.z1);
                        canvas.getEnemies().add(enemy);
                        break;
                    } else {
                        FastEnemy enemy = new FastEnemy(rand.nextInt(700), 700, fastSpeed, panel);
                        enemy.setImage(ImageStore.z1);
                        canvas.getEnemies().add(enemy);
                        break;
                    }
                    case 1 : 
                    if(rand.nextInt(2) == 1) {
                        SlowEnemy enemy = new SlowEnemy(0, rand.nextInt(700), slowSpeed, panel);
                        enemy.setImage(ImageStore.z2);
                        canvas.getEnemies().add(enemy);
                        break; 
                    } else {
                        SlowEnemy enemy = new SlowEnemy(700, rand.nextInt(700), slowSpeed, panel);
                        enemy.setImage(ImageStore.z2);
                        canvas.getEnemies().add(enemy);
                        break;
                    }                
                }
            }
        } else if(e.getSource() == canvas.getzRunTimer()) {
            for(int i = 0; i < canvas.getEnemies().size(); i++) {
                canvas.getEnemies().get(i).translate();
                if(canvas.getTurret().getBoundingBox().contains(canvas.getEnemies().get(i).getBoundingBox())){
                    panel.setState(GameState.GAMEOVER);
                    canvas.stopGameTimers();
                    break;
                }
                canvas.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int dx, dy; 
        Turret turret = panel.getCanvas().getTurret();
        if(panel.getState()==GamePanel.GameState.PLAYING) {
            if(e.getX() <= turret.getX()) {
                dx = turret.getX();
            }else if(e.getX() > turret.getX() && e.getX() < turret.getX()+turret.getImage().getWidth()) {
                dx = e.getX();
            }else {
                dx = turret.getX()+turret.getImage().getWidth();
            }
            
            if(e.getY() <= turret.getY()) {
                dy = turret.getY();
            }else if(e.getY() > turret.getY() && e.getY() < turret.getY()+turret.getImage().getHeight()) {
                dy = e.getY();
            }else {
                dy = turret.getY()+turret.getImage().getHeight();
            }
            panel.getCanvas().getBullets().add(new Bullet(dx, dy, e.getX(), e.getY(), radius, panel));
        }
        else {
            return;
        }
    }

    public void increaseHit() {
        hits++; 
        panel.setHits(hits);
        int runDelay = panel.getCanvas().getzRunTimer().getDelay();
        int spawnDelay = panel.getCanvas().getzTimer().getDelay();
        if(hits % 10 == 0) {
            panel.getCanvas().getzRunTimer().setDelay(runDelay-5);
            panel.getCanvas().getzTimer().setDelay(spawnDelay-100);
            fastSpeed += 2; 
            slowSpeed += 2;
            if(hits % 20 == 0) {
                difficulty++;
            }             
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
    }
    @Override
    public void mouseExited(MouseEvent e) {
    }

}
