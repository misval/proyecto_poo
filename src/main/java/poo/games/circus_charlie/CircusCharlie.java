package poo.games.circus_charlie;

import com.entropyinteractive.JGame;
import com.entropyinteractive.Keyboard;

import poo.games.Personaje;
import poo.games.Camara;

import java.awt.*;
import java.awt.event.*;

import java.util.*;


public class CircusCharlie extends JGame{
    private Nivel nivel;

    public CircusCharlie(){
        super("CircusCharlie", 800, 480);

//        nivel = new Nivel1();
        nivel = new Nivel3();
        nivel.getCharlie().quieto();
    };


    public void gameStartup(){
//        if() {
//
//        }
    };

    public void gameUpdate(double var1){
        Keyboard keyboard = this.getKeyboard();

        Personaje heroe = nivel.getCharlie();
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
            if ((event.getID() == KeyEvent.KEY_RELEASED)){
                heroe.quieto();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_UP)) {
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

        heroe.update(var1);

//        heroe.applyForce(gravedad);

        cam.seguirPersonaje(heroe);
    };
 
    public void gameDraw(Graphics2D var1){
        nivel.draw(var1);
    };
 
    public void gameShutdown(){};

    public static void main(String[] args) {
        System.out.println("Circus Charlie");
    }
}
