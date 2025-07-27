package solver.Impl;

import models.Cell;
import models.CellState;
import models.SolveResults;
import solver.MazeSolver;

import java.util.ArrayList;
import java.util.List;

public class MazeSolverRecursivoCompletoBT implements MazeSolver {

    private int filas;
    private int columnas;
    private boolean[][] visitado;
    private List<Cell> mejorRuta;

    @Override
    public SolveResults resolver(CellState[][] laberinto, Cell inicio, Cell fin) {
        filas = laberinto.length;
        columnas = laberinto[0].length;
        visitado = new boolean[filas][columnas];
        mejorRuta = new ArrayList<>();

        List<Cell> rutaActual = new ArrayList<>();
        buscar(laberinto, inicio.getFila(), inicio.getColumna(), fin, rutaActual);

        return new SolveResults(mejorRuta, mejorRuta.size(), 0); // el tiempo lo mide el botón
    }

    private void buscar(CellState[][] laberinto, int i, int j, Cell fin, List<Cell> rutaActual) {
        if (i < 0 || i >= filas || j < 0 || j >= columnas) return;
        if (visitado[i][j] || laberinto[i][j] == CellState.WALL) return;

        visitado[i][j] = true;
        rutaActual.add(new Cell(i, j, laberinto[i][j]));

        if (i == fin.getFila() && j == fin.getColumna()) {
            if (mejorRuta.isEmpty() || rutaActual.size() < mejorRuta.size()) {
                mejorRuta = new ArrayList<>(rutaActual); // copiamos la ruta más corta
            }
        } else {
            buscar(laberinto, i - 1, j, fin, rutaActual); // arriba
            buscar(laberinto, i + 1, j, fin, rutaActual); // abajo
            buscar(laberinto, i, j - 1, fin, rutaActual); // izquierda
            buscar(laberinto, i, j + 1, fin, rutaActual); // derecha
        }

        // backtrack
        rutaActual.remove(rutaActual.size() - 1);
        visitado[i][j] = false;
    }
}
