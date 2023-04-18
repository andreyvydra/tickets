package commands;

import application.DataApp;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;
import requests.AddIfMaxRequest;
import requests.Request;
import responses.AddResponse;
import responses.Response;

import java.util.logging.Logger;

public class AddIfMax extends Command {


    public AddIfMax(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        Ticket ticket = ((AddIfMaxRequest) request).getTicket();
        try {
            if (ticket.compareTo(dataApp.getMaxTicket()) > 0) {
                if (dataApp.addTicketToCollectionWithoutId(ticket)) {
                    return new AddResponse("Тикет был добавлен", ticket.getId());
                } else {
                    return new AddResponse("Тикет не был добавлен", -1);
                }
            }
            return new AddResponse("Тикет не является самым большим", -1);
        } catch (ValueIsNotPositiveException e) {
            return new AddResponse("Тикет не смог добавиться!", -1);
        }
    }
}
