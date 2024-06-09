package poo.games.circus_charlie;

import poo.games.ObjetoGrafico;
import poo.games.Personaje;

import java.awt.*;

public abstract class  Montura extends ObjetoGrafico {
    Montura(String sprite) {
        super(sprite);
    }

    public abstract void moverse(Integer direccion);

    public void draw(Graphics2D g){
        g.drawImage(sprite, (int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight(),null);
    }
}
