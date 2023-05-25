package requests;

import core.Globals;
import data.TicketType;

import java.util.HashMap;

public class GreaterThanTypeRequest extends Request {
    private final TicketType ticketType;

    public GreaterThanTypeRequest(TicketType type, HashMap<String, String> user) {
        super(Globals.CommandNames.COUNT_GREATER_THAN_TYPE, user);
        ticketType = type;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
