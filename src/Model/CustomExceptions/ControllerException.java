package Model.CustomExceptions;

public class ControllerException extends MyException{
    public ControllerException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
