package commands;

import application.DataApp;
import requests.GreaterThanTypeRequest;
import requests.Request;
import responses.GreaterThanTypeResponse;
import responses.Response;

import java.util.logging.Logger;

public class GreaterThanType extends Command {
    public GreaterThanType(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        GreaterThanTypeRequest request1 = (GreaterThanTypeRequest) request;
        long count = dataApp.getCollection().stream().filter(
                x -> x.getType().compareTo(request1.getTicketType()) > 0
        ).count();
        return new GreaterThanTypeResponse("Кол-во элементов больших по типу " +
                request1.getTicketType() + ": " + count);
    }
}
