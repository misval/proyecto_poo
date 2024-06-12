package poo.games.circus_charlie;

public class BolsaBonus extends Obstaculo{
    final int DIRECCION_DERECHA = 0;
    final int DIRECCION_IZQUIERDA = 1;
    private double desplazamiento = 1.0;


    BolsaBonus(String sprite){
        super(sprite);
    }

    public void moverse(Integer direccion) {
        if(direccion == DIRECCION_DERECHA) {
            this.setX(this.getX() + desplazamiento);
        } else if (direccion == DIRECCION_IZQUIERDA) {
            this.setX(this.getX() - desplazamiento);
        }
    }
}
