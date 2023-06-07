package battleship;

/**
 * A class that represents a single ship on the board.
 */

import java.io.Serializable;

/**
 * A single ship in a Battleship game
 */
public class Ship implements Serializable {
    public static final String SUNK_MESSAGE = "A battleship has been sunk!";
    private int hitCount;
    private int length;
    private Board board;
    private Orientation orientation;
    private int row;
    private int col;

    public Ship(Board board, int row, int col, Orientation ort, int length) throws OverlapException, OutOfBoundsException{
        if(row < 0 || row >= board.getHeight() || col < 0 || col >= board.getWidth()){
            throw new OutOfBoundsException(row, col);
        }

        this.length = length;
        this.board = board;
        this.orientation = ort;
        this.row = row;
        this.col = col;
        hitCount = 0;

    }
    public int getRow(){
        return row;
    }

    public int getCol(){
        return col;
    }

    public Orientation getOrientation(){
        return orientation;
    }
    public int getLength(){
        return length;
    }

    public void hit(){
        hitCount++;
    }

    public boolean isSunk(){
        return length == hitCount;
    }
}
