package responses;

import static core.Globals.Network.TICKET_IS_NOT_EXIST;

public class TicketExistResponse extends Response{
    public int status = TICKET_IS_NOT_EXIST;
    public TicketExistResponse(String msg, int status) {
        super(msg);
        this.status = status;
    }
}
