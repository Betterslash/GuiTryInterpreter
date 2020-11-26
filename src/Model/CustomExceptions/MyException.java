package Model.CustomExceptions;

public class MyException extends RuntimeException{
    final String message;

    public MyException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
