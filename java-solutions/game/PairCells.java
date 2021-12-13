package game;

public class PairCells {
    private Cell prev;
    private Cell next;

    public PairCells(Cell prev, Cell next) {
        this.prev = prev;
        this.next = next;
    }

    public Cell getPrev() {
        return prev;
    }

    public Cell getNext() {
        return next;
    }
}