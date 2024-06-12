package poo.games.circus_charlie;

import poo.games.Camara;
import poo.games.Fondo;
import poo.games.Mundo;
import poo.games.Personaje;
import java.lang.Math;

import java.awt.*;

public class Nivel1 extends Nivel {
    private Leon leon;
    private Aro[] aros = new Aro[3];
    private Jarron[] jarrones = new Jarron[3];
    private BolsaBonus bolsaBonus;
    int tiempoColision = 0;
    private boolean bolsaAgarrado;

    Nivel1(Integer vidasJugador, Integer puntosTotales) {
        super();
        this.iniciarse();
        setVidas(vidasJugador);
        setPuntosTotales(puntosTotales);
        reiniciarBonus();
    }

    public void iniciarse() {
        Mundo m = Mundo.getInstance();
        bolsaAgarrado = false;

        aros[0] = new Aro("imagenes/MitadAtrasAroGrande.png", "imagenes/MitadAdelanteAroGrande.png");
        aros[1] = new Aro("imagenes/MitadAtrasAroGrande.png", "imagenes/MitadAdelanteAroGrande.png");
        aros[2] = new Aro("imagenes/MitadAtrasAroGrande.png", "imagenes/MitadAdelanteAroGrande.png");
        
        aros[0].setPosition(560, 162);
        aros[1].setPosition(1060, 162);
        aros[2].setPosition(1560, 162);

        aros[0].setColision((int)aros[0].getAro().getX()-5,(int)aros[0].getAro().getY() + aros[0].getAro().getHeight()-10, 10, 10);
        aros[1].setColision((int)aros[1].getAro().getX()-5,(int)aros[1].getAro().getY() + aros[1].getAro().getHeight()-10, 10, 10);
        aros[2].setColision((int)aros[2].getAro().getX()-5,(int)aros[2].getAro().getY() + aros[2].getAro().getHeight()-10, 10, 10);

        bolsaBonus = new BolsaBonus("imagenes/BolsaBonus.png");
        bolsaBonus.setX(aros[2].getX()-20);
        bolsaBonus.setY(200);
        bolsaBonus.setColision(new Rectangle((int)bolsaBonus.getX(), (int)bolsaBonus.getY(), bolsaBonus.getWidth(), bolsaBonus.getHeight()));
        

        jarrones[0] = new Jarron("imagenes/JarronEstatico.png");
        jarrones[1] = new Jarron("imagenes/JarronEstatico.png");
        jarrones[2] = new Jarron("imagenes/JarronEstatico.png");

        jarrones[0].setY(355);
        jarrones[0].setX(560);

        jarrones[1].setY(355);
        jarrones[1].setX(1060);

        jarrones[2].setY(355);
        jarrones[2].setX(1560);

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
        leon.setColision(new Rectangle((int)leon.getX()+18,(int)leon.getY(),57, 22));

        meta = new Meta("imagenes/Meta1.png");
        meta.setY(350);
        meta.setX(6700);
        meta.setColision(new Rectangle((int) meta.getX(),(int) meta.getY(), meta.getWidth(), meta.getHeight()+15));

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

        aros[0].display1(g);
        aros[1].display1(g);
        aros[2].display1(g);

        bolsaBonus.display(g);

        jarrones[0].display(g);
        jarrones[1].display(g);
        jarrones[2].display(g);

        charlie.display(g);
        leon.display(g);

        meta.display(g);

        //Mostrar puntajes y vidas
        g.setColor(Color.white);
        g.setFont(new Font("Retro Gaming", Font.PLAIN, 18));
        g.drawString("Vidas  "+this.getVidas().toString(),(int)-cam.getX()+40, (int)fondo.getHeight()-420);
        g.drawString("Puntos  "+this.getPuntosTotales().toString(),(int)-cam.getX() +140, (int)fondo.getHeight()-420);
        g.drawString("Bonus  "+ this.getBonus(),(int)(-cam.getX())+670, (int)fondo.getHeight()-420);
        
        aros[0].display2(g);
        aros[1].display2(g);
        aros[2].display2(g);

        g.translate(-cam.getX(), -cam.getY());
    };

