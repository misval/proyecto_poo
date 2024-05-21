package poo.games.pong;

import java.awt.*;

import poo.games.ObjetoGrafico;

public class Paleta extends ObjetoGrafico{

    public Paleta(String sprite){
        super(sprite);
    }

    public Paleta(String sprite, Dimension dimensiones, Point punto){
        super(sprite, dimensiones, punto);
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
