package poo.games;

import poo.games.circus_charlie.CircusCharlie;
import poo.games.pong.Pong;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.entropyinteractive.*; //las librerias JGame,GameLoop,KeyBoard,Mouse,etc...

public class GamePlatform extends JPanel implements ActionListener {

	JGame juego;
	Thread t;

	public GamePlatform() {
		int filas = 2;
		int columnas = 0;
		int separacion = 0;
		JPanel fondo = new JPanel();
		JPanel barraSuperior = new JPanel();
		JPanel barraInferior = new JPanel();
		JPanel juegosLaterales = new JPanel();
		JPanel juegosCentral = new JPanel();

		this.add(fondo);
		fondo.add(barraSuperior);
		fondo.add(barraInferior);
		barraInferior.add(juegosLaterales);
		barraInferior.add(juegosCentral);
		
		juegosLaterales.setBackground(Color.BLACK);
		juegosCentral.setBackground(Color.CYAN);
		barraInferior.setBackground(Color.GREEN);
		barraSuperior.setBackground(Color.PINK);
		
		barraInferior.setLayout(new GridLayout(1, 2,0,0));
		juegosLaterales.setLayout(new GridLayout(2,1,10,10));
		fondo.setLayout(new GridLayout(filas, columnas, separacion, separacion));
		this.setLayout(new GridLayout(1,0,0,0));

		String[] arrEtiquetas = { "Pong", "CircusCharlie"};
		JButton boton;

		for (String etiqueta : arrEtiquetas) {

			boton = new JButton(etiqueta);

			boton.addActionListener(this);
			juegosLaterales.add(boton);
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Pong")) {
			juego = new Pong();

			t = new Thread() {
				public void run() {
					juego.run(1.0 / 60.0);
				}
			};

			t.start();
		}

		if (e.getActionCommand().equals("CircusCharlie")) {
			juego = new CircusCharlie();

			t = new Thread() {
				public void run() {
					juego.run(1.0 / 60.0);
				}
			};

			t.start();
		}

	}

	public static void main(String... z) {
		JFrame f = new JFrame("Steam");

		f.add(new GamePlatform());
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.pack();
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

}