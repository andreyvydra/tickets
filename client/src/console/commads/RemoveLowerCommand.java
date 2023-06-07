package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.RemoveLowerRequest;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * RemoveLowerCommand deletes all tickets which are lower than inputted ticket.
 *
 * @see InputTicket
 */
public class RemoveLowerCommand extends ServerCommand {


    public RemoveLowerCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        Ticket ticket = InputTicket.getTicketWithoutIdFromConsole(user);
        try {
            Response response = udpClient.sendRequestAndGetResponse(new RemoveLowerRequest(ticket, user));
            outputHandler.println(response);
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
