package game;

public class MNKGame {
    private final Board board;
    private int alive;
    private final Player[] players;
    private boolean[] lost;

    public MNKGame(Board board, Player[] players) {
        this.board = board;
        this.players = players;
        this.alive = players.length;
        this.lost = new boolean[alive];
    }

    public int play(boolean log) {
        while (alive > 1) {
            for (int i = 0; i < players.length; i++) {
                if (lost[i]) {
                    continue;
                }

                final int result = makeMove(players[i], i + 1, log);
                if (result == -2) {
                    lost[i] = true;
                    if (--alive == 1) {
                        break;
                    }
                    continue;
                }

                if (result != -1) {
                    return result;
                }
            }
        }

        for (int i = 0; i < players.length; i++) {
            if (!lost[i]) {
                return i + 1;
            }
        }
        return 0;
    }

    private int makeMove(Player player, int no, boolean log) {
        final Move move = player.makeMove(board.getPosition());
        final GameResult result = board.makeMove(move);
        if (log) {
            System.out.println();
            System.out.println("Player: " + no);
            System.out.println(move);
            System.out.println(board);
            System.out.println("Result: " + result);
        }
        switch (result) {
            case WIN:
                return no;
            case DRAW:
                return 0;
            case UNKNOWN:
                return -1;
            case LOOSE:
                return -2;
            default:
                throw new AssertionError("Unknown makeMove result " + result);
        }
    }
}
