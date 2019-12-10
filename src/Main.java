public class Main {
    public static void main(String[] args) {
        Board a1 = new Board("..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..", 9);
        ArcCons ac1 = new ArcCons(new Board(a1));
        System.out.println(ac1.getSolution());

        Board h1 = new Board("..3.2.6..9..3.5..1..18.64....81.29..7.......8..67.82....26.95..8..2.3..9..5.1.3..", 9);
        HillClimb hc1 = new HillClimb(new Board(h1));
        System.out.println(hc1.getSolution());

        Board a2 = new Board("4173698.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......", 9);
        ArcCons ac2 = new ArcCons(new Board(a2));
        System.out.println(ac2.getSolution());

        Board h2 = new Board("4173698.5.3..........7......2.....6.....8.4......1.......6.3.7.5..2.....1.4......", 9);
        HillClimb hc2 = new HillClimb(new Board(h2));
        System.out.println(hc2.getSolution());

        Board a3 = new Board("1....7.9..3..2...8..96..5....53..9...1..8...26....4...3......1..4......7..7...3..", 9);
        ArcCons ac3 = new ArcCons(new Board(a3));
        System.out.println(ac3.getSolution());

        Board h3 = new Board("1....7.9..3..2...8..96..5....53..9...1..8...26....4...3......1..4......7..7...3..", 9);
        HillClimb hc3 = new HillClimb(new Board(h3));
        System.out.println(hc3.getSolution());

    }
}
