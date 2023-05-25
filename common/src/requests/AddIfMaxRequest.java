package requests;

import data.Ticket;

import java.util.HashMap;

public class AddIfMaxRequest extends AddRequest {
    public AddIfMaxRequest(Ticket ticket, HashMap<String, String> user) {
        super(ticket, user);
    }
}
