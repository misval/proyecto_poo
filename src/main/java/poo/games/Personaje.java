package poo.games;

import java.awt.*;
import java.awt.geom.*;
import poo.games.circus_charlie.Montura;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.*; //nuevo para sonido


//import processing.core.*;
///   http://jsfiddle.net/LyM87/
/// https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java/37758533
public class Personaje extends ObjetoGrafico {

	private boolean onGround = false;

	final int DIRECCION_DERECHA = 0;
	final int DIRECCION_IZQUIERDA = 1;

	final int ESTADO_QUIETO = -1;
	final int ESTADO_CAMINANDO = 0;
	final int ESTADO_SALTANDO = 1;

	int direccionActual;
	int estadoActual;
	double tempVelocity;

	protected double velocityX = 4.0;
	protected double velocityY = 0.0;
	protected double gravity = 0.5;
	protected double angulo=0.0;

	protected int direccionAngulo= 1;

	public final int POSICION_Y_PISO=335;

	public Montura getMontura() {
		return montura;
	}

	public void setMontura(Montura montura) {
		this.montura = montura;
	}

	private Montura montura;

	public Personaje(String filename){
		super(filename);
	}

	public void jump() {
		if (onGround) {
			tempVelocity = velocityX;
			estadoActual = ESTADO_SALTANDO;
			velocityY = -12.0;
			onGround = false;
		}
	}

	public void jumpEnd() {
		if(velocityY < -6.0){
			estadoActual = ESTADO_SALTANDO;
			velocityY = -6.0;
		}
	}

	public void quieto() {
		estadoActual = ESTADO_QUIETO;
	}

	public void left() {
		if(estadoActual != ESTADO_SALTANDO) {
			velocityX = -4.0;
			estadoActual = ESTADO_CAMINANDO;
			direccionActual = DIRECCION_IZQUIERDA;
		}
//		this.setX(punto.getX() + velocityX);
	}

	public void right() {
		if(estadoActual != ESTADO_SALTANDO) {
			velocityX = 4.0;
			estadoActual = ESTADO_CAMINANDO;
			direccionActual = DIRECCION_DERECHA;
		}
//		this.setX(punto.getX() + velocityX);
	}

	public void update(double delta) {
		velocityY += gravity;
		System.out.println(this.getY());
		if (estadoActual == ESTADO_CAMINANDO) {
			this.setY(this.getY() + velocityY);
			this.setX(this.getX() + velocityX);
		}

		if(estadoActual == ESTADO_SALTANDO) {
			this.setY(this.getY() + velocityY);
			this.setX(this.getX() + tempVelocity);
		}

		Mundo m = Mundo.getInstance();

        /* Rebota contra los margenes X del mundo */
        if ((this.getX() + (this.getWidth())) > m.getWidth()) {
            this.setX(m.getWidth() - (this.getWidth()));
            velocityX= -1;
        }

		/* Rebota contra la X=0 del mundo */
        if ((getX()) < 0) {
            velocityX= -1;
            this.setX(0);
        }

        if (getY() > POSICION_Y_PISO) {
			if (montura != null) {
				this.setY(montura.getY() - montura.getHeight());
			} else {
				this.setY(POSICION_Y_PISO);
			}
            velocityY = 0.0;
            onGround = true;
			estadoActual = ESTADO_QUIETO;
		}
	}

	private void rotarImagenGrados(double ang){
			angulo +=ang;
	}

//	 public void display(Graphics2D g2) {
//		g2.drawImage(sprite,(int) this.getX(),(int) this.getY(),null);
//		System.out.println(this.getY());
//	}
}