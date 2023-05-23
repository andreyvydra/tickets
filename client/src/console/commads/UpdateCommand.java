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

import static core.Globals.ARGUMENT_POSITION;
import static core.Globals.CommandNames.TICKET_EXIST;
import static core.Globals.Network.TICKET_IS_EXIST;

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
        String[] arguments = command.split(" ");
        if (arguments.length == 1) {
            outputHandler.println("Введите id для update.");
        } else {
            long id = Long.parseLong(arguments[ARGUMENT_POSITION]);

            try {
                TicketExistResponse responseId = (TicketExistResponse) udpClient.sendRequestAndGetResponse(new TicketExistRequest(TICKET_EXIST, id));
                if (responseId.status == TICKET_IS_EXIST) {
                    Ticket inpTicket = InputTicket.getTicketWithoutIdFromConsole();
                    Response response = udpClient.sendRequestAndGetResponse(new UpdateRequest(inpTicket, id));
                    outputHandler.println(response);
                } else {
                    outputHandler.println(responseId.getMsg());
                }
            } catch (IOException  e) {
                outputHandler.println("Ошибка при передачи данных! " + e);
            } catch (ClassNotFoundException e) {
                outputHandler.println("Класс не был найден! " + e);
            }
        }
    }

    @Override
    public void description() {
        outputHandler.println("update id {element} : обновить значение элемента коллекции," +
                " id которого равен заданному");
    }
}
