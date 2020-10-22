package model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface IRender {
    void render(Graphics2D g2);
    Rectangle getBoundingBox(); 
    void translate();
}
