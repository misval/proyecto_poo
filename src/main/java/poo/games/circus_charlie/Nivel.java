package poo.games.circus_charlie;

import poo.games.*;

import java.awt.*;

public abstract class Nivel {
    private Fondo fondo;
    private Personaje charlie;
    private Camara cam;

    public Camara getCam() {
        return cam;
    }

    public Personaje getCharlie() {
        return charlie;
    }

    public void setCharlie(Personaje charlie) {
        this.charlie = charlie;
    }

    Nivel() {
        Mundo m = Mundo.getInstance();
        this.charlie= new Personaje("imagenes/Caldero.png");
        this.charlie.setX(300);
        this.charlie.setY(360);
        this.charlie.quieto();

        cam = new Camara(0, 0);
        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/FondoCircusCharlieFinal.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());
    }

    public void draw(Graphics2D g) {
//        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Mundo m = Mundo.getInstance();
        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        charlie.display(g);

        g.translate(-cam.getX(), -cam.getY());
    }
}
