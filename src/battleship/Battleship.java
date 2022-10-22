package battleship;

import java.io.IOException;
import java.util.Scanner;

public class Battleship {
    private Player player1;
    private Player player2;
    private static final Battlefield fogField = new Battlefield();
    private static final Scanner in = new Scanner(System.in);

    public Battleship() {
        this.player1 = new Player();
        this.player2 = new Player();
        boolean gameFinished;

        setShipPositions(player1);
        promptEnterKey();
        setShipPositions(player2);
        promptEnterKey();

        do {
            gameFinished = shoot(player1, player2);
            promptEnterKey();
            gameFinished = shoot(player2, player1);
            promptEnterKey();
        } while (!gameFinished);
    }

    private static void promptEnterKey() {
        System.out.println("Press Enter and pass the move to another player\n...\n");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean shoot(Player player, Player opponent) {
        boolean allShipsSunk = false;
        boolean validShotCoordinate = false;

        String[][] opponentField = opponent.getBattlefield().getField();
        String shotCoordinate;

        fogField.displayField();
        System.out.println("---------------------");
        player.getBattlefield().displayField();
        System.out.println("\n" + player.getName() + ", it's your turn:");
        do {
            shotCoordinate = in.nextLine();
            if (shotCoordinate.matches("[a-jA-J]10" +
                    "|[a-jA-J][0-9]")) {
                String[] coordinateArr = shotCoordinate.split(" ");

                int[] coordinate = new int[]{
                        (((int) coordinateArr[0].toUpperCase().charAt(0)) - 64),
                        (Integer.parseInt(coordinateArr[0].substring(1))),
                };

                if (opponentField[coordinate[0]][coordinate[1]].equals("~")) {
                    opponentField[coordinate[0]][coordinate[1]] = "M";
                    System.out.println("\nYou missed!");
                } else if (opponentField[coordinate[0]][coordinate[1]].equals("O") ||
                        opponentField[coordinate[0]][coordinate[1]].equals("X")) {
                    opponentField[coordinate[0]][coordinate[1]] = "X";

                    int shipsSunk = 0;
                    for (Ship ship : opponent.getShips()) {
                        if (ship.isSunk()) {
                            shipsSunk++;
                        }
                    }

                    boolean sunkShipWithShot = false;
                    for (Ship ship : opponent.getShips()) {
                        int hits = 0;
                        for (int[] arr : ship.getCoordinates()) {
                            if (arr[0] == coordinate[0] && arr[1] == coordinate[1]) {
                                for (int i = 0; i < ship.getCoordinates().length; i++) {
                                    if (opponentField[ship.getCoordinates()[i][0]]
                                            [ship.getCoordinates()[i][1]].equals("X")) {
                                        hits++;
                                    }
                                }
                            }
                        }

                        if (hits == ship.getLength() && !ship.isSunk()) {
                            shipsSunk++;
                            ship.setSunk(true);
                            if (shipsSunk < opponent.getShips().length) {
                                sunkShipWithShot = true;
                                System.out.println("\nYou sank a ship!\n");
                            } else {
                                allShipsSunk = true;
                                System.out.println("You sank the last ship. You won. Congratulations!");
                                System.exit(0);
                            }
                        }
                    }
                    if (!sunkShipWithShot) {
                        System.out.println("\nYou hit a ship!");
                    }
                }
                validShotCoordinate = true;
            } else {
                System.out.println("Error! You entered the wrong coordinates! Try again:\n");
                System.out.println(shotCoordinate);
            }
        } while (!validShotCoordinate);
        return allShipsSunk;
    }

    private void setShipPositions(Player player) {
        final int FIELD_SIZE = 10;
        final int COORDINATES = 4;
        boolean shipInserted = false;
        boolean correctInput = false;

        System.out.println(player.getName() + ", place your ships on the game field");

        for (Ship ship : player.getShips()) {
            String[][] field = player.getBattlefield().getField();
            String position;
            String[] coordinates;
            int[] pos = new int[COORDINATES];

            System.out.println();
            player.getBattlefield().displayField();
            System.out.print("\nEnter the coordinates of the " + ship.getName() + " (" + ship.getLength() + " cells):\n\n");

            do {
                position = in.nextLine();
                if (position.matches("[a-zA-Z]10+\s+[a-zA-Z]10" +
                        "|[a-zA-Z]10+\s+[a-zA-Z][1-9]" +
                        "|[a-zA-Z][0-9]+\s+[a-zA-Z]10" +
                        "|[a-zA-Z][0-9]+\s+[a-zA-Z][1-9]")) {
                    coordinates = position.split("\s+");

                    pos = new int[]{
                            (((int) coordinates[0].toUpperCase().charAt(0)) - 64),
                            (Integer.parseInt(coordinates[0].substring(1))),
                            (((int) coordinates[1].toUpperCase().charAt(0)) - 64),
                            (Integer.parseInt(coordinates[1].substring(1))),
                    };

                    //Check if the user inverted the coordinates
                    if (pos[0] == pos[2] && pos[1] - pos[3] == ship.getLength() - 1) {
                        int temp = pos[1];
                        pos[1] = pos[3];
                        pos[3] = temp;
                    }

                    if (pos[1] == pos[3] && pos[0] - pos[2] == ship.getLength() - 1) {
                        int temp = pos[0];
                        pos[0] = pos[2];
                        pos[2] = temp;
                    }

                    if (pos[0] == pos[2] && pos[3] - pos[1] != ship.getLength() - 1 ||
                            pos[1] == pos[3] && pos[2] - pos[0] != ship.getLength() - 1) {
                        System.out.println("\nError! Wrong length of the " + ship.getName() + "! Try again:\n");
                        correctInput = false;
                    } else if (pos[0] > FIELD_SIZE || pos[1] > FIELD_SIZE || pos[2] > FIELD_SIZE || pos[3] > FIELD_SIZE) {
                        System.out.println("\nError! Wrong ship location! Try again:\n");
                        correctInput = false;
                    } else if (pos[0] != pos[2] && pos[1] != pos[3]) {
                        System.out.println("\nError! Wrong ship location! Try again:\n");
                        correctInput = false;
                    } else {
                        correctInput = true;
                    }

                    //Check if the ship would be too close to another ship
                    if (pos[0] == pos[2]) {
                        for (int i = 0; i < ship.getLength(); i++) {
                            if (pos[0] == 10 && pos[1] != 10 && pos[1] + i != 10) {
                                if (field[pos[0]][pos[1] + i - 1].equals("O") ||
                                        field[pos[0]][pos[1] + i + 1].equals("O") ||
                                        field[pos[0] - 1][pos[1] + i].equals("O")
                                ) {
                                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                    correctInput = false;
                                }
                            }

                            if (pos[0] != 10 && pos[1] != 10 && pos[1] + i != 10) {
                                if (field[pos[0]][pos[1] + i - 1].equals("O") ||
                                        field[pos[0]][pos[1] + i + 1].equals("O") ||
                                        field[pos[0] - 1][pos[1] + i].equals("O") ||
                                        field[pos[0] + 1][pos[1] + i].equals("O")
                                ) {
                                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                    correctInput = false;
                                }
                            }
                        }
                    }

                    if (pos[1] == pos[3]) {
                        for (int i = 0; i < ship.getLength(); i++) {
                            //Check if the ship would be too close to another ship
                            if (pos[1] == 10 && pos[0] != 0) {
                                if (field[pos[0] + i - 1][pos[1]].equals("O") ||
                                        field[pos[0] + i + 1][pos[1]].equals("O") ||
                                        field[pos[0] + i][pos[1] - 1].equals("O")
                                ) {
                                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                    correctInput = false;
                                }
                            }

                            if (pos[1] != 10 && pos[0] != 10 && pos[1] - 1 >= 0 && pos[1] + 1 <= 10 &&
                                    pos[0] + i + 1 <= 10 && pos[0] + i - 1 >= 0 && pos[0] + i + 1 <= 10) {
                                if (player.getBattlefield().getField()[pos[0] + i - 1][pos[1]].equals("O") ||
                                        field[pos[0] + i + 1][pos[1]].equals("O") ||
                                        field[pos[0] + i][pos[1] - 1].equals("O") ||
                                        field[pos[0] + i][pos[1] + 1].equals("O")
                                ) {
                                    System.out.println("\nError! You placed it too close to another one. Try again:\n");
                                    correctInput = false;
                                }
                            }
                        }
                    }
                } else {
                    System.out.println("\nError! Incorrect coordinates pattern! Try again:\n");
                }
            } while (!correctInput);

            do {
                try {
                    //Ship is horizontal
                    if (pos[0] == pos[2] && pos[3] - pos[1] == ship.getLength() - 1) {
                        for (int j = 0; j < ship.getLength(); j++) {
                            field[pos[0]][pos[1] + j] = "O";
                            int[][] shipCoordinates = ship.getCoordinates();
                            shipCoordinates[j][0] = pos[0];
                            shipCoordinates[j][1] = pos[1] + j;
                            ship.setCoordinates(shipCoordinates);
                        }
                        shipInserted = true;
                    }
                    //Ship is vertical
                    else if (pos[1] == pos[3] && pos[2] - pos[0] == ship.getLength() - 1) {
                        for (int j = 0; j < ship.getLength(); j++) {
                            field[pos[0] + j][pos[1]] = "O";
                            int[][] shipCoordinates = ship.getCoordinates();
                            shipCoordinates[j][0] = pos[0] + j;
                            shipCoordinates[j][1] = pos[1];
                            ship.setCoordinates(shipCoordinates);
                        }
                        shipInserted = true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(e.getMessage());
                    shipInserted = false;
                } catch (Exception e) {
                    System.out.println(e.getMessage() + "\n\n");
                    shipInserted = false;
                }
            } while (!shipInserted);
            player.setBattlefield(field);
        }
        System.out.println();
        player.getBattlefield().displayField();
        System.out.println();
    }

}
