import java.util.Random;

public class HillClimb {

    protected Board board;
    protected ConstraintCount counter;
    long startTime, endTime;

    public HillClimb(Board board) {
        this.board = board;
        counter = new ConstraintCount();
    }

    public Board getSolution() {
        System.out.println("-------------------------------\n");
        System.out.println(board);
        System.out.println("\nHill Climbing solver started");
        startTime = System.currentTimeMillis();

        randomFill();
        Tuple[][] cells = board.groupCells();

        int thresholdMax = 9;
        int thresholdCurrent = 0;
        int[] boardIndices;
        int conflicts;

        while (conflicts() > 0) {

            thresholdCurrent = 0;

            for (Tuple[] cellGroup : cells) {

                conflicts = conflicts();
                boardIndices = findSwap(cellGroup);
                board.swap(boardIndices[0], boardIndices[1]);

                thresholdCurrent = conflicts() >= conflicts ? (thresholdCurrent + 1) : 0;
            }

            if (thresholdCurrent >= thresholdMax) {
                System.out.println(("caught in local minimum, no solution found"));
                return board;
            }

        }

        endTime = System.currentTimeMillis() - startTime;
        System.out.println("\nSolver took " + endTime + "miliseconds");

        return board;
    }


    private int[] findSwap(Tuple[] cellGroup) {
        int[] indices = new int[2];
        int lowestConflicts = 9999;
        int currentConflicts;

        Board boardCopy = new Board(board);

        for (int i = 0; i < cellGroup.length; i++) {
            for (int j = 0; j < cellGroup.length; j++) {

                if (i == j || boardCopy.get(i).isOriginal() || boardCopy.get(j).isOriginal()) {
                    continue;
                } else {
                    boardCopy = new Board(board);
                    boardCopy.swap(cellGroup[i].getIndex(), cellGroup[j].getIndex());

                    lowestConflicts = (currentConflicts = counter.countTotalConstraints(boardCopy)) < lowestConflicts ? currentConflicts : lowestConflicts;

                    if (lowestConflicts == currentConflicts) {
                        indices[0] = cellGroup[i].getIndex();
                        indices[1] = cellGroup[j].getIndex();
                        return indices;
                    }
                }
            }
        }

        return indices;
    }

    protected void randomFill() {

        Random rand = new Random();
        boolean[] numsExisted;
        int randVal;

        Tuple[][] cellGroup = board.groupCells();

        for (Tuple[] cell : cellGroup) {
            //checking array for numbers existed
            numsExisted = new boolean[board.width()];
            for (Tuple originalVal : cell) {

                if (originalVal.getValue() != board.blank()) {
                    numsExisted[originalVal.getValue() - 1] = true;
                }
            }

            for (Tuple cellVal : cell) {
                if (!cellVal.isOriginal()) {
                    randVal = rand.nextInt(board.width()) + 1;

                    while (numsExisted[randVal - 1]) {
                        randVal = rand.nextInt(board.width()) + 1;
                    }

                    board.set(randVal, cellVal.getIndex());
                    numsExisted[randVal - 1] = true;
                }
            }
        }
    }

    public Board getBoard() {
        return board;
    }

    public int conflicts() {
        return counter.countTotalConstraints(board);
    }

}
