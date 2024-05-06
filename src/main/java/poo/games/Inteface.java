package poo.games;

import javax.swing.*;
import java.awt.*;

public class Inteface {
    public static void main(String[] args) {
        MiPantalla f = new MiPantalla();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}

class MiPantalla extends JFrame{

    public MiPantalla(){
        Toolkit pantalla = Toolkit.getDefaultToolkit();
        Dimension tamanioPantalla = pantalla.getScreenSize();
        int alto = tamanioPantalla.width , largo = tamanioPantalla.height;
        setSize(960, 600);
        setLocation(alto-100, largo+100);
        setResizable(true);
    }
}