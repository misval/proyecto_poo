package poo.games;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class ObjetoGrafico {
    BufferedImage sprite;
    protected int ancho, alto;
    protected int x, y;

    public ObjetoGrafico( int alto, int ancho, int x, int y){
        //this.sprite = sprite;
        this.alto = alto;
        this.ancho = ancho;
        this.x = x;
        this.y = y;
    }

    public void pintarse(){}

}
