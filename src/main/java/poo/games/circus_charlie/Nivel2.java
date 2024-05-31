package poo.games.circus_charlie;

import poo.games.Mundo;

import java.awt.*;

public class Nivel2 extends Nivel {
    Nivel2() {
        super();
    };

    @Override
    public void draw(Graphics2D g) {
        Mundo m = Mundo.getInstance();
        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        System.out.println("Estoy aca? " + charlie.getY());
        charlie.display(g);

        g.translate(-cam.getX(), -cam.getY());
    }

    public void update() {};
}