    public void update() {
        charlie.getColision().setLocation((int)charlie.getX()+10, (int)charlie.getY()+13);
        leon.getColision().setLocation((int)leon.getX()+18, (int)leon.getY());

        leon.setX(charlie.getX()-leon.getWidth()/3);
        leon.setY(charlie.getY()+charlie.getHeight());

        aros[0].moverse(1);
        aros[1].moverse(1);
        aros[2].moverse(1);

        bolsaBonus.moverse(1);
        bolsaBonus.getColision().setLocation((int)bolsaBonus.getX(), (int)bolsaBonus.getY());

        //Mueve los jarrones cuando salen de pantalla
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

        
        //Mueve los aros cuando salen de pantalla
        if((aros[0].getX() + aros[0].getWidth()) < (-cam.getX())) {
            aros[0].setPosition((int)aros[2].getX() + aros[2].getWidth() + 500, (int)aros[0].getY());
            aros[0].getColision().setLocation((int)aros[0].getAro().getX()-5,(int)aros[0].getAro().getY() + aros[0].getAro().getHeight()-10);
            if(bolsaAgarrado){
                bolsaBonus.setX(aros[0].getX()-20);
                bolsaAgarrado=false;
            }
        }
        else if((aros[1].getX() + aros[1].getWidth()) < (-cam.getX())) {
            aros[1].setPosition((int)aros[0].getX() + aros[0].getWidth() + 500, (int)aros[1].getY());
            aros[1].getColision().setLocation((int)aros[1].getAro().getX()-5,(int)aros[1].getAro().getY() + aros[1].getAro().getHeight()-10);
            if(bolsaAgarrado){
                bolsaBonus.setX(aros[1].getX()-20);
                bolsaAgarrado=false;
            }
        }
        else if((aros[2].getX() + aros[2].getWidth()) < (-cam.getX())) {
            aros[2].setPosition((int)aros[1].getX() + aros[1].getWidth() + 500, (int)aros[2].getY());
            aros[2].getColision().setLocation((int)aros[2].getAro().getX()-5,(int)aros[2].getAro().getY() + aros[2].getAro().getHeight()-10);
            if(bolsaAgarrado){
                bolsaBonus.setX(aros[2].getX()-20);
                bolsaAgarrado=false;
            }
        }

        //mueve bolsaBonus cuando sale de pantalla
        if((bolsaBonus.getX() + bolsaBonus.getWidth()) < (-cam.getX())){
            bolsaBonus.setX(bolsaBonus.getX() + 1500);
        } 


        //Colision con aros
        if((aros[0].getColision().intersects(charlie.getColision())) || (aros[0].getColision().intersects(leon.getColision()))) {
            aros[0].moverse(-1);
            charlie.animacionMuerte();
            leon.setSprite("imagenes/LeonMuriendo.png");
            tiempoColision++;
            if(tiempoColision == 1){}
            else{
                while(tiempoColision < 10){
                    tiempoColision++;
                }
                reiniciarse();
            }   
        }
        else if((aros[1].getColision().intersects(charlie.getColision())) || (aros[1].getColision().intersects(leon.getColision()))) {
            aros[1].moverse(-1);
            charlie.animacionMuerte();
            leon.setSprite("imagenes/LeonMuriendo.png");
            tiempoColision++;
            if(tiempoColision == 1){}
            else{
                while(tiempoColision < 10){
                    tiempoColision++;
                }
                reiniciarse();
            } 
        }
        else if((aros[2].getColision().intersects(charlie.getColision())) || (aros[2].getColision().intersects(leon.getColision()))) {
            aros[2].moverse(-1);
            charlie.animacionMuerte();
            leon.setSprite("imagenes/LeonMuriendo.png");
            tiempoColision++;
            if(tiempoColision == 1){}
            else{
                while(tiempoColision < 10){
                    tiempoColision++;
                }
                reiniciarse();
            } 
        }

        //colision con los jarrones
        if((jarrones[0].getColision().intersects(charlie.getColision())) || (jarrones[0].getColision().intersects(leon.getColision()))) {
            charlie.animacionMuerte();
            leon.setSprite("imagenes/LeonMuriendo.png");
            tiempoColision++;
            if(tiempoColision == 1){}
            else{
                while(tiempoColision < 10){
                    tiempoColision++;
                }
                reiniciarse();
            }   
        }
        else if((jarrones[1].getColision().intersects(charlie.getColision())) || (jarrones[1].getColision().intersects(leon.getColision()))) {
            charlie.animacionMuerte();
            leon.setSprite("imagenes/LeonMuriendo.png");
            tiempoColision++;
            if(tiempoColision == 1){}
            else{
                while(tiempoColision < 10){
                    tiempoColision++;
                }
                reiniciarse();
            } 
        }
        else if((jarrones[2].getColision().intersects(charlie.getColision())) || (jarrones[2].getColision().intersects(leon.getColision()))) {
            charlie.animacionMuerte();
            leon.setSprite("imagenes/LeonMuriendo.png");
            tiempoColision++;
            if(tiempoColision == 1){}
            else{
                while(tiempoColision < 10){
                    tiempoColision++;
                }
                reiniciarse();
            } 
        }


        //colision con bolsaBonus
        if(bolsaBonus.getColision().intersects(charlie.getColision())){
            setPuntosTotales(500);
            bolsaBonus.setX(cam.GetRegionVisibleX()*2.5+-cam.getX());
            bolsaAgarrado = true;
        }

        animacionBonus();

    }

    public Personaje getCharlie() {
        return charlie;
    }

    public Camara getCam() {
        return cam;
    }

    public void reiniciarse() {
        tiempoColision = 0;
        this.setVidas(this.getVidas()-1);
        this.reiniciarBonus();
        this.iniciarse();
    }
}