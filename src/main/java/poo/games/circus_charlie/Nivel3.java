package poo.games.circus_charlie;

import poo.games.Camara;
import poo.games.Fondo;
import poo.games.Mundo;
import poo.games.Personaje;

import java.awt.*;


public class Nivel3 extends Nivel {
    private Pelota[] pelotas = new Pelota[10];
    private Pelota pelotaActual;

    Nivel3() {
        super();
        Mundo m = Mundo.getInstance();

//      aca voy a iterar por cada pelota y le doy sus caracteristicas correspondientes+
        for (int i = 0; i < 10; i++) {
            this.pelotas[i] = new Pelota("imagenes/SpritePelota.jpeg");
            this.pelotas[i].setY(339);
        }

//      creo variable pelotaActual
        pelotaActual = pelotas[0];
        pelotaActual.setX(300);

        charlie = new Personaje("imagenes/Caldero.png");
        charlie.setMontura(pelotaActual);

//      seteo la posicion Y de charlie dependiendo el Rectangle de la pelota y la posicion X se la doy a la pelota
//      y despues a charlie, esta sera solo la primera vez, luego la pelota sigue a charlie
        charlie.setX(pelotaActual.getX());
        charlie.setY(pelotaActual.getY() - pelotaActual.getHeight());
        charlie.quieto();

        pelotaActual.addCharlie(charlie);
        cam = new Camara(0, 0);
        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/FondoCircusCharlieFinal.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());

    }

    public Camara getCam() {
        return cam;
    }
    public Personaje getCharlie() {
        return charlie;
    }

    @Override
    public void draw(Graphics2D g) {
        Mundo m = Mundo.getInstance();
        g.translate(cam.getX(), cam.getY());

        fondo.display(g);
        m.display(g);
        pelotaActual.display(g);
        charlie.display(g);

        g.translate(-cam.getX(), -cam.getY());
    }

    public void update() {
//      Si la pelota actual esta colisionando con el charlie, le seteo la misma x que el charlie
//      Sino la pelota se movera a la izquierda como normalmente lo hace
//      Si la pelotaActual colisiona con otra pelota esta pelota sale para la izquierda y la otra pelota sale para la derecha

//      charlie.setY(pelotaActual.getY() - pelotaActual.getHeight());
        pelotaActual.setX(charlie.getX());
//      System.out.println("UPDATE CHARLIE NIVEL 3" + charlie.getY());
//      System.out.println("UPDATE PELOTA NIVEL 3" + pelotaActual.getY());
    };
}
