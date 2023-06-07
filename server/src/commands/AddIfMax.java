package commands;

import application.DataApp;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;
import requests.AddIfMaxRequest;
import requests.Request;
import responses.AddResponse;
import responses.Response;

import java.util.logging.Logger;

import static core.Globals.Responses.TICKET_WAS_NOT_ADDED;

public class AddIfMax extends Command {


    public AddIfMax(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new AddResponse("Ошибка авторизации", -1);
        }
        Ticket ticket = ((AddIfMaxRequest) request).getTicket();
        try {
            if (ticket.compareTo(dataApp.getMaxTicket()) > 0) {
                if (dataApp.addTicketToCollectionWithoutId(ticket)) {
                    return new AddResponse("Тикет был добавлен", ticket.getId());
                } else {
                    return new AddResponse("Тикет не был добавлен", TICKET_WAS_NOT_ADDED);
                }
            }
            return new AddResponse("Тикет не является самым большим", TICKET_WAS_NOT_ADDED);
        } catch (ValueIsNotPositiveException e) {
            return new AddResponse("Тикет не смог добавиться!", TICKET_WAS_NOT_ADDED);
        }
    }
}
