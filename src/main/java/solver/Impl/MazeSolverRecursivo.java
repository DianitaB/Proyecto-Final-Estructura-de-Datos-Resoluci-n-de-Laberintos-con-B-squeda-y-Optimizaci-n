package solver.Impl;

import models.Cell;
import models.CellState;
import models.SolveResults;
import solver.MazeSolver;

import java.util.ArrayList;
import java.util.List;

public class MazeSolverRecursivo implements MazeSolver {

    private int filas;
    private int columnas;
    private boolean[][] visitado;
    private List<Cell> camino;
    private boolean encontrado;

    @Override
    public SolveResults resolver(CellState[][] laberinto, Cell inicio, Cell fin) {
        filas = laberinto.length;
        columnas = laberinto[0].length;
        visitado = new boolean[filas][columnas];
        camino = new ArrayList<>();
        encontrado = false;

        buscar(laberinto, inicio.getFila(), inicio.getColumna(), fin);

        return new SolveResults(camino, camino.size(), 0); // tiempo se mide fuera
    }

    private void buscar(CellState[][] laberinto, int i, int j, Cell fin) {
        if (i < 0 || i >= filas || j < 0 || j >= columnas || visitado[i][j] || laberinto[i][j] == CellState.WALL || encontrado) {
            return;
        }

        visitado[i][j] = true;
        camino.add(new Cell(i, j, laberinto[i][j]));

        if (i == fin.getFila() && j == fin.getColumna()) {
            encontrado = true;
            return;
        }

        // Solo dos direcciones: derecha y abajo
        buscar(laberinto, i, j + 1, fin); // derecha
        buscar(laberinto, i + 1, j, fin); // abajo

        if (!encontrado) {
            camino.remove(camino.size() - 1); // backtrack visual si no lleva a destino
        }
    }
}
