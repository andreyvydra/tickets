package requests;

import core.Globals;
import data.Ticket;

import java.util.HashMap;

public class RemoveLowerRequest extends Request {
    private final Ticket ticket;
    public RemoveLowerRequest(Ticket ticket, HashMap<String, String> user) {
        super(Globals.CommandNames.REMOVE_LOWER, user);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
