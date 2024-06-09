package poo.games.circus_charlie;

import poo.games.*;

import java.awt.*;

public abstract class Nivel {
    protected Fondo fondo;
    protected Personaje charlie;
    protected Camara cam;
    protected Meta meta;

    public Camara getCam() {
        return cam;
    }
    public Personaje getCharlie() {
        return charlie;
    }

    public Meta getMeta() {
        return meta;
    }

    public abstract void draw(Graphics2D g);
    public abstract void update();

}
