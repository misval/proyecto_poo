package poo.games.circus_charlie;

import java.awt.*;

import poo.games.*;

public class Nivel2 extends Nivel {
    private Mono[] monos = new Mono[10];
    private Boolean colsionMono = false;
    private Mono monoActual,monoSiguiente,monoAuxiliar;
    private MonoAzul monoAzul;
    int tiempoColision = 0;

    Nivel2(Integer vidasJugador, Integer puntosTotales) {
        super();
        this.iniciarse();
        setVidas(vidasJugador);
        setPuntosTotales(puntosTotales);
        reiniciarBonus();
        FXPlayer.NivelSoundtrack2.loop();
    }

    public void reiniciarse() {
        tiempoColision = 0;
        this.setVidas(this.getVidas()-1);
        this.reiniciarBonus();
        this.iniciarse();
    }

    public void iniciarse() {
        Mundo m = Mundo.getInstance();
        FXPlayer.NivelSoundtrack2.loop();
        cam = new Camara(0, 0);
        cam.setRegionVisible(640, 480);

        fondo = new Fondo("imagenes/FondoCharlieSoga.png");
        m.setLimitesMundo(fondo.getWidth(), fondo.getHeight());

        monos[0] = new Mono("imagenes/MarronCamina1.gif");
        monos[0].setX(cam.GetRegionVisibleX()+cam.getX());
        monos[0].setY(270);
        monos[0].setColision(new Rectangle((int) monos[0].getX(), (int) monos[0].getY(), monos[0].getWidth(), monos[0].getHeight()));

        monos[1] = new Mono("imagenes/MarronCamina1.gif");
        monos[1].setX(cam.GetRegionVisibleX()/0.5+cam.getX());
        monos[1].setY(270);
        monos[1].setColision(new Rectangle((int)monos[1].getX(), (int) monos[1].getY(), monos[1].getWidth(), monos[1].getHeight()));

        monos[2] = new Mono("imagenes/MarronCamina1.gif");
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

        monoActual = monos[0];
        monoSiguiente = monos[1];
        monoAuxiliar = monos[2];

        charlie = new Personaje("imagenes/CharlieMuriendo.png");

         charlie.setX(cam.GetRegionVisibleX() / 2+6000);
//        charlie.setX(6400);
        charlie.setY(monoActual.getY()-20);
        charlie.setPOSICION_Y_PISO((int) monoActual.getY()-20);
        charlie.setColision(new Rectangle((int) charlie.getX(), (int) charlie.getY(), charlie.getWidth(), charlie.getHeight()));
        charlie.quieto();

        colsionMono = false;
    }

    @Override
    public void draw(Graphics2D g) {
        Mundo m = Mundo.getInstance();
        g.translate(cam.getX(), cam.getY());
        fondo.display(g);
        m.display(g);
        charlie.display(g);
        meta.display(g);

        monoActual.display(g);
        monoSiguiente.display(g);
        monoAuxiliar.display(g);
        monoAzul.display(g);

        //Mostrar puntajes y vidas
        g.setColor(Color.white);
        g.setFont(new Font("Retro Gaming", Font.PLAIN, 18));
        g.drawString("Vidas  "+this.getVidas().toString(),(int)-cam.getX()+40, (int)fondo.getHeight()-420);
        g.drawString("Puntos  "+this.getPuntosTotales().toString(),(int)-cam.getX() +140, (int)fondo.getHeight()-420);
        g.drawString("Bonus  "+ this.getBonus(),(int)(-cam.getX())+670, (int)fondo.getHeight()-420);

        g.translate(-cam.getX(), -cam.getY());
    }


    public void update() {
//      Si choca charlie con el mono
        if(charlie.getY() < charlie.getPOSICION_Y_PISO()) {
            charlie.setPOSICION_Y_PISO((int)monoActual.getY()-20);
            if(monos[0].getX() >= charlie.getX()-2 && monos[0].getX() <= charlie.getX()+2) {
                setPuntosTotales(100);
            }

            if(monos[1].getX() >= charlie.getX()-2 && monos[1].getX() <= charlie.getX()+2) {
                setPuntosTotales(100);
            }

            if(monos[2].getX() >= charlie.getX()-2 && monos[2].getX() <= charlie.getX()+2) {
                setPuntosTotales(100);
            }

            if(monoAzul.getX() >= charlie.getX()-2  && monoAzul.getX() <= charlie.getX()+2) {
                setPuntosTotales(500);
            }
        }
        if(monoActual.getColision().intersects(monoSiguiente.getColision()) || colsionMono) {
            colsionMono = true;
//          reinicio el nivel
            if((monoActual.getX() + monoActual.getWidth()) < cam.getX()) {
                this.reiniciarse();
            }
        }
        else if((monoActual.getColision().intersects(charlie.getColision()))) {
            charlie.animacionMuerte();
            monoActual.moverse(0);
            tiempoColision++;
            if(tiempoColision > 3)
                reiniciarse();
        }else if((monoSiguiente.getColision().intersects(charlie.getColision()))) {
            monoSiguiente.moverse(0);
            charlie.animacionMuerte();
            tiempoColision++;
            if(tiempoColision > 3)
                reiniciarse();
        }else if((monoAuxiliar.getColision().intersects(charlie.getColision()))) {
            monoAuxiliar.moverse(0);
            charlie.animacionMuerte();
            tiempoColision++;
            if(tiempoColision > 3)
                reiniciarse();
        }else if((monoAzul.getColision().intersects(charlie.getColision()))) {
            monoAzul.moverse(0);
            charlie.animacionMuerte();
            tiempoColision++;
            if(tiempoColision > 3)
                reiniciarse();
        }else{
            monoActual.moverse(1);
            monoSiguiente.moverse(1);
            monoAuxiliar.moverse(1);
            monoAzul.moverse(1);
        }

        this.animacionBonus();

//        monoSiguiente.animacion();
//        monoActual.animacion();
//        monoAuxiliar.animacion();
//        monoAzul.animacion();

        if(monoAzul.getColision().intersects(monoSiguiente.getColision()) || monoAzul.getColision().intersects(monoAuxiliar.getColision()) || monoAzul.getColision().intersects(monoActual.getColision())) {
            monoAzul.saltar();
        }

        monoAzul.mover();



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


    };
}