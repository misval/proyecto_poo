package poo.games.circus_charlie;

import com.entropyinteractive.JGame;

import com.entropyinteractive.Keyboard;

import poo.games.Jugador;
import poo.games.Personaje;
import poo.games.Camara;


import java.awt.*;
import java.awt.event.*;

import java.util.*;

public class CircusCharlie extends JGame{
    private Personaje heroe;
    private Nivel nivel;
    private Integer nivelActual = 0;
    private Jugador jugadorActual;
    private Integer vidasJugador = 3;

    public CircusCharlie(Jugador jugadorActual){
        super("CircusCharlie", 800, 480);
        this.jugadorActual = jugadorActual;
        this.gameStartup();
    };

    public void gameStartup(){
        if(nivelActual == 0) {
            nivel = new Nivel1();
        } else if(nivelActual == 1) {
            nivel = new Nivel2();
        } else if(nivelActual == 2) {
            nivel = new Nivel3();
        }
    };

    public void gameUpdate(double var1){
        Keyboard keyboard = this.getKeyboard();

        heroe = nivel.getCharlie();
        Camara cam = nivel.getCam();

        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)){
            heroe.left();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)){
            heroe.right();
        }

        // check the list of key events for a pressed escape key
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getKeyCode() == KeyEvent.VK_UP)) {
                heroe.jump();
            }
            if ((event.getID() == KeyEvent.KEY_RELEASED) &&
                    (event.getKeyCode() == KeyEvent.VK_UP)) {
                heroe.jumpEnd();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }
        /*     
        if(heroe.getColision().intersects(nivel.getMeta().getColision())) {
            this.ganar();
        }
        */
        heroe.update(var1);
        cam.seguirPersonaje(heroe);
        nivel.update();
    };
 
    public void gameDraw(Graphics2D var1) {
        nivel.draw(var1);
    };
 
    public void gameShutdown(){};

    public void ganar() {

    };

    public static void main(String[] args) {
        System.out.println("Circus Charlie");
    }
}
