package poo.games.circus_charlie;

import poo.games.ObjetoGrafico;
import java.awt.*;

public class Mono extends Obstaculo{

    protected final int DIRECCION_DERECHA = 0;
    protected final int DIRECCION_IZQUIERDA = 1;

    protected double desplazamiento;
    protected double contador=0;
    protected String salto = new String("imagenes/SaltoMarron.gif");
    protected String movimiento1 = new String("imagenes/MarronCamina1.gif");
    protected String movimiento2 = new String("imagenes/MarronCamina2.gif");

    Mono(String sprite) {
        super("imagenes/MarronCamina1.gif");
        desplazamiento = 1.0;
    };

    //  deberia recibir la direccion y el desplazamiento
    public void moverse(Integer direccion) {
        if(direccion == DIRECCION_DERECHA) {
            this.setX(this.getX() + desplazamiento);
        } else if (direccion == DIRECCION_IZQUIERDA) {
            this.setX(this.getX() - desplazamiento);
        }
    }
    public void animacion() {
        contador++;

        System.out.println(contador);
        if(contador <= 20){
            setSprite(movimiento1);
        }else if(contador <= 40 && contador > 20){
            setSprite(movimiento2);
        }else contador = 0;
    }

}