package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2 || args.length > 4) {
            System.out.println("The number of players cannot be less than 2 more than 4");
            return;
        }

        Scanner in = new Scanner(System.in);

        Player[] players = new Player[args.length];
        for (int p = 0; p < args.length; p++) {
            switch (args[p].toLowerCase().charAt(0)) {
                case 'c':
                    players[p] = new CheatingPlayer();
                    break;
                case 'h':
                    players[p] = new HumanPlayer(in);
                    break;
                case 'n':
                    players[p] = new NullPlayer();
                    break;
                case 'r':
                    players[p] = new RandomPlayer();
                    break;
                case 's':
                    players[p] = new SequentialPlayer();
                    break;
                default:
                    break;
            }
        }

        System.out.print("M N K: ");
        int m = in.nextInt(), n = in.nextInt(), k = in.nextInt();

        final int result = new MNKGame(
                new MNKBoard(m, n, k, players.length),
                players
        ).play(true);

        switch (result) {
            case 1:
                System.out.println("First player won");
                break;
            case 2:
                System.out.println("Second player won");
                break;
            case 3:
                System.out.println("Third player won");
                break;
            case 4:
                System.out.println("Fourth player won");
                break;
            case 0:
                System.out.println("Draw");
                break;
            default:
                throw new AssertionError("Unknown result " + result);
        }
        in.close();
    }
}
