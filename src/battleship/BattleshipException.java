package battleship;
/**
 * A custom checked exception class to cover all the
 * violations that are happening during the game.
 *
 */

public class BattleshipException extends Exception {

    //public static final int UNSET = -1;
    public final int row;
    public final int column;

    public BattleshipException(int row, int column, String msg){
        super(msg);
        this.row = row;
        this.column = column;
    }

    /*public BattleshipException(String msg){
        super(msg);
        this.row = UNSET;
        this.column = UNSET;
    }*/
}
