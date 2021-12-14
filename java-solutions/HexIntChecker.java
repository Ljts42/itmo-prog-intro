public class HexIntChecker implements CharChecker {
    public boolean check(char c) {
        return Character.isDigit(c) || (c == '-')
                || (97 <= c && c <= 102);
    }
}
