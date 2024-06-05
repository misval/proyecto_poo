package poo.games;

import poo.games.circus_charlie.CircusCharlie;
import poo.games.pong.Pong;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class GamePlatform extends JFrame implements ActionListener {
	public GamePlatform() {
		super("Nintendo Platform");
		setSize(1000, 600);


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});

		Panel container = new Panel();
		JPanel menuVeticalIzquierdo = new JPanel();
		menuVeticalIzquierdo.setBorder(new LineBorder(Color.white));
		JPanel navBar = new JPanel();
		navBar.setBorder(new LineBorder(Color.white));
		JPanel juegos = new JPanel();
		JPanel containerJuegos = new JPanel();
//		ScrollPane juegos = new ScrollPane();

//		CONTAINER PRINCIPAL
		container.setLayout(new BorderLayout());
		container.add(navBar, BorderLayout.NORTH);
		container.add(containerJuegos, BorderLayout.CENTER);
		container.add(menuVeticalIzquierdo, BorderLayout.WEST);

		Font retroFont = new Font("Retro Gaming", Font.PLAIN, 19);

		//		MENU VERTICAL IZQUIERDO
		JLabel homeLabel = new JLabel("Home");
		homeLabel.setFont(retroFont);
		homeLabel.setForeground(Color.WHITE);
		homeLabel.setBackground(Color.BLACK);
		homeLabel.setBorder(new LineBorder(Color.white, 1));
		JLabel storeLabel = new JLabel("Store");
		storeLabel.setFont(retroFont);
		storeLabel.setForeground(Color.WHITE);
		storeLabel.setBackground(Color.BLACK);
		storeLabel.setBorder(new LineBorder(Color.WHITE, 1));
		JLabel libraryLabel = new JLabel("My Games");
		libraryLabel.setFont(retroFont);
		libraryLabel.setForeground(Color.WHITE);
		libraryLabel.setBackground(Color.YELLOW);
		libraryLabel.setBorder(new LineBorder(Color.WHITE, 1));


		menuVeticalIzquierdo.setLayout(new BorderLayout());
		Panel menuVerticalLabels = new Panel();
		menuVerticalLabels.setLayout(new GridLayout(3, 1));
		Panel emptyPanel = new Panel();
		emptyPanel.setBackground(Color.BLACK);
		Panel buttonsMenuIzquierdo = new Panel();
		buttonsMenuIzquierdo.setLayout(new GridLayout(2, 1));
		buttonsMenuIzquierdo.setBackground(Color.BLACK);

//		configuraciones Button
		JButton configuraciones = new JButton("Configuraciones");
		configuraciones.setFont(retroFont);
		configuraciones.setBackground(Color.BLACK);
		configuraciones.setForeground(Color.WHITE);
		configuraciones.setBorder(new LineBorder(Color.WHITE, 1));

//		RANKING
		JButton rankings = new JButton("Rankings");
		rankings.setFont(retroFont);
		rankings.setBackground(Color.BLACK);
		rankings.setForeground(Color.WHITE);
		rankings.setBorder(new LineBorder(Color.WHITE, 1));

		buttonsMenuIzquierdo.add(rankings);
		buttonsMenuIzquierdo.add(configuraciones);

		menuVerticalLabels.add(homeLabel);
		menuVerticalLabels.add(storeLabel);
		menuVerticalLabels.add(libraryLabel);
		menuVerticalLabels.setBackground(Color.BLACK);

		menuVeticalIzquierdo.add(menuVerticalLabels, BorderLayout.NORTH);
		menuVeticalIzquierdo.add(emptyPanel, BorderLayout.CENTER);
		menuVeticalIzquierdo.add(buttonsMenuIzquierdo, BorderLayout.SOUTH);


//		NAVBAR
		navBar.setLayout(new BorderLayout());
		Panel navBarItems = new Panel();

		JLabel searchLabel = new JLabel("Search");
		searchLabel.setFont(retroFont);
		searchLabel.setForeground(Color.WHITE);
		searchLabel.setBorder(new LineBorder(Color.WHITE, 1));
		JLabel acountLabel = new JLabel("Account");
		acountLabel.setFont(retroFont);
		acountLabel.setForeground(Color.WHITE);
		acountLabel.setBorder(new LineBorder(Color.WHITE, 1));

		navBarItems.setLayout(new GridLayout(1, 2));
		navBarItems.add(searchLabel);
		navBarItems.add(acountLabel);
		navBar.add(navBarItems, BorderLayout.EAST);
		navBar.setBackground(Color.BLACK);
		navBarItems.setBackground(Color.BLACK);

		add(container);

