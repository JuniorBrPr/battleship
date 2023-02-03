package battleship;

class Player {
    private final String name;
    private static int players = 0;
    private final Battlefield battlefield;
    private final Ship[] ships;

    public Player() {
        ++players;
        this.name = "Player " + players;
        this.battlefield = new Battlefield();
        this.ships = setShips();
    }

    public Player(String name) {
        this.name = name;
        this.battlefield = new Battlefield();
        this.ships = setShips();
    }

    private static Ship[] setShips() {
        Ship aircraftCarrier = new Ship("Aircraft Carrier", 5);
        Ship battleShip = new Ship("Battleship", 4);
        Ship submarine = new Ship("Submarine", 3);
        Ship cruiser = new Ship("Cruiser", 3);
        Ship destroyer = new Ship("Destroyer", 2);

        return new Ship[]{
                aircraftCarrier,
                battleShip,
                submarine,
                cruiser,
                destroyer
        };
    }

    public String getName() {
        return name;
    }

    public Battlefield getBattlefield() {
        return battlefield;
    }

    public void setBattlefield(char[][] field) {
        this.battlefield.setField(field);
    }

    public Ship[] getShips() {
        return ships;
    }
}
