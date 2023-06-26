package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.AddIfMaxRequest;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

import static core.Globals.Network.IS_LOGGED;

/**
 * AddIfMaxCommand adds inputted element if it's max of collection.
 *
 */
public class AddIfMaxCommand extends AddCommand {


    public AddIfMaxCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        if (!Objects.isNull(user.get(IS_LOGGED))) {
            Response response = udpClient.sendRequestAndGetResponse(new AddIfMaxRequest(ticket, user));
            outputHandler.println(response);
        } else {
            outputHandler.println("Ошибка авторизации");
        }
    }

    @Override
    public void description() {
        outputHandler.println("add_if_max {element} : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
    }
}
