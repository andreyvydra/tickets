package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.AddRequest;
import responses.Response;

import java.io.IOException;

/**
 * AddCommand adds new item to collection.
 *
 * @see core.InputTicket
 */
public class AddCommand extends ServerCommand {

    public AddCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command) {
        try {
            Ticket ticket = InputTicket.getTicketWithoutIdFromConsole();
            Response response = udpClient.sendRequestAndGetResponse(new AddRequest(ticket));
            outputHandler.println(response);
        } catch (IOException | ClassNotFoundException e) {
            outputHandler.println("Тикет не добавлен" + e);
        }
    }

    public void printHelp() {
        outputHandler.println("add {element} : добавить новый элемент в коллекцию");
    }
}
