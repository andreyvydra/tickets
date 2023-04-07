package core.exceptions;

public class EmptyNameException extends Exception {
    public EmptyNameException() {
        super("Название не может быть пустым!");
    }
}
