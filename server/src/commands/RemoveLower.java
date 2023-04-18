package commands;

import application.DataApp;
import data.Ticket;
import requests.RemoveLowerRequest;
import requests.Request;
import responses.RemoveLowerResponse;
import responses.Response;

import java.util.ArrayList;
import java.util.logging.Logger;

public class RemoveLower extends Command {
    public RemoveLower(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        Ticket ticket = ((RemoveLowerRequest) request).getTicket();
        ArrayList<Ticket> ticketsToDelete = new ArrayList<>();
        dataApp.getCollection().stream().filter(x -> x.compareTo(ticket) < 0).forEach(ticketsToDelete::add);
        dataApp.removeTickets(ticketsToDelete);
        return new RemoveLowerResponse("Удалено " + ticketsToDelete.size() + " тикетов");
    }
}
