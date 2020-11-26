package Model.CustomExceptions;

public class ExpressionException extends MyException{
    public ExpressionException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
