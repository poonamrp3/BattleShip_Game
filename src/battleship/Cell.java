package battleship;

import java.io.Serializable;

/**
 * A class indicating a single spot on the Battleship
 * game board. A cell knows if there is a ship on it,
 * and it remembers if it has been hit.
 */
public class Cell implements Serializable {

    /** Character to display for a ship that has been entirely sunk */
    public static final char SUNK_SHIP_SECTION = '*';

    /** Character to display for a ship that has been hit but not sunk */
    public static final char HIT_SHIP_SECTION = '☐';

    /** Character to display for a water cell that has been hit */
    public static final char HIT_WATER = '.';

    /**
     * Character to display for a water cell that has not been hit.
     * This character is also used for an unhit ship segment.
     */
    public static final char PRISTINE_WATER = '_';

    /**
     * Character to display for a ship section that has not been
     * sunk, when revealing the hidden locations of ships
     */
    public static final char HIDDEN_SHIP_SECTION = 'S';
    private final int column;
    private final int row;
    private boolean hit;
    private Ship ship;
    public boolean IsHiddenReveal = false;

    public Ship getShip(){
        return ship;
    }

    public Cell(int row, int column){
        this.row = row;
        this.column = column;
    }

    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    public char displayChar(){
        if (ship == null) {
            if (hit) {
                return HIT_WATER;
            } else if (IsHiddenReveal) {
                return HIDDEN_SHIP_SECTION;
            } else {
                return PRISTINE_WATER;
            }
        } else {
            if(ship.isSunk()) {
                return SUNK_SHIP_SECTION;
            } else if(hit) {
                return HIT_SHIP_SECTION;
            } else if(IsHiddenReveal) {
                return HIDDEN_SHIP_SECTION;
            } else {
                return PRISTINE_WATER;
            }
        }
    }

    //public char displayHitStatus(){ return ' ';}

    public void hit(){ hit = true; }
    public void putShip(Ship ship){
        this.ship = ship;
    }
    
    public boolean isHit(){
        return hit;
    }
}
