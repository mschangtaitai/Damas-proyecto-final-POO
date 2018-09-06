package Piece;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Piece {
    SimpleBooleanProperty color;//True = Rojo, False = Azul
    SimpleIntegerProperty row;
    SimpleIntegerProperty column;
    SimpleBooleanProperty crown;//True= Coronada, False= pieza normal

    public Piece (int row, int column){
        this.row = new SimpleIntegerProperty();
        this.column = new SimpleIntegerProperty();

        this.row.set(row);
        this.column.set(column);
        this.crown = false;
    }

    public void move(int row, int column){
        valid = checkValidMove(row, column);
        if (valid) {
            this.row = row;
            this.column = column;
        }
    }
    public boolean checkValidMove(int row, int column){
        if (!minRowColumn<row<maxRowColumn)||((!minRowColumn<column<maxRowColumn)){
            return false;
        } else{
            return true;
        }
    }

    public boolean getColor(){
        return this.color.get();
    }

    public void crownPiece(){
        this.crown = true;
    }

}
