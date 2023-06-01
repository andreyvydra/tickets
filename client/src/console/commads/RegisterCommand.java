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
    public void execute(String command, HashMap<String, String> user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            outputHandler.print("Введите username: ");
            String username = scanner.nextLine();
            if (!username.equals("")) {
                user.put(USERNAME, username);
                break;
            }
            outputHandler.println("Username пустой!");
        }
        while (true) {
            outputHandler.print("Введите пароль: ");
            String password = scanner.nextLine();
            if (!password.equals("")) {
                user.put(PASSWORD, password);
                break;
            }
            outputHandler.println("Password пустой!");
        }
        try {
            CreateUserResponse response = (CreateUserResponse) udpClient.sendRequestAndGetResponse(new CreateUserRequest(user));
            outputHandler.println(response.getMsg());
            if (response.userId > 0) {
                outputHandler.println("Username и пароль запомнены!");
                user.put(IS_LOGGED, "Да");
            } else {
                user.put(USERNAME, null);
                user.put(PASSWORD, null);
                user.put(IS_LOGGED, null);
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
