package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;

    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);

        while (true) {
            System.out.println("Enter you move for " + position.getTurn());
            final Move move = new Move(
                    in.nextInt() - 1,
                    in.nextInt() - 1,
                    position.getTurn()
            );
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
