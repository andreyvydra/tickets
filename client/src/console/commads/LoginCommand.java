package console.commads;

import console.commads.generalCommands.DefaultCommand;
import core.OutputHandler;

import java.util.HashMap;
import java.util.Scanner;

import static core.Globals.Network.PASSWORD;
import static core.Globals.Network.USERNAME;

public class LoginCommand extends DefaultCommand {
    public LoginCommand(OutputHandler outputHandler) {
        super(outputHandler);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        Scanner scanner = new Scanner(System.in);
        outputHandler.print("Введите username: ");
        user.put(USERNAME, scanner.nextLine());
        outputHandler.print("Введите пароль: ");
        user.put(PASSWORD, scanner.nextLine());
        outputHandler.println("Username и пароль запомнены!");
    }

    @Override
    public void description() {
        outputHandler.println("login : ввести логин и пароль");
    }
}
