package poo.games.circus_charlie;

import com.entropyinteractive.JGame;

import java.awt.*;
import java.util.*;

public class CircusCharlie extends JGame {
    private Map<String, Integer> scores = new HashMap<>();


    public CircusCharlie() {
        super("Circus Charlie", 600, 300);

    }

    @Override
    public void gameStartup() {
        System.out.println("game Startup");
    }

    @Override
    public void gameUpdate(double v) {
        System.out.println("game Startup circus");

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
