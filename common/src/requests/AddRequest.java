package requests;


import data.Ticket;
import core.Globals.CommandNames;

import java.util.HashMap;

public class AddRequest extends Request {
    private final Ticket ticket;

    public AddRequest(Ticket ticket, HashMap<String, String> user) {
        super(CommandNames.ADD, user);
        this.ticket = ticket;
    }

    public Ticket getTicket() {
        return ticket;
    }
}
