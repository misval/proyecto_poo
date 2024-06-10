package poo.games;

import java.util.Map;

public class Jugador {
    String nombre;
    int puntosPong;
    int puntosCharlie;

    double contador=0;
    protected String salto = new String("imagenes/SaltoMarron.gif");
    protected String movimiento1 = new String("imagenes/MarronCamina1.gif");
    protected String movimiento2 = new String("imagenes/MarronCamina2.gif");

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntosPong() {
        return puntosPong;
    }

    public void setPuntosPong(int puntosPong) {
        this.puntosPong = puntosPong;
    }

    public int getPuntosCharlie() {
        return puntosCharlie;
    }

    public void setPuntosCharlie(int puntosCharlie) {
        this.puntosCharlie = puntosCharlie;
    }
}
