# Battleship
This is a battleship game implemented in Java. The game has two players, each with their own battlefield and ships. The players take turns shooting at the opponent's battlefield, trying to sink all of their ships.

## Classes
### Battleship
This class is a game of Battleship, an interactive two-player game played on a 10x10 battlefield. The game is played with two players, `player1` and `player2`, and each player has a `Battlefield` object with ships placed on it.

#### Properties
* `player1`: an instance of the `Player` class
* `player2`: an instance of the `Player` class
* `fogField`: a `Battlefield` object used to display the game board to the players
* `in`: a `Scanner` object used to take user input

#### Methods

* `promptEnterKey()`: prints a message for the user to press Enter, and then tries to read a character from System.in
* `shoot(Player, Player)`: takes two Players as parameters, and allows the first Player to shoot at the second Player's Battlefield
* `setShipPositions(Player)`: takes a Player as a parameter and allows the user to set the positions of each of the ships on the Player's Battlefield
* `Battleship()`: constructor for the class, which sets up the game board and runs the game

### `Battlefield`
The Battlefield class is a representation of the game board in the Battleship game. It has a field attribute which is a 2D array of strings that stores the state of each cell in the board.

#### Properties
* `field`: a two-dimensional String array that represents the game board. It consists of rows and columns, with the first row and first column being reserved for labeling the rows and columns, respectively. The remaining elements of the array represent the positions on the game board where ships can be placed and shots can be taken.

#### Methods:
* `displayField()`: This method prints the current state of the field attribute to the console. It iterates through each element in the field array and prints it along with a space character. After printing the last element in a row, it moves to the next line.
* `makeField()`: This method creates a new field attribute with the dimensions specified by the FIELD_WIDTH and FIELD_HEIGHT constants. It initializes each cell with the value of the FOG_CHAR constant which represents the cells that have not been revealed by either player. The first column and row are reserved for displaying the column and row labels, respectively.
* `getField()`: This method returns the current value of the field attribute.
* `setField(String[][] field)`: This method sets the value of the field attribute to the given field parameter.

### `Player`
The Player class represents a player in the Battleship game. It has several properties and methods that are used to track the player's progress and actions during the game.

#### Properties: 
* `name`: a `String` representing the name of the player.
* `battlefield`: a Battlefield object representing the player's battlefield, where their ships are placed and shots are taken.
* `ships`: an array of Ship objects representing the player's fleet of ships.

#### Methods
* `Player()`: a constructor for the Player class that creates a new player with a default name ("Player 1" or "Player 2") and a new Battlefield object. It also sets the player's ships using the setShips() method.
* `Player(String name)`: an overloaded constructor for the Player class that creates a new player with the specified name and a new Battlefield object. It also sets the player's ships using the setShips() method.
* `setShips()`: a private method that returns an array of Ship objects representing the player's fleet. This method is called by the Player constructor to initialize the player's ships.
* `getName()`: a public method that returns the name of the player as a string.
* `getBattlefield()`: a public method that returns the player's Battlefield object.
* `setBattlefield(String[][] field)`: a public method that sets the player's Battlefield object to the specified field.
* `getShips()`: a public method that returns the player's array of Ship objects.

### `Ship`
The Ship class represents a ship in the game of Battleship. 

### Properties
* `name`: the name of the ship.
* `length`: the length of the ship.
* `coordinates`: a 2D array containing the coordinates of the ship.
* `sunk`: a boolean indicating whether the ship is sunk or not.

### Methods
* `isSunk()`: returns the boolean value of `sunk`.
* `setSunk(boolean sunk)`: sets the boolean value of `sunk`.
* `getCoordinates()`: returns the coordinates of the ship.
* `setCoordinates(int[][] coordinates)`: sets the coordinates of the ship.
* `getName()`: returns the name of the ship.
* `getLength()`: returns the length of the ship.


