package poo.games;

import java.awt.image.BufferedImage;
import java.awt.*;

public class Fondo extends ObjetoGrafico{
    private Rectangle bordeSup, bordeInf, bordeIzq, bordeDer;

    public Fondo(BufferedImage fondo, int alto, int ancho, double x, double y){
        super(fondo, alto, ancho, x, y);
        bordeSup = new Rectangle((int)x, (int)y, ancho, 3);
        bordeInf = new Rectangle((int)x, alto, ancho, 3);
        bordeIzq = new Rectangle((int)x, (int)y, 3, alto);
        bordeDer = new Rectangle(ancho, (int)y, 3, alto);
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
