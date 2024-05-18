package poo.games;

import java.awt.*;
import java.awt.geom.*;

import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import java.net.*; //nuevo para sonido


//import processing.core.*;
///   http://jsfiddle.net/LyM87/
/// https://stackoverflow.com/questions/37758061/rotate-a-buffered-image-in-java/37758533
public class Personaje extends ObjetoGrafico implements ObjetoMovible {

	private boolean onGround = false;
	private boolean saltando=false;
 

	final int DIRECCION_DERECHA = 0;
	final int DIRECCION_IZQUIERDA = 1;

	final int ESTADO_QUIETO = -1;
	final int ESTADO_CAMINANDO = 0;
	final int ESTADO_ARROJANDO_GRANADA = 4;
	final int ESTADO_MURIENDO = 5;
	int direccionActual;
	int estadoActual;


	protected double velocityX = 4.0;
	protected double velocityY = 0.0;
	protected double gravity = 0.5;
	protected double angulo=0.0;

	protected int direccionAngulo= 1;

	public final int POSICION_Y_PISO=360;

	public Personaje(String filename){
			super(filename, new Dimension(), new Point());

	}


	public void jump() {

		if (onGround) {
			    velocityY = -12.0;
        		onGround = false;
			 
		}
		 
	}

	public void jumpEnd() {

		if(velocityY < -6.0){
			velocityY = -6.0;
		}
	 
	 

	}
	public void quieto() {
		estadoActual = ESTADO_QUIETO;
		//acceleration.mult(0);
	}
	public void left() {
		velocityX = -4.0;
		direccionActual = DIRECCION_IZQUIERDA;

		estadoActual = ESTADO_CAMINANDO;
		direccionAngulo=-1;
	}

	public void right() {

		velocityX = 4.0;

		direccionActual = DIRECCION_DERECHA;
		estadoActual = ESTADO_CAMINANDO;

		direccionAngulo= 1;
	}

	public void update(double delta) {

		velocityY += gravity;
    	positionY += velocityY;
    	positionX += velocityX;


		angulo=(angulo % 360);

		Mundo m = Mundo.getInstance();

		/* Rebota contra los margenes X del mundo */
		if ((positionX+ (this.getWidth())) > m.getWidth()) {
			//positionX = m.getWidth() - (this.getWidth());
			velocityX *= -1 ;
		}
		/* Rebota contra la X=0 del mundo */
		if ((positionX) < 0) {
			velocityX *= -1  ;
			positionX = 0;
		}

	    if(positionY > POSICION_Y_PISO){
	        positionY = POSICION_Y_PISO;
	        velocityY = 0.0;
	        onGround = true;
	        angulo=0;
	        /*ya toco el piso*/
	    }
	    if ( velocityY != 0.0){
			//mientras este saltando
	    	this.rotarImagenGrados(10 * direccionAngulo);
	    }
     
	}


	private void rotarImagenGrados(double ang){
			
			
			angulo +=ang;
			//double rads = Math.toRadians(angulo);
			 
		 

	}


	 public void display(Graphics2D g2) {
	 	/*Redefinicion de Display para poder hacer la rotacion cuando salta*/

	 	AffineTransform transform = new AffineTransform();
		transform.rotate(Math.toRadians(angulo), this.getX() + getWidth()/2, this.getY() + getHeight()/2);


		AffineTransform old = g2.getTransform();
		g2.transform(transform);

		g2.drawImage(imagen,(int) this.positionX,(int) this.positionY,null);

		g2.setTransform(old);
  	}


}