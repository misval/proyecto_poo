package poo.games;

import poo.games.circus_charlie.CircusCharlie;
import poo.games.pong.Pong;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import javax.imageio.*;
import javax.swing.*;
import java.awt.Toolkit;



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
		Panel containerJuegos = new Panel();
//		ScrollPane juegos = new ScrollPane();

		menuVeticalIzquierdo.setBackground(Color.GREEN);
		juegos.setBackground(Color.BLUE);
		navBar.setBackground(Color.RED);

//		CONTAINER PRINCIPAL
		container.setLayout(new BorderLayout());
		container.add(navBar, BorderLayout.NORTH);
		container.add(containerJuegos, BorderLayout.CENTER);
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
		Panel emptyPanel2 = new Panel();
		Button configuraciones = new Button("Configuraciones");
		emptyPanel2.add(configuraciones);

		menuVerticalLabels.add(epicGames);
		menuVerticalLabels.add(homeLabel);
		menuVerticalLabels.add(storeLabel);
		menuVerticalLabels.add(libraryLabel);

		menuVeticalIzquierdo.add(menuVerticalLabels, BorderLayout.NORTH);
		menuVeticalIzquierdo.add(emptyPanel, BorderLayout.CENTER);
		menuVeticalIzquierdo.add(emptyPanel2, BorderLayout.SOUTH);

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

// JUEGOS PANELS
		Panel juegosContainer = new Panel();
		containerJuegos.setLayout(new BorderLayout());

		Panel paddingSuperior = new Panel();
		paddingSuperior.setPreferredSize(new Dimension(0, 20));
		paddingSuperior.setBackground(Color.BLUE);
		Panel paddingIzquierdo = new Panel();
		paddingIzquierdo.setPreferredSize(new Dimension(20, 0));
		paddingIzquierdo.setBackground(Color.BLUE);

//		JUEGOS CONTAINER
		juegos.setLayout(new GridLayout(2, 4));
		containerJuegos.add(paddingSuperior, BorderLayout.NORTH);
		containerJuegos.add(juegos, BorderLayout.CENTER);
		containerJuegos.add(paddingIzquierdo, BorderLayout.WEST);
		juegosContainer.setLayout(new GridLayout(1, 4, 20, 10));

		Panel auxPanel1 = new Panel();
		Panel auxPanel2 = new Panel();

//		JUEGO PANEL
//		CIRCUS CHARLIE
		Panel circusCharlie = new Panel();
		circusCharlie.setLayout(new GridLayout(3, 1));

//		circusCharlie.setSize(new Dimension(40, 60));

		Image imgC = Toolkit.getDefaultToolkit().getImage("imagenes/CircusCharlie_arcadeflyer.png");
		JLabel imageCircus = new JLabel(new ImageIcon(imgC));
		Label titleCircus = new Label("Circus Charlie");

//		panel de botones
		Panel buttonsPanelC = new Panel();
		buttonsPanelC.setLayout(new GridLayout(2, 2, 5, 0));
		Panel auxPanelButtons2 = new Panel();
		Panel auxPanelButtons1 = new Panel();

		Button playCircus = new Button("Circus");
		Button viewTableroCircus = new Button("Ver tablero");

		buttonsPanelC.add(auxPanelButtons1);
		buttonsPanelC.add(auxPanelButtons2);
		buttonsPanelC.add(playCircus);
		buttonsPanelC.add(viewTableroCircus);

		playCircus.addActionListener(this);

		imageCircus.setBackground(Color.BLACK);

		circusCharlie.add(imageCircus);
		circusCharlie.add(titleCircus);
		circusCharlie.add(buttonsPanelC);

//		PONG
		Panel pong = new Panel();
		pong.setLayout(new GridLayout(3, 1));
		Panel buttonsPanelP = new Panel();
		buttonsPanelP.setLayout(new GridLayout(2, 2, 5, 0));

		Panel auxPanelButtons3 = new Panel();
		Panel auxPanelButtons4 = new Panel();

		buttonsPanelP.add(auxPanelButtons3);
		buttonsPanelP.add(auxPanelButtons4);


		pong.setSize(new Dimension(40, 60));

		Panel imagePong = new Panel();
		Label titlePong = new Label("Pong");
		Button playPong = new Button("Pong");
		Button viewTableroPong = new Button("Ver tablero");

		buttonsPanelP.add(playPong);
		buttonsPanelP.add(viewTableroPong);

		playPong.addActionListener(this);

		imagePong.setBackground(Color.BLACK);

		pong.add(imagePong);
		pong.add(titlePong);
		pong.add(buttonsPanelP);

		juegosContainer.add(circusCharlie);
		juegosContainer.add(pong);
		juegosContainer.add(auxPanel1);
		juegosContainer.add(auxPanel2);
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

		if (e.getActionCommand().equals("Circus")) {
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