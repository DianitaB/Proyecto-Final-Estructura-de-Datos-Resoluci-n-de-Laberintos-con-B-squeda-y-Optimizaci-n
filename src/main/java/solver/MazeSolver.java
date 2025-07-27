package solver;

import models.Cell;
import models.CellState;
import models.SolveResults;

public interface MazeSolver {
    SolveResults resolver(CellState[][] laberinto, Cell inicio, Cell fin);
}
