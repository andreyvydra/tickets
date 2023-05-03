package commands;

import application.DataApp;
import core.exceptions.TicketWasNotFound;
import requests.RemoveRequest;
import requests.Request;
import responses.RemoveResponse;
import responses.Response;

import java.util.Objects;
import java.util.logging.Logger;

public class Remove extends Command {
    public Remove(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        RemoveRequest request1 = (RemoveRequest) request;
        try {
            dataApp.removeTicketById(request1.getId());
            if (Objects.isNull(dataApp.getTicketById(request1.getId()))) {
                return new RemoveResponse("Ticket " + request1.getId() + " успешно удалён!");
            }
            return new RemoveResponse("Ticket " + request1.getId() + " не был найден!");
        } catch (TicketWasNotFound e) {
            return new RemoveResponse("Ticket " + request1.getId() + " не был найден!");
        }
    }
}
