public class Tuple {

    private int value;
    private int index;
    private boolean og;

    public Tuple(int value, int index, boolean og) {
        this.value = value;
        this.index = index;
        this.og = og;
    }

    public Tuple(Tuple that) {
        this.value = that.value;
        this.index = that.index;
        this.og = that.og;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public boolean isOriginal() {
        return og;
    }
}
