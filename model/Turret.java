package model;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import java.awt.Graphics2D;
import java.awt.Color;

public class Turret {
    
    private int x; 
    private int y; 
    private BufferedImage image; 
    private Color color; 

    public Turret(int x, int y, Color color) { 
        this.x = x; 
        this.y = y; 
        this.color = color; 
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

    public void render(Graphics2D g2) {
        g2.setColor(color);
        g2.drawImage(getImage(), null, x, y);
    }

    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, image.getWidth(), image.getHeight()); 
    }
    
    public void setColor(Color color) {
        this.color = color;
    }
}
