package poo.games.pong;

import com.entropyinteractive.JGame;
import java.awt.*;

public class Pong extends JGame{

    public Pong(){
        super("Pong", 800, 600);
    };

    public void gameStartup(){};

    public void gameUpdate(double var1){};
 
    public void gameDraw(Graphics2D var1){};
 
    public void gameShutdown(){};


    public static void main(String[] args) {
        System.out.println("Pong");
    }
}
