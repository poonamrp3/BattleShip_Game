package battleship;
/**
 * An exception class that is a subclass of the
 * BattleShip Exception class. It gives an
 * exception if the ships are placed on the
 * same position, i.e. overlapped.
 */

public class OverlapException extends BattleshipException{
    public OverlapException(int row, int column) {
        super(row, column, "battleship.OverlapException: Ships placed in overlapping positions, row=" + row + ", column=" + column);
    }
}
