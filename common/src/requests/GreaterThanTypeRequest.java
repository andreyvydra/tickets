package requests;

import core.Globals;
import data.TicketType;

public class GreaterThanTypeRequest extends Request {
    private TicketType ticketType;

    public GreaterThanTypeRequest(TicketType type) {
        super(Globals.CommandNames.COUNT_GREATER_THAN_TYPE);
        ticketType = type;
    }

    public TicketType getTicketType() {
        return ticketType;
    }
}
