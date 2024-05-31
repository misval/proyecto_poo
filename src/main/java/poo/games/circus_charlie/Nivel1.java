package poo.games.circus_charlie;

import poo.games.Mundo;

import java.awt.*;

public class Nivel1 extends Nivel {
    Nivel1() {
        super();
    }

    @Override
    public void draw(Graphics2D g) {
        Mundo m = Mundo.getInstance();
        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        charlie.display(g);

        g.translate(-cam.getX(), -cam.getY());

    };

    public void update() {};
}
