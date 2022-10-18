public class Main {
    public static void main(String[] args) {
//        Ship aircraftCarrier = new Ship(5);
//        Ship battleShip = new Ship(4);
//        Ship submarine = new Ship(3);
//        Ship cruiser = new Ship(3);
//        Ship destroyer = new Ship(2);
        displayField(makeField());


    }

//    private char[][] adjust(Ship aircraftCarrier,Ship battleShip, Ship submarine,Ship cruiser, Ship destroyer){
//
//    }

    public static void displayField(String[][] field) {
        for (String[] chars : field) {
            for (int j = 0; j < chars.length; j++) {
                System.out.print(chars[j] + " ");
                if (j == chars.length - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    public static String[][] makeField() {
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
}

class Ship {
    private final int length;

    public Ship(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}
