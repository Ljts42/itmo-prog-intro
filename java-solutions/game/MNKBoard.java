package game;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class MNKBoard implements Board, Position {
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0",
            Cell.G, "-",
            Cell.V, "|"
    );

    private final int m;
    private final int n;
    private final int k;
    private final int p;
    private int moveCount = 0;

    private Cell[][] field;
    private Cell turn = Cell.X;

    private Map<Cell, PairCells> NEXT_TURN = new HashMap<>();


    public MNKBoard(int m, int n, int k, int p) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.p = p;
        field = new Cell[m][n];
        for (Cell[] row: field) {
            Arrays.fill(row, Cell.E);
        }

        if (p == 2) {
            NEXT_TURN.put(Cell.X, new PairCells(Cell.O, Cell.O));
            NEXT_TURN.put(Cell.O, new PairCells(Cell.X, Cell.X));
        } else if (p == 3) {
            NEXT_TURN.put(Cell.X, new PairCells(Cell.G, Cell.O));
            NEXT_TURN.put(Cell.O, new PairCells(Cell.X, Cell.G));
            NEXT_TURN.put(Cell.G, new PairCells(Cell.O, Cell.X));
        } else {
            NEXT_TURN.put(Cell.X, new PairCells(Cell.V, Cell.O));
            NEXT_TURN.put(Cell.O, new PairCells(Cell.X, Cell.G));
            NEXT_TURN.put(Cell.G, new PairCells(Cell.O, Cell.V));
            NEXT_TURN.put(Cell.V, new PairCells(Cell.G, Cell.X));
        }
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            Cell prev = NEXT_TURN.get(turn).getPrev();
            Cell next = NEXT_TURN.get(turn).getNext();
            NEXT_TURN.put(
                prev,
                new PairCells(
                    NEXT_TURN.get(prev).getPrev(),
                    next
                )
            );
            NEXT_TURN.put(
                next,
                new PairCells(
                    prev,
                    NEXT_TURN.get(next).getNext()
                )
            );
            turn = next;
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(move.getRow(), move.getCol())) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = NEXT_TURN.get(turn).getNext();

        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        return ++moveCount == m * n;
    }

    private int countCells(int row, int col, int rPlus, int cPlus) {
        int count = 0;
        int i = row + rPlus;
        int j = col + cPlus;
        while (i >= 0 && i < m && j >= 0 && j < n) {
            if (field[i][j] != turn) {
                break;
            }
            i += rPlus;
            j += cPlus;
            count++;
        }
        return count;
    }

    private boolean checkWin(int row, int col) {
        return 1 + countCells(row, col, 1, 1) + countCells(row, col, -1, -1) == k
                || 1 + countCells(row, col, -1, 1) + countCells(row, col, 1, -1) == k
                || 1 + countCells(row, col, 0, 1) + countCells(row, col, 0, -1) == k
                || 1 + countCells(row, col, 1, 0) + countCells(row, col, -1, 0) == k;
    }

    public boolean isValid(final Move move) {
        return move != null
                && 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getCol() && move.getCol() < n
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int col) {
        return field[row][col];
    }

    @Override
    public int getM() {
        return m;
    }

    @Override
    public int getN() {
        return n;
    }

    @Override
    public int getK() {
        return k;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(" ".repeat(String.valueOf(m).length()));
        for (int c = 1; c <= n; c++) {
            sb.append(String.format("%" + (String.valueOf(n).length() + 1) + "d", c));
        }
        sb.append(System.lineSeparator());

        for (int r = 1; r <= m; r++) {
            sb.append(String.format("%" + String.valueOf(m).length() + "d", r));

            for (Cell c: field[r - 1]) {
                sb.append(
                        String.format("%" + (String.valueOf(n).length() + 1) + "s",
                        CELL_TO_STRING.get(c))
                );
            }
            sb.append(System.lineSeparator());
        }

        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
