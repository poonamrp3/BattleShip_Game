package battleship;
/**
 * A class that sets the board on which the Battleship
 * game is to be played. It adds the ship and also
 * throws exceptions as per the violations.
 */
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Board implements Serializable{

    private Cell[][] cells;
    private List<Ship> ships;
    private int height;
    private int width;
    public boolean IsHiddenReveal = false;
    //public static final long SerialVersionUID = 1234L;

    public Board(int height, int width){
        this.height = height;
        this.width = width;

        ships = new ArrayList<>();
        cells = new Cell[height][width];

        for(int i = 0; i < height; i++ ){
            for(int j = 0; j < width; j++){
                cells[i][j] = new Cell(i,j);
                cells[i][j].IsHiddenReveal = IsHiddenReveal;
            }
        }
    }

    public void addShip(Ship ship) throws OverlapException{
        ships.add(ship);

        int row = ship.getRow();
        int column = ship.getCol();
        Orientation ort = ship.getOrientation();

        for(int l = 0; l < ship.getLength(); l++){
            if(cells[row][column].getShip() != null){
                throw new OverlapException(row, column);
            } else{
                cells[row][column].putShip(ship);
                row = row + ort.rDelta;
                column = column + ort.cDelta;
            }            
        }
    }

    public boolean allSunk(){
        for(Ship ship: ships) {
            if (!ship.isSunk()) {
                return false;
            }
        }
        return true;
    }

    public void display(PrintStream out){
        out.println(toString());
    }

    //public void fullDisplay(PrintStream out){}

    public Cell getCell(int row, int column) throws OutOfBoundsException{
        if(row < 0 || row >= height || column < 0 || column >= width){
            throw new OutOfBoundsException(row, column);
        }
        for (Cell[] cellsIter : cells) {
            for(Cell cell : cellsIter){
                if(cell.getRow() == row && cell.getColumn() == column){
                    return cell;
                }
            }
        }
        return null;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public String toString(){
        String boardString = " ";    
        for(int i = 0; i < height; i++){
            boardString += " " + i;
        }
        boardString += "\n";
        for(int i = 0; i< width; i++){
            boardString += i;
            for(int j = 0; j < height; j++){
                if(cells[i][j].getShip() != null) {
                    if(!cells[i][j].getShip().isSunk() && IsHiddenReveal){
                        cells[i][j].IsHiddenReveal = IsHiddenReveal;
                    }
                }
                    boardString += " " + cells[i][j].displayChar();
                    cells[i][j].IsHiddenReveal = false;
                }
                boardString += "\n";
            }
        return boardString;
    }

    public Boolean attackShip(int row, int column) throws CellPlayedException, OutOfBoundsException{
        Cell cell = getCell(row, column);
        Ship ship = cell.getShip();
        if(cell != null){
            if(row < 0 || row >= height || column < 0 || column >= width){
                throw new OutOfBoundsException(row, column);
            }
            if(cell.isHit()){
                throw new CellPlayedException(row, column);
            }
            cell.hit();
            if(ship != null){
                ship.hit();
            }
        }
        return ship != null ? ship.isSunk() : false;
    }
}
