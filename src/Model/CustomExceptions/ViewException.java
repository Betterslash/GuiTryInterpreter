package Model.CustomExceptions;

public class ViewException extends MyException{
    public ViewException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
