package model;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import view.GamePanel;
import view.GamePanel.GameState;

    
public abstract class Enemy implements IRender {
    
    private int speed;
    private double xdiff; 
    private double ydiff; 
    private double x1; 
    private double y1; 
    private double dx = 360;
    private double dy = 360; 
    private int x; 
    private int y; 
    private BufferedImage image; 
    private double direction;
    private GamePanel panel; 

    public Enemy(int x, int y, int speed, GamePanel panel) { 
        this.x = x; 
        this.y = y; 
        this.speed = speed; 
        this.panel=panel;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    @Override
    public void translate() {
        x1 = x; 
        y1 = y; 
        xdiff = dx - x;
        ydiff = dy - y; 
        direction = Math.atan(ydiff / xdiff);  
        if(x <= dx) {
            x1 = (x1 + (speed * Math.cos(direction)));
            y1 = (y1 + (speed * Math.sin(direction)));
            x = (int)x1;
            y = (int)y1; 
        } else {
            x1 = (x1 - (speed * Math.cos(direction)));
            y1 = (y1 - (speed * Math.sin(direction)));
            x = (int)x1;
            y = (int)y1;
        } 

        if(this.getBoundingBox().intersects(panel.getCanvas().getTurret().getBoundingBox())){
            panel.setState(GameState.GAMEOVER);
        }
    }
}
