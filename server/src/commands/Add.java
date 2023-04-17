package commands;

import application.DataApp;
import core.OutputHandler;
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
        AddRequest addRequest = (AddRequest) request;
        Ticket ticket = addRequest.getTicket();
        try {
            dataApp.setIdToTicket(ticket);
            dataApp.addTicketToCollectionWithoutId(addRequest.getTicket());
            return new AddResponse("Элемент добавлен", ticket.getId());
        } catch (ValueIsNotPositiveException e) {
            return new Response("Элемент не был добавлен");
        }
    }
}
