package poo.games.circus_charlie;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

import poo.games.ObjetoGrafico;

public class Aro {
    final int DIRECCION_DERECHA = 0;
    final int DIRECCION_IZQUIERDA = 1;

    private MedioAro mitadAtras, mitadAdelante;

    private double desplazamiento;
    double contador=0;
    protected String salto = new String("imagenes/SaltoMarron.gif");
    protected String movimiento1_1 = new String("imagenes/MitadAtrasAroGrande.png");
    protected String movimiento1_2 = new String("imagenes/MitadAtrasAroGrande2.png");
    protected String movimiento2_1 = new String("imagenes/MitadAdelanteAroGrande.png");
    protected String movimiento2_2 = new String("imagenes/MitadAdelanteAroGrande2.png");

    Aro(String sprite1, String sprite2) {
        desplazamiento = 1.0;
        mitadAtras = new MedioAro(sprite1);
        mitadAdelante = new MedioAro(sprite2);
    };

    public ObjetoGrafico getAro(){
        return mitadAdelante;
    }

    public void setColision(int x, int y, int width, int height){
        mitadAdelante.setColision(new Rectangle(x, y, width, height));
    }

    public Rectangle getColision(){
        return mitadAdelante.getColision();
    }
    
    public double getX(){
        return mitadAdelante.getX();
    }

    public double getY(){
        return mitadAdelante.getY();
    }

    public void setX(double x){
        mitadAdelante.setX(x);
    }

    public void setY(double x){
        mitadAdelante.setY(x);
    }

    public int getWidth(){
        return mitadAdelante.getWidth();
    }

    public int getHeight(){
        return mitadAdelante.getHeight();
    }

    public double getDesplazamiento() {
        return desplazamiento;
    }

    public void setDesplazamiento(double desplazamiento) {
        this.desplazamiento = desplazamiento;
    }

    public void display1(Graphics2D g) {
        mitadAtras.display(g);
    }

    public void display2(Graphics2D g) {
        mitadAdelante.display(g);
    }

    public void setPosition(int x, int y){
        mitadAtras.setX(x);
        mitadAdelante.setX(x + mitadAtras.getWidth());
        mitadAtras.setY(y);
        mitadAdelante.setY(y);
    }


    //  deberia recibir la direccion y el desplazamiento
    public void moverse(Integer direccion) {
        if(direccion == DIRECCION_DERECHA) {
            mitadAtras.setX(mitadAtras.getX() + desplazamiento);
            mitadAdelante.setX(mitadAdelante.getX() + desplazamiento);
            mitadAdelante.getColision().setLocation((int)(mitadAdelante.getColision().getX()+desplazamiento), (int)mitadAdelante.getColision().getY());
        } else if (direccion == DIRECCION_IZQUIERDA) {
            mitadAtras.setX(mitadAtras.getX() - desplazamiento);
            mitadAdelante.setX(mitadAdelante.getX() - desplazamiento);
            mitadAdelante.getColision().setLocation((int)(mitadAdelante.getColision().getX()-desplazamiento), (int)mitadAdelante.getColision().getY());
        }
    }
    public void animacion() {
        contador++;
        if(contador <= 20){
            mitadAtras.setSprite(movimiento1_1);
            mitadAdelante.setSprite(movimiento2_1);
        }else if(contador <= 40 && contador > 20){
            mitadAtras.setSprite(movimiento1_2);
            mitadAdelante.setSprite(movimiento2_2);
        }else contador = 0;
    }
}