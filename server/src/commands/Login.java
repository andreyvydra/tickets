package commands;

import application.DataApp;
import requests.Request;
import responses.LoginResponse;
import responses.Response;

import java.util.logging.Logger;

public class Login extends Command {
    public Login(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        if (dataApp.checkUser(request.getUser())) {
            return new LoginResponse("Вы залогинены", true);
        } else {
            return new LoginResponse("Вы не залогинены :(", false);
        }
    }
}
