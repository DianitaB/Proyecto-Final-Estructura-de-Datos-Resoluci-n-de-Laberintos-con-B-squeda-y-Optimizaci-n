package models;

import java.util.List;

public class SolveResults {
    private List<Cell> camino;
    private int pasos;
    private long tiempoEjecucion; // en milisegundos

    public SolveResults(List<Cell> camino, int pasos, long tiempoEjecucion) {
        this.camino = camino;
        this.pasos = pasos;
        this.tiempoEjecucion = tiempoEjecucion;
    }

    public List<Cell> getCamino() {
        return camino;
    }

    public int getPasos() {
        return pasos;
    }

    public long getTiempoEjecucion() {
        return tiempoEjecucion;
    }
}
