package solver.Impl;

import models.Cell;
import models.CellState;
import models.SolveResults;
import solver.MazeSolver;

import java.util.*;

public class MazeSolverDFS implements MazeSolver {

    private boolean[][] visitado;
    private Map<Cell, Cell> padre;
    private int filas;
    private int columnas;

    @Override
    public SolveResults resolver(CellState[][] laberinto, Cell inicio, Cell fin) {
        filas = laberinto.length;
        columnas = laberinto[0].length;
        visitado = new boolean[filas][columnas];
        padre = new HashMap<>();

        dfs(laberinto, inicio, fin);

        List<Cell> camino = new ArrayList<>();
        int pasos = 0;

        if (padre.containsKey(fin) || inicio.equals(fin)) {
            for (Cell c = fin; c != null; c = padre.get(c)) {
                camino.add(0, c);
                pasos++;
            }
        }

        return new SolveResults(camino, pasos, 0); // el tiempo lo mide el botón
    }

    private boolean dfs(CellState[][] laberinto, Cell actual, Cell fin) {
        int i = actual.getFila();
        int j = actual.getColumna();

        if (i < 0 || i >= filas || j < 0 || j >= columnas) return false;
        if (visitado[i][j] || laberinto[i][j] == CellState.WALL) return false;

        visitado[i][j] = true;

        if (actual.equals(fin)) return true;

        int[][] direcciones = {{-1,0},{1,0},{0,-1},{0,1}};
        for (int[] dir : direcciones) {
            int ni = i + dir[0];
            int nj = j + dir[1];

            if (ni >= 0 && ni < filas && nj >= 0 && nj < columnas) {
                Cell siguiente = new Cell(ni, nj, laberinto[ni][nj]);
                if (!visitado[ni][nj] && laberinto[ni][nj] != CellState.WALL) {
                    padre.put(siguiente, actual);
                    if (dfs(laberinto, siguiente, fin)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
