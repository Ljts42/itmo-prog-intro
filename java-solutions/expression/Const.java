package expression;

public class Const implements CommonExpression {
    private final int constant;

    public Const(int constant) {
        this.constant = constant;
    }

    @Override
    public int evaluate(int x) {
        return constant;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return constant;
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Const) {
            return ((Const) obj).constant == constant;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return String.valueOf(constant).hashCode();
    }
}
