import java.util.ArrayList;
import java.util.Arrays;

public class ConstraintCount {

    public ConstraintCount() {
    }

    public ArrayList<Integer> countAllowableVals(Board board, int index) {
        ArrayList valList = new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));

        int row = index / board.width();
        int col = index % board.width();
        int cell = (row / board.cellWidth()) * board.cellWidth() + (col / board.cellWidth());    // calculate cell number based on row and column


        // Check rows
        for (Tuple rowVal : board.groupRows()[row]) {
            if (rowVal.getValue() != board.blank()) {
                // the value being checked conflicts with another value in the row
                valList.set(rowVal.getValue() - 1, -1);
            }
        }

        // Check columns
        for (Tuple colVal : board.groupColumns()[col]) {

            if (colVal.getValue() != board.blank()) {
                valList.set(colVal.getValue() - 1, -1);
            }
        }

        // Check cells
        for (Tuple cellVal : board.groupCells()[cell]) {
            if (cellVal.getValue() != board.blank()) {
                valList.set(cellVal.getValue() - 1, -1);
            }
        }

        for (int i = valList.size() - 1; i >= 0; i--) {
            if (valList.get(i).equals(-1)) {
                valList.remove(i);
            }
        }
        return valList;
    }

    public int countConstraints(Board board, int index) {
        int constraints = 0;

        Tuple checkVal = board.get(index);

        int row = index / board.width();
        int col = index % board.width();
        int cell = (row / board.cellWidth()) * board.cellWidth() + (col / board.cellWidth());    // calculate cell number based on row and column

        for (Tuple rowVal : board.groupRows()[row]) {
            if (checkVal.getValue() == rowVal.getValue()
                    && checkVal.getIndex() != rowVal.getIndex()) {
                constraints++;
            }
        }

        for (Tuple colVal : board.groupColumns()[col]) {
            if (checkVal.getValue() == colVal.getValue()
                    && checkVal.getIndex() != colVal.getIndex()) {
                constraints++;
            }
        }

        for (Tuple cellVal : board.groupCells()[cell]) {
            if (checkVal.getValue() == cellVal.getValue()
                    && checkVal.getIndex() != cellVal.getIndex()) {
                constraints++;
            }
        }
        return constraints;
    }

    public int countTotalConstraints(Board board) {
        int constraints = 0;

        for (int i = 0; i < board.size(); i++) {
            constraints += countConstraints(board, i);
        }

        return constraints;
    }

}
