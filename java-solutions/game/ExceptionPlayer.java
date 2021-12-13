package game;

public class ExceptionPlayer implements Player {
    @Override
    public Move makeMove(Position position) {
         new Exception("Exception");
    }
}
