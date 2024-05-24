package poo.games.circus_charlie;

import poo.games.*;

import java.awt.*;
import java.util.*;

public abstract class Nivel {
    private Fondo fondo;
    private Personaje charlie;
    private Camara cam;
    private ArrayList<Obstaculo> obstaculos = new ArrayList<Obstaculo>();

    public Camara getCam() {
        return cam;
    }

    public void moverDerCharlie(){
        charlie.right();
    }
    
    public void moverIzqCharlie(){
        charlie.left();
    }
    
    public void saltarCharlie(){
        charlie.jump();
    }

    public void saltarFinCharlie(){
        charlie.jumpEnd();
    }

    public void updateCharlie(double delta){
        charlie.update(delta);
    }

    public Personaje getCharlie() {
        return charlie;
    }

    public void setObstaculos(Obstaculo obstaculos) {
        this.obstaculos.add(obstaculos);
    }

    public ArrayList<Obstaculo> getObstaculos() {
        return obstaculos;
    }

    Nivel() {
        Mundo m = Mundo.getInstance();
        this.charlie= new Personaje("imagenes/ImagenCharlieEstatica.png");
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
