package core.exceptions;

public class IncorrectEnumNameException extends Exception {
    public IncorrectEnumNameException() {
        super("Некорректное имя enum");
    }
}
