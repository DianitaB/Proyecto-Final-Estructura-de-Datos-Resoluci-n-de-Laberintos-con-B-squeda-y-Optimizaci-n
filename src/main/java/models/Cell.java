package models;

import java.util.Objects;

public class Cell {
    public int row;
    public int col;

    public  Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell celda = (Cell) o;
        return row == celda.row && col == celda.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "Celda{" +
                row +
                "," + col +
                '}';
    }
}
