package poo.games;

import java.awt.image.BufferedImage;


public class ObjetoGrafico {
    protected BufferedImage sprite;
    protected int ancho, alto;
    protected double x, y;

    public ObjetoGrafico(BufferedImage sprite, int alto, int ancho, double x, double y){
        this.sprite = sprite;
        this.alto = alto;
        this.ancho = ancho;
        this.x = x;
        this.y = y;
    }

    public void pintarse(){}

}
