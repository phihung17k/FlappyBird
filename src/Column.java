
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
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
public class Column extends Rectangle{
    Color color;
    int pos;
    public Column(Color color, int x, int y, int width, int height, int pos) {
        super(x, y, width, height);
        this.color = color;
        this.pos=pos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public void drawColumn(Graphics g){
        g.setColor(color);
        ImageIcon icon=null;
        if(pos==1)
            icon=new ImageIcon("ongT.png");
        else if(pos==0)
            icon=new ImageIcon("ongD.png");
        Image img=icon.getImage();
        g.drawImage(img, x, y, width, height, null);
    }
}
