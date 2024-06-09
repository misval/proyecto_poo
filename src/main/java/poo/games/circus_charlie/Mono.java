package poo.games.circus_charlie;

import poo.games.ObjetoGrafico;
import java.awt.*;

public class Mono extends Obstaculo{

    final int DIRECCION_DERECHA = 0;
    final int DIRECCION_IZQUIERDA = 1;

    private double desplazamiento;

    Mono(String sprite) {
        super(sprite);
        desplazamiento = 1.0;
    };


    public double getDesplazamiento() {
        return desplazamiento;
    }

    public void setDesplazamiento(double desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    //  deberia recibir la direccion y el desplazamiento
    public void moverse(Integer direccion) {
        if(direccion == DIRECCION_DERECHA) {
            this.setX(this.getX() + desplazamiento);
        } else if (direccion == DIRECCION_IZQUIERDA) {
            this.setX(this.getX() - desplazamiento);
        }
    }
}
