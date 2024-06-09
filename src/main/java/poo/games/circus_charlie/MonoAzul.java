package poo.games.circus_charlie;

import poo.games.ObjetoGrafico;
import java.awt.*;

public class MonoAzul extends Obstaculo{

    final int DIRECCION_DERECHA = 0;
    final int DIRECCION_IZQUIERDA = 1;

    private double desplazamiento;

    private boolean onGround = false;
    private int POSICION_Y_PISO=270;

    MonoAzul(String sprite) {
        super(sprite);
        desplazamiento = 3;
    };
    // Variable para controlar la altura máxima del salto

    int alturaSalto = 0;
    final double maxAlturaSalto = 50;
    // Lógica de movimiento del mono azul
    public void mover() {
        if (onGround) {
            if (alturaSalto < maxAlturaSalto) {
                // Realizar el salto
                this.setY(this.getY() - 1);
                alturaSalto++;
            }else{
                onGround = false;
            }
        }else if(getY() < POSICION_Y_PISO) {
            // Bajar automáticamente al final del salto
            this.setY(this.getY() + 1);
            alturaSalto = 0;
        }
    }
    public void saltar() {
        if (!onGround) {
            onGround = true;
        }
    }
    public int getPOSICION_Y_PISO(){
        return POSICION_Y_PISO;
    }

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