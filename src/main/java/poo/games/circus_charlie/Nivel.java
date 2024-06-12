package poo.games.circus_charlie;

import poo.games.*;

import java.awt.*;

public abstract class Nivel {
    protected Fondo fondo;
    protected Personaje charlie;
    protected Camara cam;
    protected Meta meta;
    protected Integer vidas;
    protected Integer bonus;
    protected Integer puntosTotales = 0 ;
    private double contador;
    private int banderaBonus = 10000;

    public Camara getCam() {
        return cam;
    }
    public Personaje getCharlie() {
        return charlie;
    }

//  El circus necesita la meta para y el charlie para comprobar si hay colision
//  Si la hay hace los mismo en los 3 niveles que es:
//  sumar puntos del nivel (puntos a traves de nivel + bonus) a puntos totales
//  nivelActual++ para pasar a otro nivel
    public Meta getMeta() {
        return meta;
    }

//  el circus necesita este metodo para comprobar que antes de la actualizacion del nuevo update no haya quedado sin vidas
    public Integer getVidas() { return vidas; }

//  para sumar a puntos totales
    public Integer getPuntosTotales() { return puntosTotales; }

//  para cuando pasa el nivel obtiene los bonus y se lo suma a los puntos del nivel
    public Integer getBonus() { return bonus; }

//  para que cada nivel pueda ir actualizando los puntos por cada interaccion
    public void setPuntosTotales(Integer puntosTotales) {
        this.puntosTotales += puntosTotales;
        if(getPuntosTotales() / banderaBonus > 0) {
            setVidas(getVidas()+1);
            banderaBonus += 10000;
        }
    }

//  para actualizar las vidas por si pierde y restarle
    public void setVidas(Integer vidas) {
        this.vidas = vidas;
    }

//  para cuando cambio de nivel o pierda, se pueda reiniciar el bonus
    public void reiniciarBonus() {
        this.bonus = 5000;
    }

    public abstract void draw(Graphics2D g);
    public abstract void update();

//  para que el bonus vaya descontandose de a poco
    public void animacionBonus() {
        contador++;
        if(contador % 7 == 1) {
            bonus--;
        } else contador = 0;
    }
}
