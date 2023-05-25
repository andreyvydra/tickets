package commands;

import application.DataApp;
import requests.CreateUserRequest;
import requests.Request;
import responses.CreateUserResponse;
import responses.Response;

import java.util.logging.Logger;

import static core.Globals.Network.USERNAME;

public class Register extends Command {
    public Register(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        CreateUserRequest request1 = (CreateUserRequest) request;
        long id = dataApp.createUser(request1.getUser());
        if (id > 0) {
            return new CreateUserResponse("User " + request1.getUser().get(USERNAME) + " был создан!", id);
        }
        return new CreateUserResponse("User не был добавлен!", id);
    }
}
