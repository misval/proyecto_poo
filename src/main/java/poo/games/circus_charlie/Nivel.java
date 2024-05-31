package poo.games.circus_charlie;

import poo.games.*;

import java.awt.*;

public abstract class Nivel {
    protected Fondo fondo;
    protected Personaje charlie;
    protected Camara cam;

    public Camara getCam() {
        return cam;
    }
    public Personaje getCharlie() {
        return charlie;
    }

    public abstract void draw(Graphics2D g);
    public abstract void update();
}
