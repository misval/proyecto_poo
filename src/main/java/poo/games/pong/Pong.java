package poo.games.pong;

import com.entropyinteractive.*;

import java.awt.*;

public class Pong extends JGame {
    public Pong() {
        super("Pong", 600, 300);
        
    }

    @Override
    public void gameStartup() {

    }

    @Override
    public void gameUpdate(double v) {
        System.out.println("game Startup");

    }

    @Override
    public void gameDraw(Graphics2D graphics2D) {
        System.out.println("game Startup");

    }
    @Override
    public void gameShutdown() {
        System.out.println("game Startup");
    }
}
