package requests;

import data.Ticket;

public class AddIfMaxRequest extends AddRequest {
    public AddIfMaxRequest(Ticket ticket) {
        super(ticket);
    }
}
