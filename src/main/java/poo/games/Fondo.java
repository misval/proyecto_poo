package poo.games;

import java.awt.image.BufferedImage;
import java.awt.*;

public class Fondo extends ObjetoGrafico{
    private Rectangle bordeSup, bordeInf, bordeIzq, bordeDer;

    public Fondo(String sprite){
        super(sprite);
    }

    public Fondo(String sprite, Dimension dimensiones, Point punto){
        super(sprite, dimensiones, punto);
        bordeSup = new Rectangle((int)this.getX(), (int)this.getY(), this.getWidth(), 3);
        bordeInf = new Rectangle((int)this.getX(), this.getHeight(), this.getWidth(), 3);
        bordeIzq = new Rectangle((int)this.getX(), (int)this.getY(), 3, this.getHeight());
        bordeDer = new Rectangle(this.getWidth(), (int)this.getY(), 3, this.getHeight());
    }

    public Rectangle getBordeSup() {
        return bordeSup;
    }

    public Rectangle getBordeInf() {
        return bordeInf;
    }

    public Rectangle getBordeIzq() {
        return bordeIzq;
    }

    public Rectangle getBordeDer() {
        return bordeDer;
    }

    public BufferedImage getFondo(){
        return sprite;
    }

    public void update(double delta){

    }

}
