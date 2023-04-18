package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.ShowRequest;
import responses.Response;

import java.io.IOException;

/**
 * This class shows full info about every ticket at collection.
 *
 */
public class ShowCommand extends ServerCommand {


    public ShowCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command) {
        try {
            Response response = udpClient.sendRequestAndGetResponse(new ShowRequest());
            outputHandler.println(response);
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("show : вывести в стандартный поток вывода все элементы" +
                " коллекции в строковом представлении");
    }
}
