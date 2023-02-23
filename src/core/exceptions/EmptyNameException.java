package core.exceptions;

public class EmptyNameException extends RuntimeException {
    public EmptyNameException() {
        super("Название не может быть пустым!");
    }
}
