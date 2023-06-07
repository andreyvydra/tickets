package commands;

import application.DataApp;
import data.Ticket;
import requests.Request;
import requests.UpdateRequest;
import responses.Response;
import responses.UpdateResponse;

import java.util.logging.Logger;


public class Update extends Command {
    public Update(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new UpdateResponse("Ошибка авторизации");
        }
        UpdateRequest request1 = (UpdateRequest) request;
        Ticket inpTicket = request1.getTicket();
        if (!dataApp.updateTicket(request1.getId(), inpTicket)) {
            return new UpdateResponse("Тикет " + request1.getId() + " не был обновлён");
        }
        return new UpdateResponse("Тикет " + request1.getId() + " был обновлён");
    }
}
