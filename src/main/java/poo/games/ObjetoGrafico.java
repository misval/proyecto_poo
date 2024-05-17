package poo.games;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;


public abstract class ObjetoGrafico {
    protected BufferedImage sprite;
    protected Dimension dimensiones;
    protected Point punto;
    protected Rectangle colision;

    public ObjetoGrafico(BufferedImage sprite, Dimension dimensiones, Point punto){
        this.sprite = sprite;
        this.dimensiones = dimensiones;
        this.punto = punto;
        colision  = new Rectangle(punto, dimensiones);
    }

    public Rectangle getColision(){
        return colision;
    }

    public void setColision(Rectangle colision) {
        this.colision = colision;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public int getWidth() {
        return (int)dimensiones.getWidth();
    }

    public void setAncho(int width) {
        dimensiones.setSize(width, dimensiones.getHeight());
    }

    public int getHeight() {
        return (int)dimensiones.getHeight();
    }

    public void setHeight(int height) {
        dimensiones.setSize(dimensiones.getWidth(), height);
    }

    public double getX() {
        return punto.getX();
    }

    public void setX(double x) {
        punto.setLocation(x, punto.getY());
    }

    public double getY() {
        return punto.getY();
    }

    public void setY(double y) {
        punto.setLocation(punto.getX(), y);
    }
}
