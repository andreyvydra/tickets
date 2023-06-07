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
            LoginResponse response = (LoginResponse) udpClient.sendRequestAndGetResponse(new LoginUserRequest(user));
            if (response.is_logged) {
                user.put(IS_LOGGED, "Да");
            } else {
                user.put(IS_LOGGED, null);
            }
            outputHandler.println(response.getMsg());
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("login : ввести логин и пароль");
    }
}
