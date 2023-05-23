package requests;

public class TicketExistRequest extends Request {
    public long ticketId;
    public TicketExistRequest(String commandName, long ticketId) {
        super(commandName);
        this.ticketId = ticketId;
    }
}
