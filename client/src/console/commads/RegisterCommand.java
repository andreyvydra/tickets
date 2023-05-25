package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.CreateUserRequest;
import responses.CreateUserResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import static core.Globals.Network.PASSWORD;
import static core.Globals.Network.USERNAME;

public class RegisterCommand extends ServerCommand {
    public RegisterCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        Scanner scanner = new Scanner(System.in);
        outputHandler.print("Введите username: ");
        user.put(USERNAME, scanner.nextLine());
        outputHandler.print("Введите пароль: ");
        user.put(PASSWORD, scanner.nextLine());
        try {
            CreateUserResponse response = (CreateUserResponse) udpClient.sendRequestAndGetResponse(new CreateUserRequest(user));
            outputHandler.println(response.getMsg());
            if (response.userId > 0) {
                outputHandler.println("Username и пароль запомнены!");
            } else {
                user.put(USERNAME, null);
                user.put(PASSWORD, null);
            }
        } catch (IOException e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("register : зарегистрировать нового пользователя");
    }
}
