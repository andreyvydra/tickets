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
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("add {element} : добавить новый элемент в коллекцию");
    }
}
