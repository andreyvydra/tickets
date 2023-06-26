package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.LoginUserRequest;
import responses.LoginResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static core.Globals.Network.*;

public class LoginCommand extends ServerCommand {

    public LoginCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        LoginResponse response = (LoginResponse) udpClient.sendRequestAndGetResponse(new LoginUserRequest(user));
        if (response.is_logged) {
            user.put(IS_LOGGED, "Да");
        } else {
            user.put(IS_LOGGED, null);
        }
        outputHandler.println(response.getMsg());
    }

    @Override
    public void description() {
        outputHandler.println("login : ввести логин и пароль");
    }
}
