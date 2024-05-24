package poo.games;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import java.awt.*;


public abstract class ObjetoGrafico {
    protected BufferedImage sprite;
    protected Dimension dimensiones;
    protected Point punto;
    protected Rectangle colision;

    public ObjetoGrafico(String sprite){
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(sprite));
            this.dimensiones = new Dimension(this.sprite.getWidth(), this.sprite.getHeight());
            this.punto = new Point(0,0);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ObjetoGrafico(String sprite, Dimension dimensiones, Point punto){
        try {
            this.sprite = ImageIO.read(getClass().getClassLoader().getResourceAsStream(sprite));
            this.dimensiones = dimensiones;
            this.punto = punto;
            colision = new Rectangle(punto, dimensiones);
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

    public void display(Graphics2D g,int cantFrames ,int width, int height) {
        try {
            for(int i = 0; i < cantFrames; i++){
                TimeUnit.SECONDS.sleep(1);
                g.drawImage(sprite.getSubimage(i * width, 0, width, height), (int) this.getX(), (int) this.getY(), null);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
          
    }

    public void display(Graphics2D g) {
        g.drawImage(sprite, (int) this.getX(), (int) this.getY(), null);
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
