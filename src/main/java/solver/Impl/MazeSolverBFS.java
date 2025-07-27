package solver.Impl;

import models.Cell;
import models.CellState;
import models.SolveResults;
import solver.MazeSolver;

import java.util.*;

public class MazeSolverBFS implements MazeSolver {

    @Override
    public SolveResults resolver(CellState[][] laberinto, Cell inicio, Cell fin) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        boolean[][] visitado = new boolean[filas][columnas];
        Map<Cell, Cell> padre = new HashMap<>();

        Queue<Cell> cola = new LinkedList<>();
        cola.add(inicio);
        visitado[inicio.getFila()][inicio.getColumna()] = true;

        int[][] direcciones = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        Cell actual = null;

        while (!cola.isEmpty()) {
            actual = cola.poll();
            if (actual.equals(fin)) break;

            for (int[] dir : direcciones) {
                int nuevaFila = actual.getFila() + dir[0];
                int nuevaColumna = actual.getColumna() + dir[1];

                if (nuevaFila >= 0 && nuevaFila < filas && nuevaColumna >= 0 && nuevaColumna < columnas) {
                    if (!visitado[nuevaFila][nuevaColumna] && laberinto[nuevaFila][nuevaColumna] != CellState.WALL) {
                        Cell vecino = new Cell(nuevaFila, nuevaColumna, laberinto[nuevaFila][nuevaColumna]);
                        cola.add(vecino);
                        visitado[nuevaFila][nuevaColumna] = true;
                        padre.put(vecino, actual);
                    }
                }
            }
        }

        List<Cell> camino = new ArrayList<>();
        int pasos = 0;

        if (actual != null && actual.equals(fin)) {
            for (Cell c = fin; c != null; c = padre.get(c)) {
                camino.add(0, c);
                pasos++;
            }
        }

        return new SolveResults(camino, pasos, 0); // tiempo real lo mide el botón
    }
}
