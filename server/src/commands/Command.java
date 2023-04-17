package commands;

import application.DataApp;
import core.OutputHandler;
import requests.Request;
import responses.Response;

import java.util.logging.Logger;

public abstract class Command {
    protected Logger logger;
    protected DataApp dataApp;

    Command(Logger logger, DataApp dataApp) {
        this.logger = logger;
        this.dataApp = dataApp;
    }

    public DataApp getDataApp() {
        return dataApp;
    }

    public Logger getLogger() {
        return logger;
    }

    public abstract Response execute(Request request);
}
