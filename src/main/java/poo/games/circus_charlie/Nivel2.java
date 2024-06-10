package poo.games.circus_charlie;


import poo.games.*;

import java.awt.*;
import poo.games.Camara;
import poo.games.Fondo;
import poo.games.Mundo;
import poo.games.Personaje;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


public class Nivel2 extends Nivel {
    private Mono[] monos = new Mono[10];
    private Boolean colsionMono = false;
    private Mono monoActual,monoSiguiente,monoAuxiliar;
    private MonoAzul monoAzul;
    int soga = 270,flag=0;
    boolean bandera = false;
    private float AnchoTotal=1;
    public void reiniciarse() {
        this.iniciarse();
        flag = 0;
    }

    Nivel2() {
        super();
        this.iniciarse();

    }

    public void iniciarse() {
        Mundo m = Mundo.getInstance();

        cam = new Camara(0, 0);
        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/FondoCharlieSoga.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());

      //  try(BufferedReader mapFile = new BufferedReader(new FileReader("src/main/resources/files/mapFile.txt"))) {
        //    if(mapFile.ready()) {
               // Integer pos1 = Integer.parseInt(mapFile.readLine());
                monos[0] = new Mono("imagenes/AzulMono2.gif");
                monos[0].setX(cam.GetRegionVisibleX()+cam.getX());
                monos[0].setY(270);
                monos[0].setColision(new Rectangle((int) monos[0].getX(), (int) monos[0].getY(), monos[0].getWidth(), monos[0].getHeight()));
               // Integer pos2 = Integer.parseInt(mapFile.readLine());
                monos[1] = new Mono("imagenes/AzulMono2.gif");
                monos[1].setX(cam.GetRegionVisibleX()/0.5+cam.getX());
                monos[1].setY(270);
                monos[1].setColision(new Rectangle((int)monos[1].getX(), (int) monos[1].getY(), monos[1].getWidth(), monos[1].getHeight()));
                //Integer pos3 = Integer.parseInt(mapFile.readLine());
                monos[2] = new Mono("imagenes/AzulMono2.gif");
                monos[2].setX(cam.GetRegionVisibleX()/0.75+cam.getX());
                monos[2].setY(270);
                monos[2].setColision(new Rectangle((int) monos[2].getX(), (int) monos[2].getY(), monos[2].getWidth(), monos[2].getHeight()));

                monoAzul = new MonoAzul("imagenes/AzulMono3.gif");
                monoAzul.setX(cam.GetRegionVisibleX()*2.5+ -cam.getX());
                monoAzul.setY(270);
                monoAzul.setColision(new Rectangle((int)monoAzul.getX(), (int) monoAzul.getY(), monoAzul.getWidth(), monoAzul.getHeight()));

                meta = new Meta("imagenes/MetaNight.png");
                meta.setY(265);
                meta.setX(6700);
                meta.setColision(new Rectangle((int) meta.getX(),(int) meta.getY(), meta.getWidth(), meta.getHeight()));
           // }
    //    } catch (IOException e) {
      //      throw new RuntimeException(e);
       // }
//      creo variable pelotaActual
        monoActual = monos[0];
        monoSiguiente = monos[1];
        monoAuxiliar = monos[2];


