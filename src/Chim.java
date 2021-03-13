
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Win 10
 */
public class Chim extends Rectangle{
    String filename;
    int i=0;
    public Chim(String filename, int x, int y, int width, int height) {
        super(x, y, width, height);
        this.filename = filename;
       
    }
    public void drawChim(Graphics g){
        ImageIcon icon=new ImageIcon(filename);
        Image img=icon.getImage();
//        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
//        at.rotate(Math.toRadians(i++));
//        g2d.setTransform(at);
        g.drawImage(img, x, y, width, height, null);
        
    }
    
}
