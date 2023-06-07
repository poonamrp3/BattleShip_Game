package battleship;
/**
 * An exception class that is a subclass of the
 * BattleShip Exception class. It gives an
 * exception if the cell is already played.
 */

public class CellPlayedException extends BattleshipException{
    public CellPlayedException(int row, int column){
        super(row, column, "battleship.CellPlayedException: This cell has already been hit, row=" + row + ", column=" + column);
    }

}
