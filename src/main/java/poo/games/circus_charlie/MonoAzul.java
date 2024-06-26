package poo.games.circus_charlie;

import poo.games.ObjetoGrafico;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MonoAzul extends Mono {


    private boolean onGround = false;
    private int POSICION_Y_PISO=270;

    protected String salto = new String("imagenes/AzulMono1.gif");
    protected String movimiento1 = new String("imagenes/AzulMono2.gif");
    protected String movimiento2 = new String("imagenes/AzulMono3.gif");
    protected String movimiento3 = new String("imagenes/AzulMono4.gif");
    // Variable para controlar la altura máxima del salto
    private int alturaSalto = 0;
    private final double maxAlturaSalto = 25;

    MonoAzul(String Sprite) {
        super(Sprite);
        desplazamiento = 3;
        this.moverse(1);
    };

    // Lógica de movimiento del mono azul
    public void mover() {
        if (onGround) {
            if (alturaSalto < maxAlturaSalto) {
                // Realizar el salto
                setSprite(salto);
                if(getY() > 180) {
                    this.setY(this.getY() - 4);
                    setDesplazamiento(4);
                }else setDesplazamiento(3);
                alturaSalto++;
            }else{
                onGround = false;
                setSprite(movimiento1);
            }
        }else if(getY() < POSICION_Y_PISO) {
            // Bajar automáticamente al final del salto
            this.setY(this.getY() + 5);
            alturaSalto = 0;
        }
        if(getY() > 272 && onGround) {
            setSprite(movimiento2);
            setSprite(movimiento3);
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
    public void saltar() {
        if (!onGround) {
            onGround = true;
        }
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