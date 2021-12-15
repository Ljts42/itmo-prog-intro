package expression;

public class Subtract extends BinaryOperation {
    public Subtract(CommonExpression first, CommonExpression second) {
        super(first, second);
    }

    @Override
    protected int calculate(int first, int second) {
        return first - second;
    }
    
    @Override
    protected String getSign() {
        return "-";
    }
}
