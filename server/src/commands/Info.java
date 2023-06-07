package commands;

import application.DataApp;
import requests.Request;
import responses.InfoResponse;
import responses.Response;

import java.util.logging.Logger;

public class Info extends Command {
    public Info(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new InfoResponse("Ошибка авторизации");
        }
        return new InfoResponse(dataApp.getCollectionManager().toString());
    }
}
