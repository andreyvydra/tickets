package responses;

public class AddResponse extends Response {
    private long ticketId;
    public AddResponse(String msg, long id) {
        super(msg);
        ticketId = id;
    }

    public long getTicketId() {
        return ticketId;
    }
}
