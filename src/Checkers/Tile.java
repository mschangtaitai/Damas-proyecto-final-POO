package Checkers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {

    private Piece piece;

    public boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Tile(boolean light, int x, int y) {
        setWidth(CheckersMain.TILE_SIZE);
        setHeight(CheckersMain.TILE_SIZE);

        relocate(x * CheckersMain.TILE_SIZE, y * CheckersMain.TILE_SIZE);

        setFill(light ? Color.valueOf("#FFFFFF") : Color.valueOf("#000000"));
    }
}