package commands;

import application.DataApp;
import data.Ticket;
import requests.Request;
import responses.Response;
import responses.ShowResponse;

import java.util.ArrayList;
import java.util.logging.Logger;

public class Show extends Command {
    public Show(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        if (!dataApp.checkUser(request.getUser())) {
            return new ShowResponse("Ошибка авторизации", tickets);
        }
        if (dataApp.getCollectionLen() != 0) {
            tickets.addAll(dataApp.getCollection());
        } else {
            return new ShowResponse("Коллекция пуста!", tickets);
        }
        return new ShowResponse("Коллекция", tickets);
    }
}
