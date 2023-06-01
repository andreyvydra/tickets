package commands;

import application.DataApp;
import requests.Request;
import responses.ClearResponse;
import responses.Response;

import java.util.logging.Logger;

public class Clear extends Command {
    public Clear(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (!dataApp.checkUser(request.getUser())) {
            return new ClearResponse("Ошибка авторизации");
        }
        if (dataApp.getCollectionLen() == 0) {
            return new ClearResponse("Коллекция пуста!");
        }
        int counter = dataApp.clearCollectionManger(request.getUser());
        return new ClearResponse("Коллекция очищена! Удалено " + counter + " элементов.");
    }
}
