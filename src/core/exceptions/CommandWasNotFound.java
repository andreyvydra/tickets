package core.exceptions;

public class CommandWasNotFound extends RuntimeException {
    public CommandWasNotFound() {
        super("Команда не была найдена");
    }
}
