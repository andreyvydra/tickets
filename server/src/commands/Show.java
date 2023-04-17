package commands;

import application.DataApp;
import requests.Request;
import responses.InfoResponse;
import responses.Response;

import java.util.logging.Logger;

public class Show extends Command {
    public Show(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        StringBuilder sb = new StringBuilder();
        dataApp.getCollection().forEach(sb::append);
        return new InfoResponse(sb.toString());
    }
}
