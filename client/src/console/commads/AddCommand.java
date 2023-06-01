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

    public AddCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        try {
            if (!Objects.isNull(user.get(IS_LOGGED))) {
                Ticket ticket = InputTicket.getTicketWithoutIdFromConsole(user);
                Response response = udpClient.sendRequestAndGetResponse(new AddRequest(ticket, user));
                outputHandler.println(response);
            } else {
                outputHandler.println("Ошибка авторизации");
            }
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
