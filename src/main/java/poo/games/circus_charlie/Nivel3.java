package poo.games.circus_charlie;

import poo.games.Camara;
import poo.games.Fondo;
import poo.games.Mundo;
import poo.games.Personaje;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Nivel3 extends Nivel {
    private Pelota[] pelotas = new Pelota[10];
    private Boolean colsionPelotas = false;
    private Pelota aux;
    private Pelota pelotaActual;
    private Pelota pelotaSiguiente;
    private Pelota pelotaAuxiliar;

    Nivel3() {
        super();

        this.iniciarse();
    }

    public void iniciarse() {
        Mundo m = Mundo.getInstance();

        try(BufferedReader mapFile = new BufferedReader(new FileReader("src/main/resources/files/mapFile.txt"))) {
            if(mapFile.ready()) {
                Integer pos1 = Integer.parseInt(mapFile.readLine());
                pelotas[0] = new Pelota("imagenes/SpritePelota.jpeg");
                pelotas[0].setX(pos1);
                pelotas[0].setY(339);
                pelotas[0].setColision(new Rectangle((int) pelotas[0].getX(), (int) pelotas[0].getY(), pelotas[0].getWidth(), pelotas[0].getHeight()));
                Integer pos2 = Integer.parseInt(mapFile.readLine());
                pelotas[1] = new Pelota("imagenes/SpritePelota.jpeg");
                pelotas[1].setX(pos2);
                pelotas[1].setY(339);
                pelotas[1].setColision(new Rectangle((int)pelotas[1].getX(), (int) pelotas[1].getY(), pelotas[1].getWidth(), pelotas[1].getHeight()));
                Integer pos3 = Integer.parseInt(mapFile.readLine());
                pelotas[2] = new Pelota("imagenes/SpritePelota.jpeg");
                pelotas[2].setX(pos3);
                pelotas[2].setY(339);
                pelotas[2].setColision(new Rectangle((int) pelotas[2].getX(), (int) pelotas[2].getY(), pelotas[2].getWidth(), pelotas[2].getHeight()));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//      creo variable pelotaActual
        pelotaActual = pelotas[0];
        pelotaSiguiente = pelotas[1];
        pelotaAuxiliar = pelotas[2];

        charlie = new Personaje("imagenes/Caldero.png");

//      seteo la posicion Y de charlie dependiendo el Rectangle de la pelota y la posicion X se la doy a la pelota
//      y despues a charlie, esta sera solo la primera vez, luego la pelota sigue a charlie
        charlie.setX(pelotaActual.getX());
        charlie.setY(pelotaActual.getY() - pelotaActual.getHeight());
        charlie.setPOSICION_Y_PISO((int) pelotaActual.getY() - pelotaActual.getHeight());
        charlie.setColision(new Rectangle((int) charlie.getX(),(int) charlie.getY(), charlie.getWidth(), charlie.getHeight()));
        charlie.quieto();

        charlie.setMontura(pelotaActual);

        cam = new Camara(0, 0);
        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/FondoCircusCharlieFinal.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());

        colsionPelotas = false;
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
        pelotaSiguiente.display(g);
        pelotaAuxiliar.display(g);

        g.translate(-cam.getX(), -cam.getY());
    }


    public void update() {
//      Si chocan las dos pelotas
        if(pelotaActual.getColision().intersects(pelotaSiguiente.getColision()) || colsionPelotas) {
            colsionPelotas = true;
//
            pelotaActual.setDesplazamiento(4);
            pelotaSiguiente.setDesplazamiento(4);
            pelotaActual.moverse(1);
            pelotaSiguiente.moverse(0);

            if((pelotaActual.getX() + pelotaActual.getWidth()) < cam.getX()) {
                this.reiniciarse();
            }
        }
//      Si el charlie salta
        else if(charlie.getY() < charlie.getPOSICION_Y_PISO()) {
            // la montura es nula
            charlie.setMontura(null);
            charlie.setPOSICION_Y_PISO((int)pelotaActual.getY());

//          las tres pelotas se deben mover a la izquierda
            pelotaActual.moverse(1);
            pelotaSiguiente.moverse(1);
            pelotaAuxiliar.moverse(1);
        }
//      si la montura es nula y colisiona con pelota actual -> seteo pelotaActual como montura
        else if((pelotaActual.getColision().intersects(charlie.getColision())) && (charlie.getMontura() == null)) {
            charlie.setMontura(pelotaActual);
            charlie.setPOSICION_Y_PISO((int) pelotaActual.getY() - pelotaActual.getHeight());
        }
//      si la montura es nula y colisiona con pelota siguiente -> seteo pelotaSiguiente como montura
        else if((pelotaSiguiente.getColision().intersects(charlie.getColision())) && (charlie.getMontura() == null)) {
            charlie.setMontura(pelotaSiguiente);
            charlie.setPOSICION_Y_PISO((int) pelotaActual.getY() - pelotaActual.getHeight());

            aux = pelotaActual;
            pelotaActual = pelotaSiguiente;
            pelotaSiguiente = pelotaAuxiliar;
            pelotaAuxiliar = aux;
        }
//      charlie perfora posicion y + height de la pelota
        else if((charlie.getY() < (pelotaActual.getY() + pelotaActual.getHeight())) && (charlie.getMontura() == null)) {
            this.reiniciarse();
        }
//      si ninguna de las anteriores ocurre es porque la montura es pelota actual, la cual debe seguir al charlie, las otras deberan moverse a la izquierda
        else {
            // pelotaActual sigue al charlie si es que es la montura del charlie
            if(charlie.getMontura() == pelotaActual) {
                pelotaActual.setX(charlie.getX());
            }

//          las tres pelotas se deben mover a la izquierda
            pelotaActual.moverse(1);
            pelotaSiguiente.moverse(1);
            pelotaAuxiliar.moverse(1);
        }

        pelotaActual.setColision(new Rectangle((int) pelotaActual.getX(), (int) pelotaActual.getY(), pelotaActual.getWidth(), pelotaActual.getHeight()));
        pelotaSiguiente.setColision(new Rectangle((int) pelotaSiguiente.getX(), (int) pelotaSiguiente.getY(), pelotaSiguiente.getWidth(), pelotaSiguiente.getHeight()));
        pelotaAuxiliar.setColision(new Rectangle((int) pelotaAuxiliar.getX(), (int) pelotaAuxiliar.getY(), pelotaAuxiliar.getWidth(), pelotaAuxiliar.getHeight()));
        charlie.setColision(new Rectangle(new Rectangle((int) charlie.getX(),(int) charlie.getY(), charlie.getWidth(), charlie.getHeight())));

        if((pelotas[0].getX() + pelotas[0].getWidth()) < cam.getX()) {
            pelotas[0].setX(pelotas[2].getX() + 600);
        } else if((pelotas[1].getX() + pelotas[1].getWidth()) < cam.getX()) {
            pelotas[1].setX(pelotas[0].getX() + 800);
        }
        else if((pelotas[2].getX() + pelotas[2].getWidth()) < cam.getX()) {
            pelotas[2].setX(pelotas[1].getX() + 900);
        }
    };

    public void reiniciarse() {
        this.iniciarse();
    }
}
