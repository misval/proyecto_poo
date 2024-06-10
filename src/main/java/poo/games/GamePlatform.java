package poo.games;

import poo.games.circus_charlie.CircusCharlie;
import poo.games.pong.Pong;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.entropyinteractive.*; //las librerias JGame,GameLoop,KeyBoard,Mouse,etc...

public class GamePlatform extends JFrame implements ActionListener {

//	JGame juego;
//	Thread t;

	public GamePlatform() {
		super("StEaM");
		setSize(1000, 500);


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});

		Panel container = new Panel();
		Panel menuVeticalIzquierdo = new Panel();
		Panel navBar = new Panel();
		Panel juegos = new Panel();
//		ScrollPane juegos = new ScrollPane();

		menuVeticalIzquierdo.setBackground(Color.GREEN);
		juegos.setBackground(Color.BLUE);
		navBar.setBackground(Color.RED);

//		CONTAINER PRINCIPAL
		container.setLayout(new BorderLayout());
		container.add(navBar, BorderLayout.NORTH);
		container.add(juegos, BorderLayout.CENTER);
		container.add(menuVeticalIzquierdo, BorderLayout.WEST);

//		MENU VERTICAL IZQUIERDO
		Label epicGames = new Label("Epic Games");
		Label homeLabel = new Label("Home");
		Label storeLabel = new Label("Store");
		Label libraryLabel = new Label("My Games");

		menuVeticalIzquierdo.setLayout(new BorderLayout());
		Panel menuVerticalLabels = new Panel();
		menuVerticalLabels.setLayout(new GridLayout(4, 1));
		Panel emptyPanel = new Panel();

		menuVerticalLabels.add(epicGames);
		menuVerticalLabels.add(homeLabel);
		menuVerticalLabels.add(storeLabel);
		menuVerticalLabels.add(libraryLabel);

		menuVeticalIzquierdo.add(menuVerticalLabels, BorderLayout.NORTH);
		menuVeticalIzquierdo.add(emptyPanel, BorderLayout.CENTER);
		menuVeticalIzquierdo.add(emptyPanel, BorderLayout.SOUTH);

//		NAVBAR
		navBar.setLayout(new BorderLayout());
		Panel navBarItems = new Panel();

		Label searchLabel = new Label("Search");
		Label acountLabel = new Label("Account ");

		navBarItems.setLayout(new GridLayout(1, 2));
		navBarItems.add(searchLabel);
		navBarItems.add(acountLabel);

		navBar.add(navBarItems, BorderLayout.EAST);

		add(container);

//		TODO: CREAR LAS CARDS PARA CADA JUEGO

		Panel juegosContainer = new Panel();
//		JUEGOS CONTAINER
		juegos.setLayout(new GridLayout(1, 1));
		juegosContainer.setLayout(new GridLayout(1, 4));

//		JUEGO PANEL
//		CIRCUS CHARLIE
		Panel circusCharlie = new Panel();
		circusCharlie.setLayout(new GridLayout(3, 1));

		circusCharlie.setSize(new Dimension(40, 60));

		Panel imageCircus = new Panel();
		Label titleCircus = new Label("Circus Charlie");
		Button playCircus = new Button("Circus Charlie");

		playCircus.addActionListener(this);

		imageCircus.setBackground(Color.BLACK);

		circusCharlie.add(imageCircus);
		circusCharlie.add(titleCircus);
		circusCharlie.add(playCircus);

//		PONG
		Panel pong = new Panel();
		pong.setLayout(new GridLayout(3, 1));

		pong.setSize(new Dimension(40, 60));


		Panel imagePong = new Panel();
		Label titlePong = new Label("Pong");
		Button playPong = new Button("Pong");

		playPong.addActionListener(this);

		imagePong.setBackground(Color.BLACK);

		pong.add(imagePong);
		pong.add(titlePong);
		pong.add(playPong);

		juegosContainer.add(circusCharlie);
		juegosContainer.add(pong);
		juegos.add(juegosContainer);


//		JPanel fondo = new JPanel();
//		JPanel barraSuperior = new JPanel();
//		JPanel barraInferior = new JPanel();
//		JPanel juegosLaterales = new JPanel();
//		JPanel juegosCentral = new JPanel();
//
//		this.add(juegosLaterales);
////		fondo.add(barraSuperior);
////		fondo.add(barraInferior);
//		barraInferior.add(juegosLaterales);
//		barraInferior.add(juegosCentral);
//
//		juegosLaterales.setBackground(Color.BLACK);
//		juegosCentral.setBackground(Color.CYAN);
//		barraInferior.setBackground(Color.GREEN);
//		barraSuperior.setBackground(Color.PINK);
//
//		barraInferior.setLayout(new GridLayout(1, 2,0,0));
//		juegosLaterales.setLayout(new GridLayout(2,1,0,0));
//		fondo.setLayout(new GridLayout(2, 0, 0, 0));
//		this.setLayout(new GridLayout(1,0,0,0));
//
//		String[] arrEtiquetas = { "Pong", "CircusCharlie"};
//		JButton boton;
//
//		for (String etiqueta : arrEtiquetas) {
//
//			boton = new JButton(etiqueta);
//
//			boton.addActionListener(this);
//			juegosLaterales.add(boton);
//		}


	}

//	public void actionPerformed(ActionEvent e) {};

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("Pong")) {
			Pong juego = new Pong();

			Thread t = new Thread() {
				public void run() {
					juego.run(1.0 / 60.0);
				}
			};
			t.start();
		}

		if (e.getActionCommand().equals("Circus Charlie")) {
			CircusCharlie juego = new CircusCharlie();

			Thread t = new Thread() {
				public void run() {
					juego.run(1.0 / 60.0);
				}
			};

			t.start();
		}
	}

	public static void main(String[] args) {
		GamePlatform gamesWindow = new GamePlatform();
		gamesWindow.setVisible(true);
	}

}