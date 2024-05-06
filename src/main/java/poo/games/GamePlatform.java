package poo.games;

import poo.games.circus_charlie.CircusCharlie;
import poo.games.pong.Pong;

import javax.swing.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;

import java.util.*;

public class GamePlatform extends JFrame {
    public GamePlatform() {
        super("StEaM");
        setSize(600, 400);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        Panel mainPanel = new Panel();

        Label titleLabel = new Label("Mis Juegos", Label.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(titleLabel);

        //CIRCUS CHARLIE
        Panel circusCharlieContainer = new Panel();
        circusCharlieContainer.setLayout(new BoxLayout(circusCharlieContainer, BoxLayout.Y_AXIS));
        circusCharlieContainer.setBackground(Color.WHITE);

        Label circusCharlieContainerTitleLabel = new Label("Circus Charlie", Label.CENTER);
        circusCharlieContainerTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        circusCharlieContainer.add(circusCharlieContainerTitleLabel);

        Image circusCharlieImage = Toolkit.getDefaultToolkit().getImage("circus_charlie.jpg");
        JLabel circusPicLabel = new JLabel(new ImageIcon(circusCharlieImage));
        circusCharlieContainer.add(circusPicLabel);

        Button playButtonCircus = new Button("Jugar");
        playButtonCircus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CircusCharlie circusCharlie = new CircusCharlie();
            }
        });
        circusCharlieContainer.add(playButtonCircus);

        //PONG
        Panel pongContainer = new Panel();
        pongContainer.setLayout(new BoxLayout(pongContainer, BoxLayout.Y_AXIS));
        pongContainer.setBackground(Color.WHITE);

        Label pongContainerTitleLabel = new Label("Pong", Label.CENTER);
        pongContainerTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pongContainer.add(pongContainerTitleLabel);

        Image pongImage = Toolkit.getDefaultToolkit().getImage("pong.jpg");
        JLabel pongPicLabel = new JLabel(new ImageIcon(pongImage));
        pongContainer.add(pongPicLabel);

        Button playButtonPong = new Button("Jugar");
        playButtonPong.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pong pong = new Pong();
            }
        });
        pongContainer.add(playButtonPong);

        mainPanel.add(circusCharlieContainer);
        mainPanel.add(pongContainer);

        add(mainPanel);
    }

    public static void main(String[] args) {
        // Crear y mostrar la ventana
        GamePlatform myGamesWindow = new GamePlatform();
        myGamesWindow.setVisible(true);
    }
}
