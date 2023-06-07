# BattleShip_Game
Battleship is a war-themed board game for two players in which the opponents try to guess the location of their opponent's warships and 
sink them. 
Also check the link for more details -> https://www.thesprucecrafts.com/the-basic-rules-of-battleship-411069

File Structure of the Game -
1. src folder has three main folders - 
a) BattleShip - This folder has necessary code of the game.
b) Input - Exemplary Input files for the game which has battleship positions.
c) Output - Exemplary Output files for the game 
d) If the user is using IntelliJ then they need to do the configurations for file and file path before '/input' needs to be given.

How to Play?
1. It is an interactive game where the user needs to give the input -> FileName BattleShip_positions_filename. For example,
for this the file name will be - 'BattleShip' followed by the 'Filename where Battleship_postions' are stored. ->>> BattleShip 4-4-0-0-2.txt
2. If the file doesn't exist then the usage message will be displayed
3. Also if the user has provided file with .bin extension then the current version of the game will be retrieved from it 
and the game will be resumed from that stage.
5. If the two consecutive turns of the player have sunk the ship then '*' will be shown and the respective message will be thrown.
6. For any user turn square will be shown for the position given by the user.
7. If the position provided by the user doesn't have any battleShip then '.' will be shown.
8. For more understanding of the game, please go through the output folder files.
9. There are user commands in the game such as >>
• help - Display a list of all commands available.
• h row column - Hit a cell.
• s file - Save game state to file. (Serialization process)
• ! - Reveal all ship locations.
• q - Quit game.


Input File Structure Points -> 
Each additional line describes a ship placement, in four parts:
1. the number of the uppermost row on which the ship sits.
2. the number of the leftmost column on which the ship sits.
3. whether the ship is HORIZONTAL (spans columns) or VERTICAL (spans rows).
4. the length of the ship.


Explanation of Files -> 
• BattleshipException: A custom checked exception for representing different
violations of the game’s rules during runtime.
• Orientation: An enum representing the different orientations (horizontal or vertical)
of a ship on the board.
• Cell: A class representing a cell on the board.
• Ship: A class representing a single ship.


