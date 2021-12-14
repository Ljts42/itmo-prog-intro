public class IntChecker implements CharChecker {
    public boolean check(char c) {
        return Character.isDigit(c) || (c == '-');
    }
}