// JUEGOS PANELS
		Panel juegosContainer = new Panel();
		containerJuegos.setLayout(new BorderLayout());

		Panel paddingSuperior = new Panel();
		paddingSuperior.setPreferredSize(new Dimension(0, 20));
		paddingSuperior.setBackground(Color.BLACK);
		Panel paddingIzquierdo = new Panel();
		paddingIzquierdo.setPreferredSize(new Dimension(20, 0));
		paddingIzquierdo.setBackground(Color.BLACK);

//		JUEGOS CONTAINER
		juegos.setBackground(Color.BLACK);
		containerJuegos.add(paddingSuperior, BorderLayout.NORTH);
		containerJuegos.add(juegos, BorderLayout.CENTER);
		containerJuegos.add(paddingIzquierdo, BorderLayout.WEST);

//		JUEGO PANEL
//		CIRCUS CHARLIE
		JPanel circusCharlie = new JPanel();
		circusCharlie.setLayout(new BorderLayout());

		JLabel imageCircus = new JLabel(new ImageIcon("src/main/resources/imagenes/CircusCharlie_arcadeflyer.png"));

//		PRINCIPAL BUTTONS
		JButton playCircus = new JButton("jugar circus charlie");
		playCircus.setBackground(Color.black);
		playCircus.setForeground(Color.WHITE);
		playCircus.setFont(retroFont);
		playCircus.setBorder(new LineBorder(Color.WHITE, 1));

		JPanel emptyButton = new JPanel();
		emptyButton.setBackground(Color.BLACK);

		JPanel buttonsCircus = new JPanel();
		buttonsCircus.setLayout(new GridLayout(2, 1));
		buttonsCircus.add(emptyButton);
		buttonsCircus.add(playCircus);

		playCircus.addActionListener(this);

		circusCharlie.add(imageCircus, BorderLayout.CENTER);
		circusCharlie.add(buttonsCircus, BorderLayout.SOUTH);

//		PONG
		JPanel pong = new JPanel();
		pong.setSize(265, 400);
		pong.setLayout(new BorderLayout());

		JLabel imagePong = new JLabel(new ImageIcon("src/main/resources/imagenes/pongFlyer.png"));

		JButton playPong = new JButton("jugar pong");
		playPong.setBackground(Color.black);
		playPong.setForeground(Color.WHITE);
		playPong.setBorder(new LineBorder(Color.black));
		playPong.setFont(retroFont);
		playPong.setBorder(new LineBorder(Color.WHITE, 1));

		JPanel emptyButton2 = new JPanel();
		emptyButton2.setBackground(Color.BLACK);

		JPanel buttonsPong = new JPanel();
		buttonsPong.setLayout(new GridLayout(2, 1));
		buttonsPong.add(emptyButton2);
		buttonsPong.add(playPong);

		playPong.addActionListener(this);

		pong.add(imagePong, BorderLayout.CENTER);
		pong.add(buttonsPong, BorderLayout.SOUTH);

		JPanel panelAux = new JPanel();
		JPanel panelAux1 = new JPanel();
		JPanel panelAux2 = new JPanel();
		JPanel panelAux3 = new JPanel();
		JPanel panelAux4 = new JPanel();
		panelAux.setBackground(Color.BLACK);
		panelAux1.setBackground(Color.BLACK);
		panelAux2.setBackground(Color.BLACK);
		panelAux3.setBackground(Color.BLACK);
		panelAux4.setBackground(Color.BLACK);

		juegosContainer.add(circusCharlie);
		juegosContainer.add(panelAux);
		juegosContainer.add(panelAux1);
		juegosContainer.add(panelAux2);
		juegosContainer.add(panelAux3);
		juegosContainer.add(panelAux4);
		juegosContainer.add(pong);
		juegosContainer.setMaximumSize(new Dimension(1000, 400));
		juegos.add(juegosContainer);
		juegos.setMaximumSize(new Dimension(1000, 400));


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

		if (e.getActionCommand().equals("jugar pong")) {
			Pong juego = new Pong();

			Thread t = new Thread() {
				public void run() {
					juego.run(1.0 / 60.0);
				}
			};

			t.start();
		}

		if (e.getActionCommand().equals("jugar circus charlie")) {
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