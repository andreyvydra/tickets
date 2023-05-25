package commands;

import application.DataApp;
import data.Ticket;
import requests.RemoveLowerRequest;
import requests.Request;
import responses.RemoveLowerResponse;
import responses.Response;

import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;

import static core.Globals.Network.USERNAME;

public class RemoveLower extends Command {
    public RemoveLower(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new RemoveLowerResponse("Ошибка авторизации");
        }
        Ticket ticket = ((RemoveLowerRequest) request).getTicket();
        ArrayList<Ticket> ticketsToDelete = new ArrayList<>();
        dataApp.getCollection().stream().filter(x -> (x.compareTo(ticket) < 0 && Objects.equals(x.getCreatorLogin(),
                request.getUser().get(USERNAME)))).forEach(ticketsToDelete::add);
        dataApp.removeTickets(ticketsToDelete);
        return new RemoveLowerResponse("Удалено " + ticketsToDelete.size() + " тикетов");
    }
}
