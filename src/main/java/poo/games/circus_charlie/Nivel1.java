package poo.games.circus_charlie;

import java.awt.*;

import poo.games.Personaje;

public class Nivel1 extends Nivel {
    Nivel1() {
        super();
    }

    @Override
    public void moverDerCharlie() {
        getCharlie().setSprite(null);
        super.moverDerCharlie();
    }

    public void draw(Graphics2D g){
        super.draw(g);
    }
}
