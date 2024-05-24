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
    };

    public void gameStartup(){

    };

    public void gameUpdate(double var1){
        Keyboard keyboard = this.getKeyboard();

        Camara cam = nivel.getCam();

        if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)){
            nivel.moverIzqCharlie();
        }
        if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)){
            nivel.moverDerCharlie();
        }

        // check the list of key events for a pressed escape key
        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents) {
            if ((event.getKeyCode() == KeyEvent.VK_UP)) {
                nivel.saltarCharlie();
            }
            if ((event.getID() == KeyEvent.KEY_RELEASED) &&
                    (event.getKeyCode() == KeyEvent.VK_UP)) {
                nivel.saltarFinCharlie();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }
        }

        nivel.updateCharlie(var1);
        cam.seguirPersonaje(nivel.getCharlie());
    };
 
    public void gameDraw(Graphics2D var1){
        nivel.draw(var1);
    };
 
    public void gameShutdown(){

    };


    public static void main(String[] args) {
        System.out.println("Circus Charlie");
    }
}
