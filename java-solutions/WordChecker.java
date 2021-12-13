public class WordChecker implements CharChecker {
    public boolean check(char c) {
        return Character.isLetter(c) || c == '\''
                || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}
