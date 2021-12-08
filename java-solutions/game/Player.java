package game;

public interface Player {
    // :NOTE: move может бросить непроверяемое исключение, и игра упадет
    Move makeMove(Position position);
}
