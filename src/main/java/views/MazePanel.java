package views;
import javax.swing.*;

import models.Cell;
import models.CellState;

import java.awt.*;

public class MazePanel extends JPanel {

    private final int filas;
    private final int columnas;
    private final JButton[][] celdas;
    private String modo = "WALL";

    public MazePanel(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        setLayout(new GridLayout(filas, columnas, 2, 2));
        setBackground(new Color(220, 220, 220));
        celdas = new JButton[filas][columnas];
        inicializar();
    }

    private void inicializar() {
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            JButton celda = new JButton();
            celda.setBackground(Color.WHITE);
            celda.setFocusPainted(false);
            celda.setOpaque(true); // ✅ permite pintar fondo completamente
            celda.setBorderPainted(false); // ✅ quita bordes que bloquean el fondo
            celda.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            int fi = i, fj = j;
            celda.addActionListener(e -> actualizarCelda(fi, fj));
            celdas[i][j] = celda;
            add(celda);
        }
    }
}


    public void setModo(String modo) {
        this.modo = modo;
    }

    private void actualizarCelda(int i, int j) {
    JButton celda = celdas[i][j];
    switch (modo) {
        case "START":
            limpiarColor(CellState.START);
            celda.setBackground(Color.GREEN);
            break;
        case "END":
            limpiarColor(CellState.END);
            celda.setBackground(Color.RED);
            break;
        case "WALL":
            if (celda.getBackground().equals(Color.BLACK)) {
                celda.setBackground(Color.WHITE); // desactiva muro
            } else {
                celda.setBackground(Color.BLACK); // activa muro
            }
            break;
        }
    }

    private void limpiarColor(CellState tipo) {
    Color color = tipo == CellState.START ? Color.GREEN : Color.RED;
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if (celdas[i][j].getBackground().equals(color)) {
                celdas[i][j].setBackground(Color.WHITE);
            }
        }
    }
}



    public void limpiar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j].setBackground(Color.WHITE);
            }
        }
    }

// Devuelve el estado actual de cada celda del laberinto
public CellState[][] getMatrizEstados() {
    CellState[][] matriz = new CellState[filas][columnas];
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            Color color = celdas[i][j].getBackground();
            if (color.equals(Color.BLACK)) {
                matriz[i][j] = CellState.WALL;
            } else if (color.equals(Color.GREEN)) {
                matriz[i][j] = CellState.START;
            } else if (color.equals(Color.RED)) {
                matriz[i][j] = CellState.END;
            } else {
                matriz[i][j] = CellState.EMPTY;
            }
        }
    }
    return matriz;
}

// Devuelve la celda marcada como inicio (verde)
public Cell getInicio() {
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if (celdas[i][j].getBackground().equals(Color.GREEN)) {
                return new Cell(i, j, CellState.START);
            }
        }
    }
    return null;
}

// Devuelve la celda marcada como fin (rojo)
public Cell getFin() {
    for (int i = 0; i < filas; i++) {
        for (int j = 0; j < columnas; j++) {
            if (celdas[i][j].getBackground().equals(Color.RED)) {
                return new Cell(i, j, CellState.END);
            }
        }
    }
    return null;
}

public void pintarSolucion(java.util.List<Cell> camino) {
    for (Cell celda : camino) {
        int i = celda.getFila();
        int j = celda.getColumna();
        Color actual = celdas[i][j].getBackground();

        // No sobrescribimos inicio ni fin
        if (!actual.equals(Color.GREEN) && !actual.equals(Color.RED)) {
            celdas[i][j].setBackground(Color.BLUE);
        }
    }
}

    public Color getCeldaColor(int i, int j) {
    return celdas[i][j].getBackground();
}

public void setCeldaColor(int i, int j, Color color) {
    celdas[i][j].setBackground(color);
    celdas[i][j].setOpaque(true);
    celdas[i][j].setBorderPainted(false);
}

}

