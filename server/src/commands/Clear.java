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
        if (dataApp.getCollectionLen() == 0) {
            return new ClearResponse("Коллекция пуста!");
        }
        dataApp.clearCollectionManger();
        return new ClearResponse("Коллекция очищена!");
    }
}
