package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.RemoveRequest;
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
        } catch (IOException | ClassNotFoundException e) {
            outputHandler.println(e);
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("show : вывести в стандартный поток вывода все элементы" +
                " коллекции в строковом представлении");
    }
}
