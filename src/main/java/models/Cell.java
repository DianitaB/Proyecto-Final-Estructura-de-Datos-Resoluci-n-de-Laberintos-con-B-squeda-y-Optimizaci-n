package models;

public class Cell {
    private int fila;
    private int columna;
    private CellState estado;

    public Cell(int fila, int columna, CellState estado) {
        this.fila = fila;
        this.columna = columna;
        this.estado = estado;
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public CellState getEstado() {
        return estado;
    }

    public void setEstado(CellState estado) {
        this.estado = estado;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell)) return false;
        Cell otra = (Cell) obj;
        return this.fila == otra.fila && this.columna == otra.columna;
    }

    @Override
    public int hashCode() {
        return fila * 31 + columna;
    }
}
