package commands;

import application.DataApp;
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
        if (!dataApp.checkUser(request.getUser())) {
            return new ShowResponse("Ошибка авторизации", new Object[]{});
        }
        ArrayList<String> data = new ArrayList<>();
        if (dataApp.getCollectionLen() != 0) {
            dataApp.getCollection().forEach(x -> data.add(x.toString()));
        } else {
            return new ShowResponse("Коллекция пуста!", data.toArray());
        }
        return new ShowResponse("Коллекция", data.toArray());
    }
}
