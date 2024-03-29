package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.TicketExistRequest;
import requests.UpdateRequest;
import responses.Response;
import responses.TicketExistResponse;

import java.io.IOException;
import java.util.HashMap;

import static core.Globals.ARGUMENT_POSITION;
import static core.Globals.Network.TICKET_IS_EXIST;

/**
 * UpdateCommand updates ticket by id and extends Input
 * collection command. It finds changeable item
 * and update its attributes.
 *
 * @see InputTicket
 */
public class UpdateCommand extends AddCommand {

    public UpdateCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        String[] arguments = command.split(" ");
        if (arguments.length == 1) {
            outputHandler.println("Введите id для update.");
        } else {
            long id = Long.parseLong(arguments[ARGUMENT_POSITION]);
            TicketExistResponse responseId = (TicketExistResponse) udpClient.sendRequestAndGetResponse(new TicketExistRequest(id, user));
            if (responseId.status == TICKET_IS_EXIST) {
                Response response = udpClient.sendRequestAndGetResponse(new UpdateRequest(ticket, id, user));
                outputHandler.println(response);
            } else {
                outputHandler.println(responseId.getMsg());
            }
        }
    }

    @Override
    public void description() {
        outputHandler.println("update id {element} : обновить значение элемента коллекции," +
                " id которого равен заданному");
    }
}
