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
public class RemoveLowerCommand extends AddCommand {


    public RemoveLowerCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        Response response = udpClient.sendRequestAndGetResponse(new RemoveLowerRequest(ticket, user));
        outputHandler.println(response);
    }

    @Override
    public void description() {
        outputHandler.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
