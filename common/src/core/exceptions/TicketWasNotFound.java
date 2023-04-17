package core.exceptions;

public class TicketWasNotFound extends Exception {
    public TicketWasNotFound() {
        super("Ticket не был найден");
    }
}
