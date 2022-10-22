package battleship;

class Ship {
    final private String name;
    final private int length;
    private int[][] coordinates;

    private boolean sunk;

    public Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.coordinates = new int[length][2];
    }

    public boolean isSunk() {
        return sunk;
    }

    public void setSunk(boolean sunk) {
        this.sunk = sunk;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[][] coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

}