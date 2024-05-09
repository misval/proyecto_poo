package poo.games.pong;

import com.entropyinteractive.*;
import java.util.LinkedList;
import java.util.Random;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Pong extends JGame{
    Paleta jDerecha, jIzquierda;
    Pelota pelota;
    BufferedImage img_fondo;
    final static int J_VELOCIDAD = 300;
    final static int PELOTA_VELOCIDAD = 200;
    int direccionPelota;

    public Pong(){
        super("Pong", 800, 600);
    };

    public void gameStartup(){
        try{
            BufferedImage imagenJ1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("libs/imagenes/SpriteJ1.jpeg"));
            BufferedImage imagenJ2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("libs/imagenes/SpriteJ2.jpeg"));

            BufferedImage imagenPelota = ImageIO.read(getClass().getClassLoader().getResourceAsStream("libs/imagenes/SpritePelota.jpeg"));

            jDerecha = new Paleta(imagenJ1 , (int)(imagenJ2.getHeight()-imagenJ2.getHeight()*0.85), (int)(imagenJ2.getWidth()-imagenJ2.getWidth()*0.8), 750, 50);
            jIzquierda = new Paleta(imagenJ1,(int)(imagenJ1.getHeight()-imagenJ1.getHeight()*0.85), (int)(imagenJ1.getWidth()-imagenJ1.getWidth()*0.8), 50, 50);
            pelota = new Pelota(imagenPelota, (int)(imagenPelota.getHeight()-imagenPelota.getHeight()*0.85), (int)(imagenPelota.getWidth()-imagenPelota.getWidth()*0.85), 330, 50);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        direccionPelota = new Random().nextInt(3);


    };

    public void gameUpdate(double delta){
        Keyboard keyboard = this.getKeyboard();
         
        if(direccionPelota == 0){
            pelota.setX( pelota.getX() - PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() - PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 1){
            pelota.setX( pelota.getX() + PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() - PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 2){
            pelota.setX( pelota.getX() - PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() + PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 3){
            pelota.setX( pelota.getX() + PELOTA_VELOCIDAD * delta);
            pelota.setY( pelota.getY() + PELOTA_VELOCIDAD * delta);
        }


        if(jIzquierda.colision.intersects(pelota.colision)){
            if(direccionPelota == 0){
                direccionPelota = 1;
            }else{
                direccionPelota = 3;
            }
        }

        if(jDerecha.colision.intersects(pelota.colision)){
            System.out.println("*");
            if(direccionPelota == 1){
                direccionPelota = 0;
            }else{
                direccionPelota = 2;
            }
        }

        if(jDerecha.colision.contains(pelota.colision)){
            System.out.println("#");
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_UP)){
            jIzquierda.setY( jIzquierda.getY() - J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN)){
            jIzquierda.setY( jIzquierda.getY() + J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_W)){
            jDerecha.setY( jDerecha.getY() - J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_S)){
            jDerecha.setY( jDerecha.getY() + J_VELOCIDAD * delta);
        }
         
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }


        jIzquierda.update(delta);
        jDerecha.update(delta);
        pelota.update(delta);
    };
 
    public void gameDraw(Graphics2D var1){
        jIzquierda.draw(var1);
        jDerecha.draw(var1);
        pelota.draw(var1);
    };
 
    public void gameShutdown(){
        Log.info(getClass().getSimpleName(), "Shutting down game");
    };

    public void reiniciar_juego(){};

    public static void main(String[] args) {
        System.out.println("Pong");
    }
}
