package poo.games.pong;

import poo.games.ObjetoGrafico;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Pelota extends ObjetoGrafico{
    public Rectangle colision;

    public Pelota(BufferedImage sprite, int alto, int ancho, double x, double y){
        super(sprite, alto, ancho, x, y);
        colision  = new Rectangle((int)x,(int)y,ancho,alto);
        System.out.println(colision.getWidth());
        System.out.println(colision.getHeight());
    }

    
    public void setX(double nuevaPosicionX){
        x = nuevaPosicionX;
        colision.setLocation((int)x, (int)y);
    }

    public void setY(double nuevaPosicionY){
        y = nuevaPosicionY;
        colision.setLocation((int)x, (int)y);
    }

    public double getX(){
        return x;
    }
    public double getY(){
        return y;
    }

    public void update(double delta){

    }

    public void draw(Graphics2D g){
        g.drawImage(sprite,(int)x,(int)y, ancho, alto,null);
    }

}
