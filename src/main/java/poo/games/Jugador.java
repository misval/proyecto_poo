package poo.games;

import java.util.Map;

public class Jugador {
    String nombre;
    int puntosPong;
    int puntosCharlie;

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
