import java.util.ArrayList;

public class ArcCons {
    protected Board board;
    protected ConstraintCount counter;
    protected ArrayList<Integer> allowableList;
    long startTime, endTime, breakTime;
    int movesMade = 0;

    public ArcCons(Board board) {
        this.board = board;
        counter = new ConstraintCount();
    }

    public Board getSolution() {
        System.out.println("-------------------------------");
        System.out.println(board);
        System.out.println("Arc-consistency solver started");
        startTime = System.currentTimeMillis();

        while (!board.isFull()) {
            for (int i = 0; i < board.size(); i++) {
                allowableList = counter.countAllowableVals(board, i);
                if (allowableList.size() == 1) {
                    board.set(allowableList.get(0), i);
                    movesMade++;
                }
            }

            breakTime = System.currentTimeMillis() - startTime;

            if (movesMade >= 100 || breakTime >= 99999) {
                System.out.println("Unsolvable\n");
                System.out.println(board);
                break;
            }
        }


        endTime = System.currentTimeMillis() - startTime;
        System.out.println("\nSolver took " + endTime + " miliseconds");

        return board;
    }
}
