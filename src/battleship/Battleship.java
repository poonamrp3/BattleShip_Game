package battleship;

/**
 * This is the main class that reads the file name from command line.
 * If provided bin file then the board will be created as the status
 * provided in that file, else if provided file is a .txt file then
 * new Board will be created for the Battleship game.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Battleship {
    public static final String ALL_SHIPS_SUNK = "All ships sunk!";
    public static final String BAD_ARG_COUNT = "Wrong number of arguments for command";
    public static final String DIM_TOO_BIG = "Board dimensions too big to display";
    public static final String HIT = "h";
    public static final int MAX_DIM = 20;
    public static final String PROMPT = "> ";
    public static final String QUIT = "q";
    public static final String REVEAL = "!";
    public static final String SAVE = "s";
    public static final String WHITESPACE = "\\s+";
    public static final String HELP = "help";
    public Map<String, String> gameCommands = new HashMap<String,String>();
    private Board board;

    public Battleship(String filename) throws BattleshipException, IOException {
        System.out.println("Welcome to Battleship!");
        gameCommands.put(HIT + " row column ", "Hit a cell.");
        gameCommands.put(SAVE + " file", "Save game state to file.");
        gameCommands.put(REVEAL, "Reveal all ship locations.");
        gameCommands.put(QUIT, "Quit game.");

        String fileExtension = filename.trim().substring(filename.length() - 3);
        StringBuilder strMsg = new StringBuilder();
        strMsg.append("Checking if " + filename + " is a saved game file... ");
    
        if(fileExtension.equals("bin")) {
            strMsg.append("yes");
            System.out.println(strMsg);
            try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("output/"+filename));) {
                board = (Board) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }

        } else {
            strMsg.append("no; will read as a text setup file.");
            System.out.println(strMsg);
            try (BufferedReader reader = new BufferedReader(new FileReader("input/"+filename))) {
                String boardSize = reader.readLine();
                String[] splited = boardSize.split(" ");
                int height = Integer.parseInt(splited[0]);
                int width = Integer.parseInt(splited[1]);

                if (height >= MAX_DIM || width >= MAX_DIM) {
                   System.out.println(DIM_TOO_BIG);
                   System.exit(0);
                } else {
                    this.board = new Board(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] shipInfo = line.split(" ");
                        Ship newShip = new Ship(board, Integer.parseInt(shipInfo[0]), Integer.parseInt(shipInfo[1]), Orientation.valueOf(shipInfo[2]), Integer.parseInt(shipInfo[3]));
                        board.addShip(newShip);
                    }
                }
            } catch (IOException | OverlapException | OutOfBoundsException e) {
               System.out.println(e.getMessage());
               System.exit(0);
            }
        }
    }

    public void play() throws IOException
    {
        drawBoard(false);
        takeInput(); 
    }
    
    public void takeInput() throws IOException{
        System.out.print(PROMPT);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] inputArray = reader.readLine().split(WHITESPACE);
        boolean isShipSunk = false;

        if (inputArray[0].equals(HIT)) {
            if (inputArray.length != 3) {
                System.out.println(BAD_ARG_COUNT + " :" + HIT);
                checkAllShips();
            } else {
                int row = Integer.parseInt(inputArray[1]);
                int col = Integer.parseInt(inputArray[2]);
                try{
                    Cell cell = board.getCell(row, col);
                    if (cell != null) {
                        try {
                            isShipSunk = board.attackShip(row, col);
                            if (isShipSunk) {
                                System.out.println(Ship.SUNK_MESSAGE);
                            }             
                            drawBoard(false);
                            checkAllShips();
                        } catch(CellPlayedException e){
                            System.out.println(e.getMessage());
                        }                     
                    } else {
                        System.out.println(BAD_ARG_COUNT + " :" + HIT);
                    }                                  
                } catch(OutOfBoundsException e){
                    System.out.println(e.getMessage());
                }
                checkAllShips();
            }

        } else if (inputArray[0].equals(REVEAL)) {
            drawBoard(true);
            checkAllShips();
            board.IsHiddenReveal = false;

        } else if (inputArray[0].equals(QUIT)) {
            System.exit(0);

        } else if(inputArray[0].equals(SAVE)){
            if (inputArray.length != 2){
                System.out.println(BAD_ARG_COUNT + " :" + SAVE);
                checkAllShips();
                
            } else {        
                try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("output/"+inputArray[1]))){
                    output.writeObject(board);
                    checkAllShips();
                }   catch(IOException e) {
                    System.out.println(e.getMessage());
                }              
            }

        } else if(inputArray[0].equals(HELP)){
            for (Map.Entry<String, String> entry : gameCommands.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            checkAllShips();
        }      
        else {
            System.out.println(BAD_ARG_COUNT);
            checkAllShips();
        } 
    }

    public void drawBoard(boolean isReveal){
        if(board != null){
            System.out.println();
            board.IsHiddenReveal = isReveal;
            board.display(System.out);
        }
    }

    public void checkAllShips() throws IOException{
        if(board.allSunk()){
            System.out.println(ALL_SHIPS_SUNK);
            System.exit(0);

        } else {
            takeInput();
        }
    }
    
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.println("Usage: java Battleship setup-file\n");
            System.exit(0);

        } else {
            try {
                Battleship game = new Battleship(args[1]);
                game.play();
            } catch (IOException | BattleshipException e) {
                System.out.println(e.getMessage());
                System.exit(0);
            }  
        }
    }
}
