package views;
import javax.swing.*;
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
                celda.setBackground(Color.GREEN);
                break;
            case "END":
                celda.setBackground(Color.RED);
                break;
            case "WALL":
                celda.setBackground(celda.getBackground().equals(Color.BLACK) ? Color.WHITE : Color.BLACK);
                break;
        }
    }

    public void limpiar() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                celdas[i][j].setBackground(Color.WHITE);
            }
        }
    }
}
