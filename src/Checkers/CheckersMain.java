package Checkers;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.Button;

import java.awt.*;

public class CheckersMain extends Application {//Se utiliza para crear la aplicación en javaFX
        //CONSTANTES PARA LA PANTALLA
        public static final int TILE_SIZE = 80;//TAMAÑO DE LA CASILA
        public static final int WIDTH = 8;
        public static final int HEIGHT = 8;

        private Tile[][] board = new Tile[WIDTH][HEIGHT];//TABLERO

        private Group tileGroup = new Group();
        private Group pieceGroup = new Group();

        private Parent createContent() {
            GridPane root = new GridPane();
            root.setPrefSize(10 * 100, HEIGHT * TILE_SIZE);
            root.getChildren().addAll(tileGroup, pieceGroup);

            Label title = new Label("Damas");
            title.setScaleX(5);
            title.setScaleY(5);
            title.setTranslateX(800);
            title.setTranslateY(-200);
            title.setFont(new Font("Verdana", 10));

            Label redCounter = new Label("Jugador Rojo: ");
            redCounter.setScaleX(5);
            redCounter.setScaleY(5);
            redCounter.setTranslateX(800);
            redCounter.setTranslateY(-100);
            redCounter.setFont(new Font("Verdana", 5));

            Label blueCounter = new Label("Jugador Azul: ");
            blueCounter.setScaleX(5);
            blueCounter.setScaleY(5);
            blueCounter.setTranslateX(800);
            blueCounter.setTranslateY(0);
            blueCounter.setFont(new Font("Verdana", 5));

            Button surrenderButton = new Button();
            surrenderButton.setMinWidth(100);
            surrenderButton.setTranslateX(750);
            surrenderButton.setTranslateY(100);
            surrenderButton.setText("Me rindo!");

            root.getChildren().addAll(title, blueCounter, redCounter, surrenderButton);
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    Tile tile = new Tile((x + y) % 2 == 0, x, y);
                    board[x][y] = tile;

                    tileGroup.getChildren().add(tile);

                    Piece piece = null;

                    if (y <= 2 && (x + y) % 2 != 0) {
                        piece = makePiece(PieceType.RED, x, y);
                    }

                    if (y >= 5 && (x + y) % 2 != 0) {
                        piece = makePiece(PieceType.BLUE, x, y);
                    }

                    if (piece != null) {
                        tile.setPiece(piece);
                        pieceGroup.getChildren().add(piece);
                    }
                }
            }

            return root;
        }

        private MoveResult tryMove(Piece piece, int newX, int newY) {
            if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
                return new MoveResult(MoveType.NONE);
            }//EN CASOS EN LOS QUE LA CASILLA A LA QUE SE DESEA MOVER LA PIEZA CONTENGA YA OTRA PIEZA
             //O QUE TRATE DE PONER DICHA PIEZA EN UNA CASILLA PAR, EL RESULTADO SERÁ NINGUNO, Y POR LO TANTO NO SE EFECTUA MOVIMIENTO ALGUNO.

            int x0 = toBoard(piece.getOldX());//Posición en x inicial
            int y0 = toBoard(piece.getOldY());//Posición en y inicial

            if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {
                return new MoveResult(MoveType.NORMAL);//MOVIMIENTO NORMAL
            } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                }
            }

            return new MoveResult(MoveType.NONE);
        }

        private int toBoard(double pixel) {
            return (int)(pixel + TILE_SIZE / 2) / TILE_SIZE;
        }

        @Override
        public void start(Stage primaryStage) throws Exception {
            Scene scene = new Scene(createContent());
            primaryStage.setTitle("Damas POO, Chan y Estrada");
            primaryStage.setScene(scene);
            primaryStage.show();
        }

        private Piece makePiece(PieceType type, int x, int y) {
            Piece piece = new Piece(type, x, y);

            piece.setOnMouseReleased(e -> {
                int newX = toBoard(piece.getLayoutX());
                int newY = toBoard(piece.getLayoutY());

                MoveResult result;

                if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                    result = new MoveResult(MoveType.NONE);
                } else {
                    result = tryMove(piece, newX, newY);
                }

                int x0 = toBoard(piece.getOldX());
                int y0 = toBoard(piece.getOldY());

                switch (result.getType()) {
                    case NONE:
                        piece.abortMove();
                        break;
                    case NORMAL:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        break;
                    case KILL:
                        piece.move(newX, newY);
                        board[x0][y0].setPiece(null);
                        board[newX][newY].setPiece(piece);
                        Piece otherPiece = result.getPiece();
                        board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                        pieceGroup.getChildren().remove(otherPiece);
                        break;
                }
            });

            return piece;
        }

        public static void main(String[] args) {
            launch(args);
        }
    }

