package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.ClearRequest;
import responses.Response;

import java.io.IOException;

/**
 * ClearCommand deletes all tickets from collection.
 *
 */
public class ClearCommand extends ServerCommand {


    public ClearCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command) {
        try {
            Response response = udpClient.sendRequestAndGetResponse(new ClearRequest());
            outputHandler.println(response);
        } catch (IOException | ClassNotFoundException e) {
            outputHandler.println("Коллекция не была очищена!");
        }

    }

    @Override
    public void printHelp() {
        outputHandler.println("clear : очистить коллекцию");
    }
}
