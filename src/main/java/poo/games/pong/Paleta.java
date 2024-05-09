package poo.games.pong;

import java.awt.*;
import java.awt.image.BufferedImage;

import poo.games.ObjetoGrafico;

public class Paleta extends ObjetoGrafico{
    public Rectangle colision;

    public Paleta(BufferedImage sprite, int alto, int ancho, double x, double y){
        super(sprite, alto, ancho, x, y);
        colision  = new Rectangle((int)this.x,(int)this.y,this.ancho,this.alto);
        System.out.println(colision.getY());
        System.out.println(colision.getX());
    }

    public void setY(double nuevaPosicion){
        this.y = nuevaPosicion;
        colision.setLocation((int)x, (int)y);
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
