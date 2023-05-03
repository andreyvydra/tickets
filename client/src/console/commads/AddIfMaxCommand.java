package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.AddIfMaxRequest;
import responses.Response;

import java.io.IOException;

/**
 * AddIfMaxCommand adds inputted element if it's max of collection.
 *
 */
public class AddIfMaxCommand extends ServerCommand {


    public AddIfMaxCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command) {
        try {
            Ticket ticket = InputTicket.getTicketWithoutIdFromConsole();
            Response response = udpClient.sendRequestAndGetResponse(new AddIfMaxRequest(ticket));
            outputHandler.println(response);
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("add_if_max {element} : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
    }
}
