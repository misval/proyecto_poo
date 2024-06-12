package poo.games.pong;

import poo.games.Fondo;
import poo.games.FXPlayer;

import com.entropyinteractive.*;
import poo.games.Jugador;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.awt.Point;

import javax.imageio.ImageIO;

// TODO: CREAR OBJETO MOVIBLE
// TODO: DARLE 2 SEGUNDITOS ANTES DE INICIAR LA PARTIDA
// TODO: AUMENTAR DIFICULTAD

public class Pong extends JGame{
    Paleta jDerecha, jIzquierda;
    Pelota pelota;
    Fondo fondo;
    BufferedImage lineaRed;
    Boolean musica;

    FXPlayer SonidoPong;

    final static int J_VELOCIDAD = 250;
    final static int PELOTA_VELOCIDAD = 300;
    int direccionPelota;
    Integer puntosJIzquierda = 0, puntosJDerecha = 0;


    public Pong(){
        super("Pong", 800, 600);

    };

    public void gameStartup(){
        try{
            FXPlayer.SOUND_TRACK.loop();

            String imagenJ1 = "imagenes/SpriteJ1.jpeg";
            String imagenJ2 = "imagenes/SpriteJ2.jpeg";

            String imagenPelota = "imagenes/SpritePelota.jpeg";
            String imagenFondo = "imagenes/ImagenNegro.jpeg";

            lineaRed = ImageIO.read(getClass().getClassLoader().getResourceAsStream("imagenes/SpriteRedMedio.jpeg"));

            //Se crea la Paleta derecha
            jDerecha = new Paleta(imagenJ2);
            Dimension tamanio = new Dimension((int)(jDerecha.getSprite().getWidth()-jDerecha.getSprite().getWidth()*0.75), (int)(jDerecha.getSprite().getHeight()-jDerecha.getSprite().getHeight()*0.80));
            Point punto = new Point(this.getWidth()-50, (this.getHeight()/2)- 50);
            jDerecha.setDimesiones(tamanio);
            jDerecha.setPunto(punto);
            jDerecha.setColision(new Rectangle(punto, tamanio));

            //Se crea la Paleta izquierda
            jIzquierda = new Paleta(imagenJ1);
            tamanio = new Dimension((int)(jIzquierda.getSprite().getWidth()-jIzquierda.getSprite().getWidth()*0.75), (int)(jIzquierda.getSprite().getHeight()-jIzquierda.getSprite().getHeight()*0.80));
            punto = new Point(this.getWidth()-(this.getWidth()-25), (this.getHeight()/2)-50);
            jIzquierda.setDimesiones(tamanio);
            jIzquierda.setPunto(punto);
            jIzquierda.setColision(new Rectangle(punto, tamanio));

            //Se crea la Pelota
            pelota = new Pelota(imagenPelota);
            tamanio = new Dimension((int)(pelota.getSprite().getWidth()-pelota.getSprite().getWidth()*0.80), (int)(pelota.getSprite().getHeight()-pelota.getSprite().getHeight()*0.80));
            punto = new Point( this.getWidth()/2, this.getHeight()/2);
            pelota.setDimesiones(tamanio);
            pelota.setPunto(punto);
            pelota.setColision(new Rectangle(punto, tamanio));

            //Se crea el Fondo
            tamanio = new Dimension(this.getWidth(), this.getHeight());
            punto = new Point(0, 30);
            fondo = new Fondo(imagenFondo, tamanio, punto);
            fondo.getBordeInf().setLocation(0, this.getHeight()-15);


            try {
                // Crear un lector para el archivo de entrada
                BufferedReader configuracionFile = new BufferedReader(new FileReader("src/main/resources/files/configuracion.txt"));
                String linea = configuracionFile.readLine();

                if(linea.equals("false")) {
                    setMusica(false);
                }

                configuracionFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //DesComentar para que reproduzca
            if(!musica) {
                FXPlayer.SOUND_TRACK.SwitchingLoop();
                FXPlayer.GANE.SwitchingMUTE();
                FXPlayer.REVOTA.SwitchingMUTE();
                FXPlayer.START.SwitchingMUTE();
            }
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
            pelota.moverseIzquierda(PELOTA_VELOCIDAD * delta);
            pelota.moverseArriba(PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 1){// Y = SUBIENDO X = DERECHA
            pelota.moverseDerecha(PELOTA_VELOCIDAD * delta);
            pelota.moverseArriba(PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 2){// Y = BAJANDO X = IZQUIERDA
            pelota.moverseIzquierda(PELOTA_VELOCIDAD * delta);
            pelota.moverseAbajo(PELOTA_VELOCIDAD * delta);

        }else if(direccionPelota == 3){// Y = BAJANDO X = DERECHA
            pelota.moverseDerecha(PELOTA_VELOCIDAD * delta);
            pelota.moverseAbajo(PELOTA_VELOCIDAD * delta);
        }

//      COLISIONES CON LAS PALETAS

        if(jIzquierda.getColision().intersects(pelota.getColision())){
            FXPlayer.REVOTA.play();
            if(direccionPelota == 0){
                direccionPelota = 1;
            }else{
                direccionPelota = 3;
            }
        }

        if(jDerecha.getColision().intersects(pelota.getColision())){
            FXPlayer.REVOTA.play();
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
            FXPlayer.START.play();
        }

        if (fondo.getBordeDer().intersects(pelota.getColision())) {
            puntosJIzquierda = reiniciar_juego(puntosJIzquierda);
            FXPlayer.START.play();
        }


//      MOVIMIENTOS DE LAS PALETAS

        if (keyboard.isKeyPressed(KeyEvent.VK_W) && jIzquierda.getColision().intersects(fondo.getBordeSup()) == false){
            jIzquierda.moverseArriba(J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_S) && jIzquierda.getColision().intersects(fondo.getBordeInf()) == false){
            jIzquierda.moverseAbajo(J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_UP) && jDerecha.getColision().intersects(fondo.getBordeSup()) == false){
            jDerecha.moverseArriba(J_VELOCIDAD * delta);
        }

        if (keyboard.isKeyPressed(KeyEvent.VK_DOWN) && jDerecha.getColision().intersects(fondo.getBordeInf()) == false){
            jDerecha.moverseAbajo(J_VELOCIDAD * delta);
        }



        LinkedList < KeyEvent > keyEvents = keyboard.getEvents();
        for (KeyEvent event: keyEvents){
            if ((event.getID() == KeyEvent.KEY_PRESSED) && keyboard.isKeyPressed(KeyEvent.VK_M))
                FXPlayer.SOUND_TRACK.SwitchingLoop();
            if ((event.getID() == KeyEvent.KEY_PRESSED) && keyboard.isKeyPressed(KeyEvent.VK_F)){
                FXPlayer.GANE.SwitchingMUTE();
                FXPlayer.REVOTA.SwitchingMUTE();
                FXPlayer.START.SwitchingMUTE();
            }
            if ((event.getID() == KeyEvent.KEY_PRESSED) &&
                    (event.getKeyCode() == KeyEvent.VK_ESCAPE)) {
                stop();
            }


        fondo.update(delta);
        jIzquierda.update(delta);
        jDerecha.update(delta);
        pelota.update(delta);
    };
    }


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

    public Boolean getMusica() {
        return musica;
    }

    public void setMusica(Boolean musica) {
        this.musica = musica;
    }
}