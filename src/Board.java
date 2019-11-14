import java.util.*;

public class Board {
    private final int[][] tiles;
    public int size = 9;

/*
    public Board(int[][] inputBoard) {
        this.tiles = new int[size][size];

        for (int i = 0; i < size; i++)
            System.arraycopy(inputBoard[i], 0, this.tiles[i], 0, size);
    }
*/

    public Board(String inputString) {
        this.tiles = new int[size][size];
        List<String> tokens = new ArrayList<>();
        tokens.addAll(Arrays.asList(inputString.split("")));
        Collections.replaceAll(tokens, ".", "0");

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                if (tokens.size() > 0) {
                    String token = tokens.get(0);
                    this.tiles[i][j] = Integer.parseInt(token);
                    tokens.remove(0);
                }
    }

    public String toString () {
        String result = "";
        for (int i = 0; i < this.tiles.length; i++) {
            if (i % 3 == 0) {
                result = result + "+---------+---------+---------+\n";
            }
            for (int j = 0; j < this.tiles.length; j++) {
                //spacing
                if (j % 3 == 0) {
                    result = result + "|";
                }

                if (tiles[i][j] == 0) {
                    result = result + " . ";
                } else {
                    result = result + " " + this.tiles[i][j] + " ";
                }
            }
            result = result + "|\n";
        }
        result = result + "+---------+---------+---------+\n";
        return result;
    }

}
