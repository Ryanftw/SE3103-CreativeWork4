package model;

import java.awt.Graphics2D;

import view.GamePanel;

public class FastEnemy extends Enemy {

    private int health = 2; 

    public FastEnemy(int x, int y, int speed, GamePanel panel) {
        super(x, y, speed, panel); 
    }

    @Override
    public void render(Graphics2D g2) {
        g2.drawImage(getImage(), null, getX(), getY()); 
    }    

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
}
