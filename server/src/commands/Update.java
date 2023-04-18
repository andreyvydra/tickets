package commands;

import application.DataApp;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;
import requests.Request;
import requests.UpdateRequest;
import responses.Response;
import responses.UpdateResponse;

import java.util.Objects;
import java.util.logging.Logger;

public class Update extends Command {
    public Update(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        UpdateRequest request1 = (UpdateRequest) request;
        Ticket curTicket = dataApp.getTicketById(request1.getId());
        Ticket inpTicket = request1.getTicket();
        if (Objects.isNull(curTicket)) {
            return new UpdateResponse("Тикет не был найден!");
        }
        try {
            curTicket.setName(inpTicket.getName());
            curTicket.setType(inpTicket.getType());
            curTicket.setPerson(inpTicket.getPerson());
            curTicket.setPrice(inpTicket.getPrice());
            curTicket.setCoordinates(inpTicket.getCoordinates());
        } catch (ValueIsNotPositiveException | EmptyNameException | EmptyFieldException e) {
            return new UpdateResponse(e.toString());
        }
        return new UpdateResponse("Тикет " + request1.getId() + " был обновлён");
    }
}
