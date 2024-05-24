package poo.games;

import java.awt.*;

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

	public final int POSICION_Y_PISO=355;

	public Personaje(String filename){
		super(filename);
		setColision(new Rectangle((int)getX()+15, (int)getY()+15, getSprite().getWidth()-15, getSprite().getHeight()-15));
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

	public void quieto(){
		estadoActual = ESTADO_QUIETO;
	}

	public void left() {
		if(estadoActual != ESTADO_SALTANDO) {
			velocityX = -4.0;
			estadoActual = ESTADO_CAMINANDO;
			direccionActual = DIRECCION_IZQUIERDA;
		}
	}

	public void right() {
		if(estadoActual != ESTADO_SALTANDO){
			velocityX = 4.0;
			estadoActual = ESTADO_CAMINANDO;
			direccionActual = DIRECCION_DERECHA;
		}
	}

	public void update(double delta) {
		velocityY += gravity;
		if (estadoActual == ESTADO_CAMINANDO ) {
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
            this.setY(POSICION_Y_PISO);
            velocityY = 0.0;
            onGround = true;
			quieto();
		}

		getColision().setLocation((int)getX() + 15, (int)getY() + 15);

	}

	 public void display(Graphics2D g2) {
		super.display(g2, 2,40, 60);
	}
}