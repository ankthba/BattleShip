public class Ship {
    private int length;
    private int hits;

    public Ship(int length) {
        this.length = length;
        this.hits = 0;
    }

    public int getLength() {
        return length;
    }

    public void hit() {
        hits++;
    }

    public boolean isSunk() {
        return hits == length;
    }
}
