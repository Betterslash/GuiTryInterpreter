package Model.CustomExceptions;

public class StatementException extends MyException{
    public StatementException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
