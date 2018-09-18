package Board;
import Cell.Cell;
import java.util.ArrayList;
import Piece.Piece;
public class Board {
    private int maxRowColumn = 8
    private int minRowColumn = 0
    private ArrayList<Cell> cells;
    private ArrayList<Piece> pieces;

    public void executeMove(Piece piece){
        px = piece.getRow();
        py = piece.getColumn();

    }
}