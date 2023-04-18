package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import data.Ticket;
import data.TicketType;
import network.UDPClient;
import requests.GreaterThanTypeRequest;
import responses.Response;

import java.io.IOException;

import static core.Globals.ARGUMENT_POSITION;

/**
 * GreaterThenTypeCommand shows tickets, which have larger
 * TicketType than inputted type.
 *
 * @see Ticket
 */
public class GreaterThenTypeCommand extends ServerCommand {


    public GreaterThenTypeCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command) {
        try {
            TicketType type = TicketType.valueOf(command.split(" ")[ARGUMENT_POSITION].toUpperCase());
            Response response = udpClient.sendRequestAndGetResponse(new GreaterThanTypeRequest(type));
            outputHandler.println(response);
        } catch (ArrayIndexOutOfBoundsException e) {
            outputHandler.println("Аргумен не был введён!");
        } catch (IllegalArgumentException e) {
            outputHandler.println("Такого type не существует!");
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("count_greater_than_type type : вывести количество элементов, значение" +
                " поля type которых больше заданного");
    }
}
