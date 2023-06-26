package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.AddRequest;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static core.Globals.Network.IS_LOGGED;


/**
 * AddCommand adds new item to collection.
 *
 * @see core.InputTicket
 */
public class AddCommand extends ServerCommand {

    public Ticket ticket;

    public AddCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        if (!Objects.isNull(user.get(IS_LOGGED))) {
            Response response = udpClient.sendRequestAndGetResponse(new AddRequest(ticket, user));
            outputHandler.println(response);
        } else {
            outputHandler.println("Ошибка авторизации");
        }
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void description() {
        outputHandler.println("add {element} : добавить новый элемент в коллекцию");
    }
}
