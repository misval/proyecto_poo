package poo.games;

import poo.games.circus_charlie.CircusCharlie;
import poo.games.pong.Pong;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class GamePlatform extends JFrame implements ActionListener {
	private JFrame iniciarSesionFrame;
	private JFrame configFrame;
	private JTextField nombreTextField;
	public Jugador jugadorActual = new Jugador();
	private JTextField inputJumpKey;
	private JTextField inputRightKey;
	private JTextField inputLeftKey;
	private JTextField inputEscKey;
	private JTextField inputPauseKey;
	private JFrame errorFrame;

	public GamePlatform() {
		super("Nintendo Platform");
		setSize(1000, 600);
		setLocation(180, 80);


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

		configuraciones.addActionListener(this);

//		RANKING
		JButton rankings = new JButton("Ranking");
		rankings.setFont(retroFont);
		rankings.setBackground(Color.BLACK);
		rankings.setForeground(Color.WHITE);
		rankings.setBorder(new LineBorder(Color.WHITE, 1));

		rankings.addActionListener(this);

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

		JLabel searchLabel = new JLabel("Buscar");
		searchLabel.setFont(retroFont);
		searchLabel.setForeground(Color.WHITE);
		searchLabel.setBorder(new LineBorder(Color.WHITE, 1));
		JButton iniciarSesionButton = new JButton("Iniciar sesion");
		iniciarSesionButton.setFont(retroFont);
		iniciarSesionButton.setForeground(Color.WHITE);
		iniciarSesionButton.setBackground(Color.BLACK);
		iniciarSesionButton.setBorder(new LineBorder(Color.WHITE, 1));

		iniciarSesionButton.addActionListener(this);

		navBarItems.setLayout(new GridLayout(1, 2));
		navBarItems.add(searchLabel);
		navBarItems.add(iniciarSesionButton);
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

//      VENTANA INICIAR SESION
		iniciarSesionFrame = new JFrame("Iniciar sesion");
		iniciarSesionFrame.setSize(250, 250);
		iniciarSesionFrame.setLocation(570, 260);

		JPanel containerIniciarSesion = new JPanel();
		containerIniciarSesion.setLayout(new GridLayout(3, 1));

		JLabel nombreLabel = new JLabel("Nombre: ");
		nombreLabel.setFont(retroFont);
		nombreLabel.setForeground(Color.white);
		nombreLabel.setSize(250, 100);
		nombreTextField = new JTextField();
		JButton ingresarNombreButton = new JButton("Ingresar");
		ingresarNombreButton.setFont(retroFont);
		ingresarNombreButton.setForeground(Color.white);
		ingresarNombreButton.setBackground(Color.BLACK);
		ingresarNombreButton.addActionListener(this);

//		CONTENEDOR DEL NOMBRE LABEL
		JPanel containerNombreLabel = new JPanel();
		containerNombreLabel.setLayout(new BorderLayout());

		JPanel auxPanelnl1 = new JPanel();
		auxPanelnl1.setBackground(Color.BLACK);
		JPanel auxPanelnl3 = new JPanel();
		auxPanelnl3.setBackground(Color.BLACK);
		JPanel auxPanelnl4 = new JPanel();
		auxPanelnl4.setBackground(Color.BLACK);

		containerNombreLabel.add(auxPanelnl1, BorderLayout.NORTH);
		containerNombreLabel.add(nombreLabel, BorderLayout.CENTER);
		containerNombreLabel.add(auxPanelnl3, BorderLayout.EAST);
		containerNombreLabel.add(auxPanelnl4, BorderLayout.WEST);

//		CONTENEDOR DEL INPUT NOMBRE
		JPanel containerNombreInput = new JPanel();
		containerNombreInput.setLayout(new BorderLayout());

		JPanel auxPanelni2 = new JPanel();
		auxPanelni2.setBackground(Color.BLACK);
		JPanel auxPanelni3 = new JPanel();
		auxPanelni3.setBackground(Color.BLACK);
		JPanel auxPanelni4 = new JPanel();
		auxPanelni4.setBackground(Color.BLACK);

		containerNombreInput.add(nombreTextField, BorderLayout.CENTER);
		containerNombreInput.add(auxPanelni2, BorderLayout.SOUTH);
		containerNombreInput.add(auxPanelni3, BorderLayout.EAST);
		containerNombreInput.add(auxPanelni4, BorderLayout.WEST);

//		CONTENEDOR DEL NOMBRE BUTTON
		JPanel containerNombreButton = new JPanel();
		containerNombreButton.setLayout(new BorderLayout());

		JPanel auxPanelnb2 = new JPanel();
		auxPanelnb2.setBackground(Color.BLACK);
		JPanel auxPanelnb3 = new JPanel();
		auxPanelnb3.setBackground(Color.BLACK);
		JPanel auxPanelnb4 = new JPanel();
		auxPanelnb4.setBackground(Color.BLACK);

		containerNombreButton.add(ingresarNombreButton, BorderLayout.CENTER);
		containerNombreButton.add(auxPanelnb2, BorderLayout.SOUTH);
		containerNombreButton.add(auxPanelnb3, BorderLayout.EAST);
		containerNombreButton.add(auxPanelnb4, BorderLayout.WEST);

		containerNombreLabel.setBackground(Color.BLACK);

		containerIniciarSesion.add(containerNombreLabel);
		containerIniciarSesion.add(containerNombreInput);
		containerIniciarSesion.add(containerNombreButton);

		iniciarSesionFrame.add(containerIniciarSesion);

//		VENTANA CONFIGURACIONES
		configFrame = new JFrame("Configuraciones");
		configFrame.setSize(300, 350);
		configFrame.setLocation(540, 200);

		JPanel mainContainerConfigGrid = new JPanel();
		mainContainerConfigGrid.setLayout(new GridLayout(7, 2, 0, 10));

		JPanel mainContainerConfig = new JPanel();
		mainContainerConfig.setLayout(new BorderLayout());

		JPanel mainContainerConfigInner = new JPanel();
		mainContainerConfigInner.setLayout(new BorderLayout());

		inputJumpKey = new JTextField();
		inputJumpKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String keyCode = Integer.toString(e.getKeyCode());
				inputJumpKey.setText(keyCode);
			}
		});

		inputRightKey = new JTextField();
		inputRightKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String keyCode = Integer.toString(e.getKeyCode());
				inputRightKey.setText(keyCode);
			}
		});

		inputLeftKey = new JTextField();
		inputLeftKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String keyCode = Integer.toString(e.getKeyCode());
				inputLeftKey.setText(keyCode);
			}
		});

		inputEscKey = new JTextField();
		inputEscKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String keyCode = Integer.toString(e.getKeyCode());
				inputEscKey.setText(keyCode);
			}
		});

		inputPauseKey = new JTextField();
		inputPauseKey.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				String keyCode = Integer.toString(e.getKeyCode());
				inputPauseKey.setText(keyCode);
			}
		});

		JCheckBox musicCheckbox = new JCheckBox();
		musicCheckbox.setBackground(Color.BLACK);

		JLabel musicaLabel = new JLabel("Musica");
		musicaLabel.setForeground(Color.WHITE);
		musicaLabel.setFont(retroFont);
		JLabel jumpLabel = new JLabel("Saltar");
		jumpLabel.setForeground(Color.WHITE);
		jumpLabel.setFont(retroFont);
		JLabel rightLabel = new JLabel("Derecha");
		rightLabel.setForeground(Color.WHITE);
		rightLabel.setFont(retroFont);
		JLabel leftLabel = new JLabel("Izquierda");
		leftLabel.setForeground(Color.WHITE);
		leftLabel.setFont(retroFont);
		JLabel exitLabel = new JLabel("Salir");
		exitLabel.setForeground(Color.WHITE);
		exitLabel.setFont(retroFont);
		JLabel pauseLabel = new JLabel("Pausa");
		pauseLabel.setForeground(Color.WHITE);
		pauseLabel.setFont(retroFont);

		JButton saveConfig = new JButton("Guardar");
		saveConfig.setBackground(Color.BLACK);
		saveConfig.setBorder(new LineBorder(Color.WHITE));
		saveConfig.setFont(retroFont);
		saveConfig.setForeground(Color.WHITE);

		saveConfig.addActionListener(this);

		mainContainerConfigGrid.add(musicaLabel);
		mainContainerConfigGrid.add(musicCheckbox);
		mainContainerConfigGrid.add(jumpLabel);
		mainContainerConfigGrid.add(inputJumpKey);
		mainContainerConfigGrid.add(rightLabel);
		mainContainerConfigGrid.add(inputRightKey);
		mainContainerConfigGrid.add(leftLabel);
		mainContainerConfigGrid.add(inputLeftKey);
		mainContainerConfigGrid.add(exitLabel);
		mainContainerConfigGrid.add(inputEscKey);
		mainContainerConfigGrid.add(pauseLabel);
		mainContainerConfigGrid.add(inputPauseKey);

		JPanel auxPanelConf5 = new JPanel();
		auxPanelConf5.setBackground(Color.BLACK);

		mainContainerConfigGrid.add(auxPanelConf5);
		mainContainerConfigGrid.add(saveConfig);
		mainContainerConfigGrid.setBackground(Color.BLACK);

		mainContainerConfigInner.add(mainContainerConfigGrid, BorderLayout.CENTER);
		mainContainerConfigInner.setBackground(Color.BLACK);

		JPanel auxPanelConf1 = new JPanel();
		auxPanelConf1.setBackground(Color.BLACK);
		JPanel auxPanelConf2 = new JPanel();
		auxPanelConf2.setBackground(Color.BLACK);
		JPanel auxPanelConf3 = new JPanel();
		auxPanelConf3.setBackground(Color.BLACK);
		JPanel auxPanelConf4 = new JPanel();
		auxPanelConf4.setBackground(Color.BLACK);

		mainContainerConfig.add(auxPanelConf1, BorderLayout.NORTH);
		mainContainerConfig.add(auxPanelConf2, BorderLayout.SOUTH);
		mainContainerConfig.add(auxPanelConf3, BorderLayout.WEST);
		mainContainerConfig.add(auxPanelConf4, BorderLayout.EAST);
		mainContainerConfig.add(mainContainerConfigInner, BorderLayout.CENTER);

		configFrame.add(mainContainerConfig);

