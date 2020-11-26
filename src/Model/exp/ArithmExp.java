package Model.exp;

import Model.CustomExceptions.ExpressionException;
import Model.adts.IDict;
import Model.adts.IHeap;
import Model.vals.IntValue;
import Model.vals.Value;

public class ArithmExp extends Expression{
    Expression right;
    Expression left;
    char op;

    public ArithmExp(char op, Expression right, Expression left) {
        this.right = right;
        this.left = left;
        this.op = op;
    }

    @Override
    public Value evaluate(IDict<String, Value> symTable, IHeap<Integer, Value> heapTable) {
        IntValue rightValue = (IntValue) this.right.evaluate(symTable, heapTable);
        IntValue leftValue = (IntValue) this.left.evaluate(symTable, heapTable);
        switch (op) {
            case '+' : return new IntValue(rightValue.getValue() + leftValue.getValue());
            case '-' : return new IntValue(rightValue.getValue() - leftValue.getValue());
            case '*' : return new IntValue(rightValue.getValue() * leftValue.getValue());
            case '/' : {
                if (leftValue.getValue() != 0) {
                   return new IntValue(rightValue.getValue() / leftValue.getValue());
                }else{
                    throw new ExpressionException("Division by 0 is not allowed!");
                }
            }
        };
        throw new ExpressionException("Wrong operator!");
    }

    @Override
    public String toString() {
        return "( " + this.left + " " +this.op + " " + this.right + " )";
    }
}
