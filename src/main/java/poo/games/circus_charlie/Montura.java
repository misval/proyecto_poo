package poo.games.circus_charlie;

import poo.games.ObjetoGrafico;
import poo.games.Personaje;

import java.awt.*;

public abstract class  Montura extends ObjetoGrafico {
    Personaje charlie;

    Montura(String sprite) {
        super(sprite);
    }

    public void addCharlie(Personaje charlie) {
           this.charlie = charlie;
    }

    public void seguirCharlie() {
        this.setX(charlie.getX());
        this.setY(charlie.getY() - this.getHeight());
    }

    public abstract void moverse(Integer direccion, double desplazamiento);

    public void draw(Graphics2D g){
        g.drawImage(sprite, (int)this.getX(), (int)this.getY(), this.getWidth(), this.getHeight(),null);
    }
}
