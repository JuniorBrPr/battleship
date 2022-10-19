
public class Main {
    public static void main(String[] args) {
        Ship aircraftCarrier = new Ship(5);
        Ship battleShip = new Ship(4);
        Ship submarine = new Ship(3);
        Ship cruiser = new Ship(3);
        Ship destroyer = new Ship(2);

        Battlefield battlefield = new Battlefield(aircraftCarrier, battleShip, submarine, cruiser, destroyer);

        battlefield.displayField();

    }
}

class Battlefield{
    private Ship[] ships;
    private String[][] field;

    public Battlefield(Ship... ships) {
        this.ships = ships;
        this.field = makeField();
    }

    private String[][] makeField() {
        final int FIELD_WIDTH = 11;
        final int FIELD_HEIGHT = 11;
        String[][] field = new String[FIELD_HEIGHT][FIELD_WIDTH];

        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (i == 0 && j != 0) {
                    field[i][j] = Integer.toString(j);
                } else if (i > 0 && j == 0) {
                    field[i][j] = String.valueOf((char) (i + 64));
                } else if (i > 0) {
                    field[i][j] = "~";
                } else {
                    field[i][j] = " ";
                }
            }
        }

        return field;
    }

    public void displayField() {
        for (String[] chars : this.field) {
            for (int j = 0; j < chars.length; j++) {
                System.out.print(chars[j] + " ");
                if (j == chars.length - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

}

class Ship {
    private int length;
    private int[] pos1;
    private int[] pos2;

    public Ship(int length) {
        this.length = length;
        this.pos1 = new int[2];
        this.pos2 = new int[2];
    }

    public int getLength() {
        return length;
    }

    public int[] getPos1() {
        return pos1;
    }

    public void setPos1(int[] pos1) {
        this.pos1 = pos1;
    }

    public int[] getPos2() {
        return pos2;
    }

    public void setPos2(int[] pos2) {
        this.pos2 = pos2;
    }
}
