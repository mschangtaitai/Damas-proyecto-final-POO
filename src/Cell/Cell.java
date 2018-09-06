package Cell;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Cell {
    SimpleIntegerProperty row;
    SimpleIntegerProperty column;
    SimpleBooleanProperty occupied;

    public Cell(int row, int column){
        this.row = new SimpleIntegerProperty();
        this.column = new SimpleIntegerProperty();

        this.row.set(row);
        this.column.set(column);
        //
    }

    public int getRow() {
        return row.get();
    }

    public int getColumn() {
        return column.get();
    }

    public boolean getState(){
        return this.occupied.get();
    }

    public void changeState(){
        if (this.getState() == true){
            this.occupied.set(false);
        }else{
            this.occupied.set(true);
        }
    }

}
