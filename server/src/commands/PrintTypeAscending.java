package commands;

import application.DataApp;
import requests.Request;
import responses.PrintTypeAscendingResponse;
import responses.Response;

import java.util.logging.Logger;

public class PrintTypeAscending extends Command {
    public PrintTypeAscending(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        StringBuilder sb = new StringBuilder();
        if (dataApp.getCollectionLen() == 0) {
            return new PrintTypeAscendingResponse("Коллекция пуста!");
        }
        dataApp.getCollection().forEach(x -> sb.append(x.getType()).append("\n"));
        return new PrintTypeAscendingResponse(sb.toString());
    }
}