//		VENTANA ERROR
		errorFrame = new JFrame("Error");
		errorFrame.setSize(350, 80);
		errorFrame.setLocation(540, 200);

		JPanel errorContainer = new JPanel();
		errorContainer.setLayout(new BorderLayout());

		JPanel auxError1 = new JPanel();
		auxError1.setBackground(Color.RED);
		JPanel auxError2 = new JPanel();
		auxError2.setBackground(Color.RED);
		JPanel auxError3 = new JPanel();
		auxError3.setBackground(Color.RED);
		JPanel auxError4 = new JPanel();
		auxError4.setBackground(Color.RED);

		JLabel errorLabel = new JLabel("Error: debe iniciar sesion");
		errorLabel.setBackground(Color.RED);
		errorLabel.setFont(retroFont);
		errorLabel.setForeground(Color.WHITE);

		errorContainer.add(auxError1, BorderLayout.NORTH);
		errorContainer.add(auxError2, BorderLayout.SOUTH);
		errorContainer.add(auxError3, BorderLayout.WEST);
		errorContainer.add(auxError4, BorderLayout.EAST);
		errorContainer.add(errorLabel, BorderLayout.CENTER);
		errorContainer.setBackground(Color.RED);

		errorFrame.add(errorContainer);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Iniciar sesion")) {
			iniciarSesionFrame.setVisible(true);
		}

		if (e.getActionCommand().equals("Ranking")) {
			Ranking ranking = new Ranking();
			ranking.setVisible(true);
		}

		if (e.getActionCommand().equals("Configuraciones")) {
			configFrame.setVisible(true);
		}

		if (e.getActionCommand().equals("Guardar")) {
			configFrame.setVisible(false);
		}

		if (e.getActionCommand().equals("Ingresar")) {

			if(nombreTextField.getText().isBlank()) {
				jugadorActual.setNombre("invitado");
			} else {
				jugadorActual.setNombre(nombreTextField.getText());
			}
			jugadorActual.setPuntosCharlie(0);

			iniciarSesionFrame.dispose();
		}

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
			if(jugadorActual.getNombre() == null) {
				errorFrame.setVisible(true);
			} else {
				CircusCharlie juego = new CircusCharlie(jugadorActual);

				Thread t = new Thread() {
					public void run() {
						juego.run(1.0 / 60.0);
					}
				};

				t.start();
			}
		}
	}

	public static void main(String[] args) {
		GamePlatform gamesWindow = new GamePlatform();
		gamesWindow.setVisible(true);
	}
}