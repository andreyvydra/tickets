package commands;

import application.DataApp;
import requests.Request;
import responses.PrintTypeAscendingResponse;
import responses.Response;

import java.util.ArrayList;
import java.util.logging.Logger;

public class PrintTypeAscending extends Command {
    public PrintTypeAscending(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new PrintTypeAscendingResponse("Ошибка авторизации", new Object[]{});
        }
        ArrayList<String> types = new ArrayList<>();
        if (dataApp.getCollectionLen() == 0) {
            return new PrintTypeAscendingResponse("Коллекция пуста!", types.toArray());
        }
        dataApp.getCollection().forEach(x -> types.add(x.getType().toString()));
        return new PrintTypeAscendingResponse("Типы билетов", types.toArray());
    }
}
