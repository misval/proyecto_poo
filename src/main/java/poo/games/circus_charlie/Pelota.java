package poo.games.circus_charlie;

import poo.games.ObjetoGrafico;

import java.awt.*;

public class Pelota extends Montura {
    final int DIRECCION_DERECHA = 0;
    final int DIRECCION_IZQUIERDA = 1;

    Pelota(String sprite) {
        super(sprite);
    };

//  deberia recibir la direccion y el desplazamiento
    public void moverse(Integer direccion, double desplazamiento) {
        if(direccion == DIRECCION_DERECHA) {
            this.setX(this.getX() + desplazamiento);
        } else if (direccion == DIRECCION_IZQUIERDA) {
            this.setX(this.getX() - desplazamiento);
        }
    }


}
