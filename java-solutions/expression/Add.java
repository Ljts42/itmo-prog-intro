package expression;

public class Add extends BinaryOperation {
    public Add(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int first, int second) {
        return first + second;
    }

    @Override
    protected String getSign() {
        return "+";
    }
}
