package commands;

import application.DataApp;
import data.Ticket;
import requests.Request;
import requests.TicketExistRequest;
import responses.Response;
import responses.TicketExistResponse;

import java.util.Objects;
import java.util.logging.Logger;

import static core.Globals.Network.*;

public class TicketExist extends  Command{
    public TicketExist(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new TicketExistResponse("Ошибка авторизации", TICKET_IS_NOT_EXIST);
        }
        TicketExistRequest request1 = (TicketExistRequest) request;
        Ticket ticket = dataApp.getTicketById(request1.ticketId);
        if (Objects.isNull(ticket)) {
            return new TicketExistResponse("Тикета нет", TICKET_IS_NOT_EXIST);
        } else if (!ticket.getCreatorLogin().equals(request.getUser().get(USERNAME))) {
            return new TicketExistResponse("Тикет не ваш", TICKET_IS_NOT_EXIST);
        }
        return new TicketExistResponse("Тикет есть",  TICKET_IS_EXIST);
    }
}
