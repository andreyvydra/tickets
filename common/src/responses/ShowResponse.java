package responses;


import data.Ticket;

import java.util.ArrayList;

public class ShowResponse extends DataResponse {
    public ArrayList<Ticket> tickets;
    public ShowResponse(String msg, ArrayList<Ticket> data) {
        super(msg, new Object[]{});
        tickets = data;
    }
}
