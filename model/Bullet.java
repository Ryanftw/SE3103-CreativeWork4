package model;

import java.awt.Graphics2D;

import view.GamePanel;
import java.awt.Rectangle;
import java.awt.Color;

public class Bullet {
    
    private final int speed = 10; 
    private double x; 
    private double y; 
    private double dx;
    private double dy; 
    private int radius; 
    private GamePanel panel; 
    private double xdiff; 
    private double ydiff; 
    private double direction; 
    public Bullet(double x, double y, double dx, double dy, int radius, GamePanel panel) {
        this.x = x; 
        this.y = y; 
        this.dx = dx; 
        this.dy = dy; 
        this.radius = radius; 
        this.panel = panel;
        ydiff = dy-y;
        if(ydiff < 0) ydiff *= -1; 
        xdiff = dx-x; 
        if(xdiff < 0) xdiff *= -1; 
    }

    public void render(Graphics2D g2) {
        g2.setColor(Color.red);
        g2.fillOval((int)x, (int)y, radius, radius);
    }

    public void translate() {
        
        xdiff = dx - x;
        ydiff = dy - y; 
        direction = Math.atan(ydiff / xdiff);  
        if(x <= dx) {
            x = (x + (speed * Math.cos(direction)));
            y = (y + (speed * Math.sin(direction)));
            if(Math.abs(x-dx) < 5.5 && Math.abs(y-dy) < 5.5) panel.getCanvas().getBullets().remove(this);
        } else {
            x = (x - (speed * Math.cos(direction)));
            y = (y - (speed * Math.sin(direction)));
            if(Math.abs(x-dx) < 5.5 && Math.abs(y-dy) < 5.5) panel.getCanvas().getBullets().remove(this);
        } 
        for(int i = 0; i < panel.getCanvas().getEnemies().size(); i++) {
            if(panel.getCanvas().getEnemies().get(i).getBoundingBox().intersects(getBoundingBox())) {
                panel.getCanvas().getEnemies().remove(i);
                panel.getCanvas().getBullets().remove(this);
                panel.getListener().increaseHit();
                return;
            }
        }
    }

    public void removeBullet() {
        if(x == dx && y == dy) {
            panel.getCanvas().getBullets().remove(this);
        }
    }

    public Rectangle getBoundingBox() {
        return new Rectangle((int)x, (int)y, radius*2, radius*2);
    }
}
