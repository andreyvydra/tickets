package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.UpdateRequest;
import responses.Response;

import java.io.IOException;

import static core.Globals.ARGUMENT_POSITION;

/**
 * UpdateCommand updates ticket by id and extends Input
 * collection command. It finds changeable item
 * and update its attributes.
 *
 * @see InputTicket
 */
public class UpdateCommand extends ServerCommand {

    public UpdateCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[ARGUMENT_POSITION]);
        Ticket inpTicket = InputTicket.getTicketWithoutIdFromConsole();
        try {
            Response response = udpClient.sendRequestAndGetResponse(new UpdateRequest(inpTicket, id));
            outputHandler.println(response);
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("update id {element} : обновить значение элемента коллекции," +
                " id которого равен заданному");
    }
}
