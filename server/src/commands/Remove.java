package commands;

import application.DataApp;
import core.exceptions.TicketWasNotFound;
import requests.RemoveRequest;
import requests.Request;
import responses.RemoveResponse;
import responses.Response;

import java.util.logging.Logger;

public class Remove extends Command {
    public Remove(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new RemoveResponse("Ошибка авторизации");
        }
        RemoveRequest request1 = (RemoveRequest) request;
        try {
            if (dataApp.removeTicketById(request1.getId(), request1.getUser())) {
                return new RemoveResponse("Ticket " + request1.getId() + " успешно удалён!");
            }
            return new RemoveResponse("Ticket " + request1.getId() + " не был удалён!");
        } catch (TicketWasNotFound e) {
            return new RemoveResponse("Ticket " + request1.getId() + " не был найден!");
        }
    }
}
