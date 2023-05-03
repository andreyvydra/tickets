package requests;


import data.Ticket;
import core.Globals.CommandNames;

public class AddRequest extends Request {
    private Ticket ticket;

    public AddRequest(Ticket ticket) {
        super(CommandNames.ADD);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