        charlie = new Personaje("imagenes/CharlieMuriendo.png");

//      seteo la posicion Y de charlie dependiendo el Rectangle de la pelota y la posicion X se la doy a la pelota
//      y despues a charlie, esta sera solo la primera vez, luego la pelota sigue a charlie
       // charlie.setX(cam.GetRegionVisibleX() / 2);
        charlie.setX(6600);
        charlie.setY(monoActual.getY()-20);
        charlie.setPOSICION_Y_PISO((int) monoActual.getY()-20);
        charlie.setColision(new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight()));
        charlie.quieto();

        colsionMono = false;
    }


    public boolean victoria(){
        return (flag > 0)?true:false;
    }

    @Override
    public void draw(Graphics2D g) {
        Mundo m = Mundo.getInstance();
        g.translate(cam.getX(), cam.getY());
        AnchoTotal = m.getWidth();
        fondo.display(g);
        m.display(g);
        charlie.display(g);
        meta.display(g);
        if(flag == 0){
            monoActual.display(g);
            monoSiguiente.display(g);
            monoAuxiliar.display(g);
            monoAzul.display(g);
        }

        g.translate(-cam.getX(), -cam.getY());
    }


    public void update() {
//      Si choca charlie con el mono
        if(monoActual.getColision().intersects(monoSiguiente.getColision()) || colsionMono) {
            colsionMono = true;
//          reinicio el nivel
            if((monoActual.getX() + monoActual.getWidth()) < cam.getX()) {
                this.reiniciarse();
            }
        }

//      Si el charlie salta
        else if(charlie.getY() < charlie.getPOSICION_Y_PISO()) {
            charlie.setPOSICION_Y_PISO((int)monoActual.getY()-20);
        }
//      si la montura es nula y colisiona con pelota actual -> seteo pelotaActual como montura
        else if((monoActual.getColision().intersects(charlie.getColision())) && !this.victoria()) {
            this.reiniciarse();
        }if((monoSiguiente.getColision().intersects(charlie.getColision())) && !this.victoria()) {
            this.reiniciarse();
        }if((monoAuxiliar.getColision().intersects(charlie.getColision())) && !this.victoria()) {
            this.reiniciarse();
        }if((monoAzul.getColision().intersects(charlie.getColision())) && !this.victoria()) {
            this.reiniciarse();
        }
        monoSiguiente.animacion();
        monoActual.animacion();
        monoAuxiliar.animacion();
        monoAzul.animacion();

        if(monoAzul.getColision().intersects(monoSiguiente.getColision()) || monoAzul.getColision().intersects(monoAuxiliar.getColision()) || monoAzul.getColision().intersects(monoActual.getColision())) {
            monoAzul.saltar();
        }
        monoAzul.mover();

/* if(monoAzul.getY() < monoAzul.getPOSICION_Y_PISO()) {

        }*/



//      si ninguna de las anteriores ocurre es porque la montura es pelota actual, la cual debe seguir al charlie, las otras deberan moverse a la izquierda


//      los tres Monos se deben mover a la izquierda
        monoActual.moverse(1);
        monoSiguiente.moverse(1);
        monoAuxiliar.moverse(1);
        monoAzul.moverse(1);

       monoActual.setColision(new Rectangle((int) monoActual.getX(), (int) monoActual.getY(), monoActual.getWidth(), monoActual.getHeight()));
       monoSiguiente.setColision(new Rectangle((int) monoSiguiente.getX(), (int) monoSiguiente.getY(), monoSiguiente.getWidth(), monoSiguiente.getHeight()));
       monoAuxiliar.setColision(new Rectangle((int) monoAuxiliar.getX(), (int) monoAuxiliar.getY(), monoAuxiliar.getWidth(), monoAuxiliar.getHeight()));
       monoAzul.setColision(new Rectangle((int) monoAzul.getX(), (int) monoAzul.getY(), monoAzul.getWidth(), monoAzul.getHeight()));
       charlie.setColision(new Rectangle(new Rectangle((int) charlie.getX(),(int) charlie.getY(), charlie.getWidth(), charlie.getHeight())));

        if((monos[0].getX() + monos[0].getWidth()) < -cam.getX()) {
            monos[0].setX(cam.GetRegionVisibleX()/0.25+ -cam.getX());
        } else if((monos[1].getX() + monos[1].getWidth()) < -cam.getX()){
            monos[1].setX(cam.GetRegionVisibleX()/0.5+ -cam.getX());
        }
        else if((monos[2].getX() + monos[2].getWidth()) < -cam.getX()) {
            monos[2].setX(cam.GetRegionVisibleX()/0.75+ -cam.getX());
        } else if((monoAzul.getX() + monoAzul.getWidth()) < -cam.getX()) {
            monoAzul.setX(cam.GetRegionVisibleX()*2.5+-cam.getX());
        }
        if(charlie.getColision().intersects(meta.getColision()) || flag > 0){
            charlie.animacionVictoria();
            charlie.setPOSICION_Y_PISO((int)meta.getY()-meta.getHeight()-10);
            charlie.setX((int)meta.getX()+meta.getWidth()/2-charlie.getWidth()/2);
            flag++;
            if(flag > 50)
                reiniciarse();
        }

// Verificar y ajustar la posición de los enemigos para mantener la distancia mínima


    };
}
