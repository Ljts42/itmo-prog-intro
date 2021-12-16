package expression;

public abstract class BinaryOperation implements CommonExpression {
    protected CommonExpression first, second;

    public BinaryOperation(CommonExpression first, CommonExpression second) {
        this.first = first;
        this.second = second;
    }

    protected abstract int calculate(int first, int second);
    protected abstract String getSign();

    @Override
    public final int evaluate(int x) {
        return calculate(
                first.evaluate(x),
                second.evaluate(x)
        );
    }

    @Override
    public final int evaluate(int x, int y, int z) {
        return calculate(
                first.evaluate(x, y, z),
                second.evaluate(x, y, z)
        );
    }

    @Override
    public final String toString() {
        StringBuilder result = new StringBuilder("(");
        result.append(first).append(' ');
        result.append(getSign()).append(' ');
        result.append(second).append(')');
        return result.toString();
    }

    @Override
    public final boolean equals(Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            BinaryOperation binOpObj = (BinaryOperation) obj;
            return binOpObj.first.equals(first) && binOpObj.second.equals(second);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return ((first.hashCode() * 17 + getSign().hashCode()) * 17 + second.hashCode()) * 17;
    }
}
