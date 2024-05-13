package poo.games.pong;

import poo.games.Fondo;

import com.entropyinteractive.*;
import java.util.LinkedList;
import java.util.Random;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

// TODO: CREAR OBJETO MOVIBLE
// TODO: DARLE 2 SEGUNDITOS ANTES DE INICIAR LA PARTIDA
// TODO: AUMENTAR DIFICULTAD

public class Pong extends JGame{
    Paleta jDerecha, jIzquierda;
    Pelota pelota;
    Fondo fondo;
    BufferedImage lineaRed;
    final static int J_VELOCIDAD = 250;
    final static int PELOTA_VELOCIDAD = 300;
    int direccionPelota;
    Integer puntosJIzquierda = 0, puntosJDerecha = 0;

    public Pong(){
        super("Pong", 800, 600);
    };

    public void gameStartup(){
        try{
            BufferedImage imagenJ1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imagenes/SpriteJ1.jpeg"));
            BufferedImage imagenJ2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imagenes/SpriteJ2.jpeg"));

            BufferedImage imagenPelota = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imagenes/SpritePelota.jpeg"));
            BufferedImage imagenFondo = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imagenes/ImagenNegro.jpeg"));

            lineaRed = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imagenes/SpriteRedMedio.jpeg"));
        
            fondo = new Fondo(imagenFondo, this.getHeight(), this.getWidth(), 0, 30);
            fondo.getBordeInf().setLocation(0, this.getHeight()-15);
            jDerecha = new Paleta(imagenJ1 , (int)(imagenJ2.getHeight()-imagenJ2.getHeight()*0.80), (int)(imagenJ2.getWidth()-imagenJ2.getWidth()*0.75), this.getWidth()-50, (this.getHeight()/2)- 50);
            jIzquierda = new Paleta(imagenJ1,(int)(imagenJ1.getHeight()-imagenJ1.getHeight()*0.80), (int)(imagenJ1.getWidth()-imagenJ1.getWidth()*0.75), this.getWidth()-(this.getWidth()-25), (this.getHeight()/2)-50);
            pelota = new Pelota(imagenPelota, (int)(imagenPelota.getHeight()-imagenPelota.getHeight()*0.80), (int)(imagenPelota.getWidth()-imagenPelota.getWidth()*0.80), this.getWidth()/2, this.getHeight()/2);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        direccionPelota = new Random().nextInt(4);


    };

    public void gameUpdate(double delta){
        Keyboard keyboard = this.getKeyboard();
         
//      DIRECCION DE LA PALOTA

        if(direccionPelota == 0){// Y = SUBIENDO X = IZQUIERDA
            pelota.setX( pelota.getX() - PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() - PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 1){// Y = SUBIENDO X = DERECHA
            pelota.setX( pelota.getX() + PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() - PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 2){// Y = BAJANDO X = IZQUIERDA
            pelota.setX( pelota.getX() - PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() + PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 3){// Y = BAJANDO X = DERECHA
            pelota.setX( pelota.getX() + PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() + PELOTA_VELOCIDAD * delta);
        }

//      COLISIONES CON LAS PALETAS

        if(jIzquierda.getColision().intersects(pelota.getColision())){
            if(direccionPelota == 0){
                direccionPelota = 1;
            }else{
                direccionPelota = 3;
            }
        }

        if(jDerecha.getColision().intersects(pelota.getColision())){
            if(direccionPelota == 1){
                direccionPelota = 0;
            }else{
                direccionPelota = 2;
            }
        }

//      COLISIONES CON LOS BORDES

        if (fondo.getBordeSup().intersects(pelota.getColision())) {
            if (direccionPelota == 0) {
                direccionPelota = 2;
            } else {
                direccionPelota = 3;
            }
        }

        if (fondo.getBordeInf().intersects(pelota.getColision())) {
            if (direccionPelota == 2) {
                direccionPelota = 0;
            } else {
                direccionPelota = 1;
            }
        }

        if (fondo.getBordeIzq().intersects(pelota.getColision())) {
            puntosJDerecha = reiniciar_juego(puntosJDerecha);
        }

        if (fondo.getBordeDer().intersects(pelota.getColision())) {
            puntosJIzquierda = reiniciar_juego(puntosJIzquierda);
        }


//      MOVIMIENTOS DE LAS PALETAS

        if (keyboard.isKeyPressed(KeyEvent.VK_W) && jIzquierda.getColision().intersects(fondo.getBordeSup()) == false){
            jIzquierda.setY( jIzquierda.getY() - J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_S) && jIzquierda.getColision().intersects(fondo.getBordeInf()) == false){
            jIzquierda.setY( jIzquierda.getY() + J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_UP) && jDerecha.getColision().intersects(fondo.getBordeSup()) == false){
            jDerecha.setY( jDerecha.getY() - J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN) && jDerecha.getColision().intersects(fondo.getBordeInf()) == false){
            jDerecha.setY( jDerecha.getY() + J_VELOCIDAD * delta);
        }
         
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

        fondo.update(delta);
        jIzquierda.update(delta);
        jDerecha.update(delta);
        pelota.update(delta);
    };
 
    public void gameDraw(Graphics2D g){
        g.drawImage(fondo.getFondo(),0,0,null);
        g.drawImage(lineaRed, (this.getWidth()/2)-(lineaRed.getWidth()/2), 0, null);

        g.setColor(Color.white);
        g.setFont(new Font("Retro Gaming", Font.PLAIN, 60));
        
        g.drawString(puntosJIzquierda.toString(),(this.getWidth()/3)-30,100);
        g.drawString(puntosJDerecha.toString(),this.getWidth()-(this.getWidth()/3)-25,100);


        jIzquierda.draw(g);
        jDerecha.draw(g);
        pelota.draw(g);
    };
 
    public void gameShutdown(){
        Log.info(getClass().getSimpleName(), "Shutting down game");
    };

    private Integer reiniciar_juego(Integer puntuador){
        pelota.setX(this.getWidth()/2);
        pelota.setY(this.getHeight()/2);
        direccionPelota = new Random().nextInt(4);
        return puntuador+1;
        
    };

}
