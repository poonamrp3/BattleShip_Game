package battleship;

/**
 * An exception class that is a subclass of the
 * BattleShip Exception class It gives an
 * exception if the entered rows and columns
 * are not in the given board.
 */

public class OutOfBoundsException extends BattleshipException{
    public OutOfBoundsException(int row, int column){
        super(row, column, "battleship.OutOfBoundsException: Coordinates are past board edge, row=" + row + ", column=" + column);
    }
}
