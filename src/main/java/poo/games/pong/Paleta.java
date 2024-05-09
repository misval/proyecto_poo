package poo.games.pong;

import java.awt.*;
import java.awt.geom.*;

import poo.games.ObjetoGrafico;

public class Paleta extends ObjetoGrafico{
    public Rectangle colision;

    public Paleta(){
        super(15, 15, 20, 20);
        colision  = new Rectangle(x,y,ancho,alto);
    }

    public void setY(int nuevaPosicion){
        this.y = nuevaPosicion;
        colision.setLocation(x, y);
    }

    public int getY(){
        return y;
    }
}
