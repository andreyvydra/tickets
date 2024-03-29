package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.CreateUserRequest;
import responses.CreateUserResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static core.Globals.Network.*;

public class RegisterCommand extends ServerCommand {
    public RegisterCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        CreateUserResponse response = (CreateUserResponse) udpClient.sendRequestAndGetResponse(new CreateUserRequest(user));
        outputHandler.println(response.getMsg());
        if (response.userId > 0) {
            user.put(IS_LOGGED, "Да");
        } else {
            user.put(USERNAME, null);
            user.put(PASSWORD, null);
            user.put(IS_LOGGED, null);
        }
    }

    @Override
    public void description() {
        outputHandler.println("register : зарегистрировать нового пользователя");
    }
}
