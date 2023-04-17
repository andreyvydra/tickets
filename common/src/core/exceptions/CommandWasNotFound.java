package core.exceptions;

public class CommandWasNotFound extends Exception {
    public CommandWasNotFound() {
        super("Команда не была найдена");
    }
}
