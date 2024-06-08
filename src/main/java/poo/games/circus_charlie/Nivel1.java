package poo.games.circus_charlie;

import poo.games.Camara;
import poo.games.Fondo;
import poo.games.Mundo;
import poo.games.Personaje;

import java.awt.*;

public class Nivel1 extends Nivel {
    private Leon leon;

    Nivel1() {
        super();
        Mundo m = Mundo.getInstance();
        
        charlie = new Personaje("imagenes/ImagenCharlieEstatica.png");
        leon = new Leon("imagenes/LeonQuieto.png");

        leon.setX(120);
        leon.setY(120);

        charlie.setX(leon.getX());
        charlie.setY(leon.getY() - leon.getHeight());
        charlie.quieto();

        cam = new Camara(0, 0);
        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/FondoCircusCharlieFinal.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());

    }

    @Override
    public void draw(Graphics2D g) {
        Mundo m = Mundo.getInstance();
        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        charlie.display(g);
        leon.display(g);

        g.translate(-cam.getX(), -cam.getY());
    };

    public void update() {};

    public Personaje getCharlie() {
        return charlie;
    }

    public Camara getCam() {
        return cam;
    }
}
