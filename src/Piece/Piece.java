package Piece;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Piece {
    SimpleBooleanProperty color;//True = Rojo, False = Azul
    SimpleIntegerProperty row;
    SimpleIntegerProperty column;
    SimpleBooleanProperty crown;//Coronada

    public Piece (int row, int column){
        this.row = new SimpleIntegerProperty();
        this.column = new SimpleIntegerProperty();

        this.row.set(row);
        this.column.set(column);
        //Pendientes otros atributos
    }

    //ToDo public void move(){}
    //ToDo public boolean checkValidMove()

    public boolean getColor(){
        return this.color.get();
    }

}
