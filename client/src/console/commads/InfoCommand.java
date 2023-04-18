package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.InfoRequest;
import responses.Response;

import java.io.IOException;

/**
 * InfoCommand shows info about CollectionManager
 *
 */
public class InfoCommand extends ServerCommand {

    public InfoCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command) {
        try {
            Response response = udpClient.sendRequestAndGetResponse(new InfoRequest());
            outputHandler.println(response);
        } catch (IOException | ClassNotFoundException e) {
            outputHandler.println("Проблема с сериализацией");
        }
    }

    @Override
    public void description() {
        outputHandler.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }
}
