package requests;

import core.Globals;
import data.Ticket;

public class UpdateRequest extends Request{
    public Ticket ticket;
    private long id;
    public UpdateRequest(Ticket ticket, long id) {
        super(Globals.CommandNames.UPDATE);
        this.ticket = ticket;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
