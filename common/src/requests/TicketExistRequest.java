package requests;

import java.util.HashMap;

import static core.Globals.CommandNames.TICKET_EXIST;

public class TicketExistRequest extends Request {
    public long ticketId;
    public TicketExistRequest(long ticketId, HashMap<String, String> user) {
        super(TICKET_EXIST, user);
        this.ticketId = ticketId;
    }
}
