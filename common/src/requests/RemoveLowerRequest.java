package requests;

import core.Globals;
import data.Ticket;

public class RemoveLowerRequest extends Request {
    private Ticket ticket;
    public RemoveLowerRequest(Ticket ticket) {
        super(Globals.CommandNames.REMOVE_LOWER);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
