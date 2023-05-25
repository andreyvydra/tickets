package commands;

import application.DataApp;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;
import requests.AddRequest;
import requests.Request;
import responses.AddResponse;
import responses.Response;

import java.util.logging.Logger;

public class Add extends Command {


    public Add(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new AddResponse("Ошибка авторизации", -1);
        }
        AddRequest addRequest = (AddRequest) request;
        Ticket ticket = addRequest.getTicket();
        try {
            if (dataApp.addTicketToCollectionWithoutId(addRequest.getTicket())) {
                return new AddResponse("Элемент добавлен", ticket.getId());
            }
            return new AddResponse("Элемент не был добавлен", -1);
        } catch (ValueIsNotPositiveException e) {
            return new AddResponse("Элемент не был добавлен", -1);
        }
    }
}
