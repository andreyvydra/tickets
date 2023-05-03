package core.exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException() {
        super("Поле не может быть null");
    }
}
