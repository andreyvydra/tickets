package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.InfoRequest;
import responses.InfoResponse;

import java.io.IOException;
import java.util.HashMap;

/**
 * InfoCommand shows info about CollectionManager
 *
 */
public class InfoCommand extends ServerCommand {

    public InfoCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        try {
            InfoResponse response = (InfoResponse) udpClient.sendRequestAndGetResponse(new InfoRequest(user));
            outputHandler.println(response);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Проблема с сериализацией " + e);
        } catch (IOException e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }
}
