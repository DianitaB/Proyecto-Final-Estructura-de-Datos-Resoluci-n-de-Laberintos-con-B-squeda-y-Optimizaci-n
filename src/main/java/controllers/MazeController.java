package controllers;

import dao.AlgorithmResultDAO;
import dao.impl.AlgorithmResultDAOFile;
import models.AlgorithmResult;
import models.Cell;
import models.CellState;
import models.SolveResults;
import solver.MazeSolver;
import solver.Impl.*;

import views.MazePanel;
import views.ResultsDialog;

import javax.swing.*;
import java.time.LocalDate;

public class MazeController {

    private final MazePanel mazePanel;
    private final JFrame parent;
    private final AlgorithmResultDAO dao;

    public MazeController(MazePanel mazePanel, JFrame parent) {
        this.mazePanel = mazePanel;
        this.parent = parent;
        this.dao = new AlgorithmResultDAOFile("resultados.csv");
    }

    public void resolverLaberinto(String nombreAlgoritmo) {
        // 1. Obtener laberinto
        CellState[][] matriz = mazePanel.getMatrizEstados();
        Cell inicio = mazePanel.getInicio();
        Cell fin = mazePanel.getFin();

        if (inicio == null || fin == null) {
            JOptionPane.showMessageDialog(parent, "Debes marcar un punto de inicio (🟢) y uno de fin (🔴).");
            return;
        }

        // 2. Seleccionar algoritmo
        MazeSolver solver = switch (nombreAlgoritmo) {
            case "BFS" -> new MazeSolverBFS();
            case "DFS" -> new MazeSolverDFS();
            case "Recursivo" -> new MazeSolverRecursivo();
            case "Recursivo 4D" -> new MazeSolverRecursivoCompleto();
            case "Recursivo 4D + BT" -> new MazeSolverRecursivoCompletoBT();
            default -> null;
        };

        if (solver == null) {
            JOptionPane.showMessageDialog(parent, "Algoritmo no válido.");
            return;
        }

        // 3. Ejecutar y medir tiempo
        long t0 = System.currentTimeMillis();
        SolveResults resultado = solver.resolver(matriz, inicio, fin);
        long t1 = System.currentTimeMillis();
        long tiempoTotal = t1 - t0;

        // 4. Pintar ruta
        mazePanel.pintarSolucion(resultado.getCamino());

        // 5. Mostrar resultado
        new ResultsDialog(parent, resultado.getPasos(), (int) tiempoTotal).setVisible(true);

        // 6. Guardar en archivo
        try {
            dao.guardar(new AlgorithmResult(nombreAlgoritmo, resultado.getPasos(), tiempoTotal, LocalDate.now().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
