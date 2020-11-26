package Model.exp;

import Model.CustomExceptions.ExpressionException;
import Model.adts.IDict;
import Model.adts.IHeap;
import Model.vals.BoolValue;
import Model.vals.IntValue;
import Model.vals.Value;

public class RelationalExp extends Expression {
    final String op;
    final Expression expressionLeft;
    final Expression expressionRight;
    public RelationalExp(String op, Expression expression, Expression expressionRight) {
        this.op = op;
        this.expressionLeft = expression;
        this.expressionRight = expressionRight;
    }

    @Override
    public Value evaluate(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) {
        IntValue valueL = (IntValue) expressionLeft.evaluate(symTable, heapTable);
        IntValue valueR = (IntValue) expressionRight.evaluate(symTable, heapTable);
        return switch (op) {
            case ">" -> new BoolValue(valueL.getValue() > valueR.getValue());
            case "<" -> new BoolValue(valueL.getValue() < valueR.getValue());
            case ">=" -> new BoolValue(valueL.getValue() >= valueR.getValue());
            case "<=" -> new BoolValue(valueL.getValue() <= valueR.getValue());
            case "==" -> new BoolValue(valueL.getValue() == valueR.getValue());
            case "!=" -> new BoolValue(valueL.getValue() != valueR.getValue());
            default -> throw new ExpressionException("Operator is invalid!");
        };
    }

    @Override
    public String toString() {
        return this.expressionLeft +" "+ this.op +" "+ this.expressionRight;
    }
}
