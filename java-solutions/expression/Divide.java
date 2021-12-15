package expression;

public class Divide extends BinaryOperation {
    public Divide(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int first, int second) {
        return first / second;
    }

    @Override
    protected String getSign() {
        return "/";
    }
}
