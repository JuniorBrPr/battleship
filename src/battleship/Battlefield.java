package battleship;

public class Battlefield {
    private String[][] field;

    public Battlefield() {
        this.field = makeField();
    }

    protected void displayField() {
        for (String[] chars : this.field) {
            for (int j = 0; j < chars.length; j++) {
                System.out.print(chars[j] + " ");
                if (j == chars.length - 1) {
                    System.out.print("\n");
                }
            }
        }
    }

    protected static String[][] makeField() {
        final int FIELD_WIDTH = 11;
        final int FIELD_HEIGHT = 11;
        final String FOG_CHAR = "~";
        String[][] field = new String[FIELD_HEIGHT][FIELD_WIDTH];

        for (int i = 0; i < FIELD_HEIGHT; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (i == 0 && j != 0) {
                    field[i][j] = Integer.toString(j);
                } else if (i > 0 && j == 0) {
                    field[i][j] = String.valueOf((char) (i + 64));
                } else if (i > 0) {
                    field[i][j] = FOG_CHAR;
                } else {
                    field[i][j] = " ";
                }
            }
        }
        return field;
    }

    protected String[][] getField() {
        return field;
    }

    protected void setField(String[][] field) {
        this.field = field;
    }
}
