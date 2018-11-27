package Checkers;

public enum PieceType {
    ROJA(1), AZUL(-1);

    final int moveDir;

    PieceType(int moveDir) {
        this.moveDir = moveDir;
    }
}