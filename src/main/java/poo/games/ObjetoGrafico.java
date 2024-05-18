package poo.games;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public abstract class ObjetoGrafico {
    protected BufferedImage sprite;
    protected Dimension dimensiones;
    protected Point punto;
    protected Rectangle colision;

    public ObjetoGrafico(String sprite){
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(sprite));;
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObjetoGrafico(String sprite, Dimension dimensiones, Point punto){
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(sprite));;
            this.dimensiones = dimensiones;
            this.punto = punto;
            colision  = new Rectangle(punto, dimensiones);
        } catch (Exception e) {
            System.out.println(e);
        }

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

    public void setSprite(String sprite) {
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(sprite));
        } catch (Exception e) {
            System.out.println(e);
        }  
    }

    public void setDimesiones(Dimension dimensiones){
        this.dimensiones = dimensiones;
    }

    public void setPunto(Point punto){
        this.punto = punto;
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
