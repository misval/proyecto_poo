package poo.games;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Ranking extends JFrame {
    private ArrayList<Jugador> jugadores = new ArrayList<>();

    public Ranking() {
        super("Ranking");
        setSize(600, 400);
        setLocation(380, 200);
        setLayout(new BorderLayout());

        Font retroFont = new Font("Retro Gaming", Font.PLAIN, 19);

        //		bucle for recorriendo las lineas de un archivo y aniadiendo el jugador con sus puntajes
        try {
            // Crear un lector para el archivo de entrada
            BufferedReader lectorJugadores = new BufferedReader(new FileReader("src/main/resources/files/jugadores.txt"));
            lectorJugadores.readLine();

            String linea;
            int countLines = 1;
            Jugador j = new Jugador();
            while ((linea = lectorJugadores.readLine()) != null) {
                if(countLines > 2) {
                    jugadores.add(j);
                    j = new Jugador();
                    countLines = 1;
                }
                if(countLines == 1) {
                    j.setNombre(linea);
                }
                if(countLines == 2) {
                    j.setPuntosCharlie(Integer.parseInt(linea));
                }
                countLines++;
            }

            jugadores.add(j);

            lectorJugadores.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //		VENTANA RANKINGS
        JPanel auxRank1 = new JPanel();
        JPanel auxRank2 = new JPanel();
        JPanel auxRank3 = new JPanel();
        JPanel auxRank4 = new JPanel();
        JPanel mainContainerRanking = new JPanel();
        mainContainerRanking.setLayout(new BorderLayout());

        JLabel rankingTitle = new JLabel("Ranking");
        rankingTitle.setFont(retroFont);
        rankingTitle.setForeground(Color.WHITE);

        // Crear el modelo de la tabla con dos columnas
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("");
        model.addColumn("");
        model.addColumn("");


        jugadores.sort(new Comparator<Jugador>() {
            @Override
            public int compare(Jugador j1, Jugador j2) {
                return Integer.compare(j2.getPuntosCharlie(), j1.getPuntosCharlie());
            }
        });

        int rankI = 1;

        for (Jugador jugador : jugadores) {
            model.addRow(new Object[]{"#" + rankI, jugador.getNombre(), jugador.getPuntosCharlie()});
            rankI++;
        }

        // Crear la tabla con el modelo
        JTable table = new JTable(model);
        table.setFont(retroFont);
        table.setForeground(Color.WHITE);
        table.setRowHeight(30);
        table.setGridColor(Color.BLACK);
        table.setBorder(new LineBorder(Color.BLACK));
        table.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);

        mainContainerRanking.add(rankingTitle, BorderLayout.NORTH);
        mainContainerRanking.add(scrollPane, BorderLayout.CENTER);

        mainContainerRanking.setBackground(Color.BLACK);
        mainContainerRanking.setBorder(new LineBorder(Color.BLACK));
        auxRank1.setBackground(Color.BLACK);
        auxRank2.setBackground(Color.BLACK);
        auxRank3.setBackground(Color.BLACK);
        auxRank4.setBackground(Color.BLACK);

        add(auxRank1, BorderLayout.NORTH);
        add(auxRank2, BorderLayout.SOUTH);
        add(mainContainerRanking, BorderLayout.CENTER);
        add(auxRank3, BorderLayout.EAST);
        add(auxRank4, BorderLayout.WEST);
    }
}
