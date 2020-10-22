package model.Images;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Image;
//import java

public class ImageStore {

    public static BufferedImage turret;
    public static BufferedImage z1;
    public static BufferedImage z2;

    static {
        String cwd = System.getProperty("user.dir");
        System.out.println("cwd: " + cwd + "\n");
        turret = readImage("model/images/turret.png", 80, 60);
        z1 = readImage("model/images/z1.png", 50, 30);
        z2 = readImage("model/images/z2.png", 50, 30);
    }

    public static BufferedImage readImage(String path, int width, int height) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = resizedImage.createGraphics();
            g2.drawImage(tmp, 0, 0, null); 
            g2.dispose(); 
            return resizedImage;
        } catch (Exception e) {
            System.out.println("Image file load error");
        }
        return null; 
    }

    
}
