import java.util.Arrays;

public class Board {
    private final int width;
    private final int size;
    private final int cellWidth;
    private final int blankSudoku;

    private Tuple[] tiles;

    public Board(String inputString, int width) {
        this.width = width;
        size = width * width;
        this.tiles = new Tuple[size];
        cellWidth = (int) Math.sqrt(this.width);
        blankSudoku = 0;

        int[] contentsArr = Arrays.stream(inputString.substring(0, size).replaceAll("[^1-9]{1}",
                Integer.toString(blankSudoku)).split("")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < contentsArr.length; i++) {
            this.tiles[i] = new Tuple(contentsArr[i], i, contentsArr[i] != blankSudoku);
        }
    }

    // Copy Constructor
    public Board(Board that) {

        this.width = that.width;
        this.size = that.size;
        this.cellWidth = that.cellWidth;
        this.blankSudoku = that.blankSudoku;

        this.tiles = new Tuple[that.tiles.length];
        for (int i = 0; i < this.tiles.length; i++) {
            this.tiles[i] = new Tuple(that.tiles[i]);
        }
    }

    public Tuple[][] groupRows() {
        Tuple[][] rowsGroup = new Tuple[width][width];
        int groupNum = -1;

        for (int rowsNum = 0; rowsNum < size; rowsNum++) {
            if (rowsNum % 9 == 0) {
                groupNum++;
            }
            rowsGroup[groupNum][rowsNum % 9] = tiles[rowsNum];
        }
        return rowsGroup;
    }

    public Tuple[][] groupColumns() {
        Tuple[][] colsGroup = new Tuple[width][width];
        int groupNum = -1;

        for (int colNums = 0; colNums < size; colNums++) {
            if (colNums % 9 == 0) {
                groupNum++;
            }
            colsGroup[colNums % 9][groupNum] = tiles[colNums];
        }
        return colsGroup;
    }

    public Tuple[][] groupCells() {
        Tuple[][] cellsGroup = new Tuple[width][width];

        int col = 0;
        int row = 0;
        int cellRow = 0;
        int cellCol = 0;

        int valCurrentCell = 0;

        for (Tuple val : tiles) {
            cellsGroup[row][col] = val;
            valCurrentCell++;

            // rows and columns inside a cell
            col++;
            if (col % cellWidth == 0) {
                row++;
                col = cellRow;
            }

            // pointers to the current working cell
            if (valCurrentCell == width) {
                cellRow += cellWidth;
                if (cellRow >= width) {
                    cellCol += cellWidth;
                    cellRow = 0;
                }
                valCurrentCell = 0;
                col = cellRow;
                row = cellCol;
            }
        }

        return cellsGroup;
    }

    public void swap(int index1, int index2) {

        if (index1 == index2) {
            return;
        }

        int temp = tiles[index2].getValue();
        tiles[index2].setValue(tiles[index1].getValue());
        tiles[index1].setValue(temp);

    }

    public boolean isFull() {
        for (Tuple tile : tiles) {
            if (tile.getValue() == blankSudoku) {
                return false;
            }
        }
        return true;
    }

    public int size() {
        return size;
    }

    public int width() {
        return width;
    }

    public int cellWidth() {
        return cellWidth;
    }

    public Tuple get(int index) {
        return tiles[index];
    }

    public void set(int value, int index) {
        if (!tiles[index].isOriginal()) {
            tiles[index].setValue(value);
        }
    }

    public int blank() {
        return blankSudoku;
    }

    public String toString() {
        StringBuilder retStr = new StringBuilder();

        // Build separating line to place between groups of rows
        StringBuilder columnDelimiter = new StringBuilder();

        int dashCount;
        for (int i = 0; i < cellWidth; i++) {
            if (i != 0) columnDelimiter.append("+");


            if (i != 0 && i != cellWidth - 1) {
                dashCount = cellWidth * 2 + 1;
            } else {
                dashCount = cellWidth * 2;
            }

            for (int j = 0; j < dashCount; j++) {
                columnDelimiter.append("-");
            }
        }

        columnDelimiter.append("\n");

        // Build string representation of the Sudoku board
        int row = 0;
        int col = 0;
        for (int index = 0; index < size; index++) {
            retStr.append((tiles[index].getValue() == blankSudoku) ? ". " : tiles[index].getValue() + " ");

            col++;
            if (col % cellWidth == 0 && col != width) {
                retStr.append("| ");
            }

            if (col % width == 0) {
                retStr.append("\n");
                row++;
                col = 0;
                if (row % cellWidth == 0 && row != width) {
                    retStr.append(columnDelimiter);
                }
            }
        }

        return retStr.toString();
    }
}
