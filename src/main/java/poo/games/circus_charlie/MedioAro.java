package poo.games.circus_charlie;

public class MedioAro extends Obstaculo{
    protected final int DIRECCION_DERECHA = 0;
    protected final int DIRECCION_IZQUIERDA = 1;

    protected double desplazamiento;

    MedioAro(String sprite) {
        super(sprite);
        desplazamiento = 1.0;
    }

    @Override
    public void moverse(Integer direccion) {
        if(direccion == DIRECCION_DERECHA) {
            this.setX(this.getX() + desplazamiento);
        } else if (direccion == DIRECCION_IZQUIERDA) {
            this.setX(this.getX() - desplazamiento);
        }
    }
}