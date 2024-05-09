package poo.games.pong;

import poo.games.ObjetoGrafico;
import java.awt.*;

public class Pelota extends ObjetoGrafico{
    public Rectangle colision;

    public Pelota(){
        super(15, 15, 20, 20);
        colision  = new Rectangle(x,y,ancho,alto);
    }

    public void setX(int nuevaPosicionX){
        x = nuevaPosicionX;
        colision.setLocation(x, y);
    }

    public void setY(int nuevaPosicionY){
        y = nuevaPosicionY;
        colision.setLocation(x, y);
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }




}
