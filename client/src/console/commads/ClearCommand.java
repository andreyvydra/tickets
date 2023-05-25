package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.ClearRequest;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * ClearCommand deletes all tickets from collection.
 *
 */
public class ClearCommand extends ServerCommand {


    public ClearCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        try {
            Response response = udpClient.sendRequestAndGetResponse(new ClearRequest(user));
            outputHandler.println(response);
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }

    }

    @Override
    public void description() {
        outputHandler.println("clear : очистить коллекцию");
    }
}
