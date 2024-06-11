package poo.games.circus_charlie;

import poo.games.Camara;
import poo.games.Fondo;
import poo.games.Mundo;
import poo.games.Personaje;

import java.awt.*;

public class Nivel1 extends Nivel {
    private Leon leon;
    private Aro[] aros = new Aro[3];
    private Jarron[] jarrones = new Jarron[3];
    private int[] despJarrones = {50, 20, 200 ,150 ,230};
    private int[] despAros = {50, 20, 200 ,150 ,230};

    Nivel1() {
        super();
        Mundo m = Mundo.getInstance();

        jarrones[0] = new Jarron("imagenes/JarronEstatico.png");
        jarrones[1] = new Jarron("imagenes/JarronEstatico.png");
        jarrones[2] = new Jarron("imagenes/JarronEstatico.png");

        jarrones[0].setX(560);
        jarrones[0].setY(355);

        jarrones[1].setX(1060);
        jarrones[1].setY(355);

        jarrones[2].setX(1560);
        jarrones[2].setY(355);

        jarrones[0].setColision(new Rectangle((int)jarrones[0].getX()+9, (int)jarrones[0].getY()+20, 27, 40));      
        jarrones[1].setColision(new Rectangle((int)jarrones[1].getX()+9, (int)jarrones[1].getY()+20, 27, 40));
        jarrones[2].setColision(new Rectangle((int)jarrones[2].getX()+9, (int)jarrones[2].getY()+20, 27, 40));

        charlie = new Personaje("imagenes/ImagenCharlieEstatica.png");
        leon = new Leon("imagenes/LeonQuieto.png");

        leon.setX(120);
        leon.setY(355);

        charlie.setX(leon.getX() - leon.getWidth()/3);
        charlie.setY(leon.getY() - leon.getHeight());
        charlie.setPOSICION_Y_PISO((int)leon.getY()-leon.getHeight());
        charlie.quieto();

        charlie.setColision(new Rectangle((int)charlie.getX()+10,(int)charlie.getY()+13,19, 47));
        leon.setColision(new Rectangle((int)leon.getX()+18,(int)leon.getY(),57, 32));

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

        jarrones[0].display(g);
        jarrones[1].display(g);
        jarrones[2].display(g);

        charlie.display(g);
        leon.display(g);

        g.draw(charlie.getColision());
        g.draw(leon.getColision());

        g.translate(-cam.getX(), -cam.getY());
    };

    public void update() {
        charlie.getColision().setLocation((int)charlie.getX()+10, (int)charlie.getY()+13);
        leon.getColision().setLocation((int)leon.getX()+18, (int)leon.getY());

        leon.setX(charlie.getX()-leon.getWidth()/3);
        leon.setY(charlie.getY()+charlie.getHeight());

        if((jarrones[0].getX() + jarrones[0].getWidth()) < (-cam.getX())) {
            jarrones[0].setX(jarrones[2].getX() + jarrones[2].getWidth() + 500);
            jarrones[0].getColision().setLocation((int)jarrones[0].getX()+9, (int)jarrones[0].getY()+20);
        } 
        else if((jarrones[1].getX() + jarrones[1].getWidth()) < (-cam.getX())) {
            jarrones[1].setX(jarrones[0].getX() + jarrones[0].getWidth() + 500);
            jarrones[1].getColision().setLocation((int)jarrones[1].getX()+9, (int)jarrones[1].getY()+20);
        }
        else if((jarrones[2].getX() + jarrones[2].getWidth()) < (-cam.getX())) {
            jarrones[2].setX(jarrones[1].getX() + jarrones[1].getWidth() + 500);
            jarrones[1].getColision().setLocation((int)jarrones[2].getX()+9, (int)jarrones[2].getY()+20);
        }

    };

    public Personaje getCharlie() {
        return charlie;
    }

    public Camara getCam() {
        return cam;
    }
}
