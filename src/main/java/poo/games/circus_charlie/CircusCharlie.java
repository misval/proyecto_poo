package poo.games.circus_charlie;

import com.entropyinteractive.JGame;

import com.entropyinteractive.Keyboard;

import poo.games.FXPlayer;
import poo.games.Jugador;
import poo.games.Personaje;
import poo.games.Camara;


import java.awt.*;
import java.awt.event.*;

import java.io.*;
import java.util.*;

public class CircusCharlie extends JGame {
    private Personaje heroe;
    private Nivel nivel;
    private Integer nivelActual = 1;
    private Jugador jugadorActual;
    private Integer vidasJugador = 3;
    private Integer puntosTotales = 0;
    private Boolean entroGameShutdown = false;
    private Integer flag = 0;
    boolean musica;
int cont = 0;
    public CircusCharlie(Jugador jugadorActual) {
        super("CircusCharlie", 800, 480);
        setJugadorActual(jugadorActual);
        this.gameStartup();
    }

    ;

    public void gameStartup() {
        if (nivelActual == 0) {
            nivel = new Nivel1(vidasJugador, puntosTotales);
        } else if (nivelActual == 1) {
            nivel = new Nivel2(vidasJugador, puntosTotales);
        } else if (nivelActual == 2) {
            nivel = new Nivel3(vidasJugador, puntosTotales);
        }
        try {
            // Crear un lector para el archivo de entrada
            BufferedReader configuracionFile = new BufferedReader(new FileReader("src/main/resources/files/configuracion.txt"));
            String linea = configuracionFile.readLine();

            if(linea.equals("false")) {
                setMusica(false);
            }
            if(!musica) {
                if (nivelActual == 0) {
                    FXPlayer.NivelSoundtrack1.stop();
                } else if (nivelActual == 1) {
                    FXPlayer.NivelSoundtrack2.stop();
                }else if (nivelActual == 2) {
                    FXPlayer.NivelSoundTrack3.stop();
                    nivelActual = 0;
                }
                FXPlayer.NivelSalto2.SwitchingMUTE();
            }
            configuracionFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //DesComentar para que reproduzca

    }

    public Boolean getMusica() {
        return musica;
    }

    public void setMusica(Boolean musica) {
        this.musica = musica;
    }

    public void gameUpdate(double var1) {
        Keyboard keyboard = this.getKeyboard();

        heroe = nivel.getCharlie();
        Camara cam = nivel.getCam();

        if (nivel.getVidas() == 0) {
            this.gameShutdown();
        } else if (heroe.getColision().intersects(nivel.getMeta().getColision()) || flag > 0) {
            heroe.animacionVictoria();
            heroe.setPOSICION_Y_PISO((int) nivel.getMeta().getY() - nivel.getMeta().getHeight() - 10);
            heroe.setX((int) nivel.getMeta().getX() + nivel.getMeta().getWidth() / 2 - heroe.getWidth() / 2);
            flag++;
            if (flag > 50) {
                flag = 0;
                this.ganar();
            }
        } else {
            if (keyboard.isKeyPressed(KeyEvent.VK_LEFT)) {
                heroe.left();
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_RIGHT)) {
                heroe.right();
            }

            LinkedList<KeyEvent> keyEvents = keyboard.getEvents();
            for (KeyEvent event : keyEvents) {
                if ((event.getKeyCode() == KeyEvent.VK_UP)) {
                    if (nivelActual == 0) {
                        FXPlayer.NivelSalto2.play();
                    } else if (nivelActual == 1) {
                        FXPlayer.NivelSalto2.play();
                    }else if (nivelActual == 2) {
                        FXPlayer.NivelSalto2.play();
                        nivelActual = 0;
                    }
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

                if ((event.getID() == KeyEvent.KEY_PRESSED) && keyboard.isKeyPressed(KeyEvent.VK_M)) {
                    if (nivelActual == 0) {
                        FXPlayer.NivelSoundtrack1.SwitchingLoop();
                    } else if (nivelActual == 1) {
                        FXPlayer.NivelSoundtrack2.SwitchingLoop();
                    }else if (nivelActual == 2) {
                        FXPlayer.NivelSoundTrack3.SwitchingLoop();
                        nivelActual = 0;
                    }
                }

                if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                        (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                    stop();
                }
            }

            setPuntosTotales(nivel.getPuntosTotales());
            heroe.update(var1);
            cam.seguirPersonaje(heroe);
            nivel.update();
        }
    }

    ;

    public void gameDraw(Graphics2D var1) {
        nivel.draw(var1);
    }

    ;

    public void gameShutdown() {
        //cuando pierde, se le suman los puntos totales al ranking de jugadores
        jugadorActual.setPuntosCharlie(getPuntosTotales());

        if (!entroGameShutdown) {
            try {
                BufferedWriter escritorJugadores = new BufferedWriter(new FileWriter("src/main/resources/files/jugadores.txt", true));

                escritorJugadores.newLine(); // Añadir una nueva línea antes de escribir el nuevo jugador
                escritorJugadores.write(jugadorActual.getNombre());
                escritorJugadores.newLine();
                escritorJugadores.write(String.valueOf(jugadorActual.getPuntosCharlie()));

                escritorJugadores.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            entroGameShutdown = true;
        }

//      luego se detiene el juego
        stop();
    }

    ;

    public void ganar() {

            if (nivelActual == 0) {
                FXPlayer.NivelSoundtrack1.stop();
            } else if (nivelActual == 1) {
                FXPlayer.NivelSoundtrack2.stop();
            }
            if (nivelActual == 2) {
                FXPlayer.NivelSoundTrack3.stop();
                nivelActual = 0;
            }
            nivelActual++;

//      si gana el nivel, se extrae el bonus del nivel y se lo suma a los puntos totales
            setPuntosTotales(nivel.getPuntosTotales() + nivel.getBonus());
            setVidasJugador(nivel.getVidas());
            gameStartup();
    };

    public Integer getPuntosTotales() {
        return puntosTotales;
    }

    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales = puntosTotales;
    }

    public void setVidasJugador(Integer vidasJugador) {
        this.vidasJugador = vidasJugador;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public static void main(String[] args) {
        System.out.println("Circus Charlie");
    }
}
