package model;

import java.awt.Graphics2D;

import view.GamePanel;

public class SlowEnemy extends Enemy {

    public SlowEnemy(int x, int y, int speed, GamePanel panel) {
        super(x, y, speed, panel); 
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(getImage(), null, getX(), getY()); 
    }

    
}
