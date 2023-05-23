package commands;

import application.DataApp;
import requests.Request;
import requests.TicketExistRequest;
import responses.Response;
import responses.TicketExistResponse;

import java.util.Objects;
import java.util.logging.Logger;

import static core.Globals.Network.TICKET_IS_EXIST;
import static core.Globals.Network.TICKET_IS_NOT_EXIST;

public class TicketExist extends  Command{
    public TicketExist(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        TicketExistRequest request1 = (TicketExistRequest) request;
        if (Objects.isNull(dataApp.getTicketById(request1.ticketId))) {
            return new TicketExistResponse("Тикета нет", TICKET_IS_NOT_EXIST);
        }
        return new TicketExistResponse("Тикет есть",  TICKET_IS_EXIST);
    }
}
