package poo.games.pong;

import poo.games.ObjetoGrafico;
import java.awt.*;

public class Pelota extends ObjetoGrafico{

    public Pelota(String sprite){
        super(sprite);
    }

    public Pelota(String sprite, Dimension dimensiones, Point punto){
        super(sprite, dimensiones, punto);
    }

    public void moverseIzquierda(double desplazamiento){
        this.setX(this.getX() - desplazamiento);
        this.getColision().setLocation((int)this.getX(), (int)this.getY());
    }

    public void moverseDerecha(double desplazamiento){
        this.setX(this.getX() + desplazamiento);
        this.getColision().setLocation((int)this.getX(), (int)this.getY());
    }

    public void moverseArriba(double desplazamiento){
        this.setY(this.getY() - desplazamiento);
        this.getColision().setLocation((int)this.getX(), (int)this.getY());
    }

    public void moverseAbajo(double desplazamiento){
        this.setY(this.getY() + desplazamiento);
        this.getColision().setLocation((int)this.getX(), (int)this.getY());
    }

    public void update(double delta){}

    public void draw(Graphics2D g){
        g.drawImage(sprite, (int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight(),null);
    }


}
