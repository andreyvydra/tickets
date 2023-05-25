package requests;

import core.Globals;
import data.Ticket;

import java.util.HashMap;

public class UpdateRequest extends Request{
    public Ticket ticket;
    private final long id;
    public UpdateRequest(Ticket ticket, long id, HashMap<String, String> user) {
        super(Globals.CommandNames.UPDATE, user);
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
