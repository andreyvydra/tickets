package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.GroupByDateRequest;
import responses.Response;

import java.io.IOException;

/**
 * GroupByDateCommand group tickets by creationDate.
 *
 * @see Ticket
 */
public class GroupByDateCommand extends ServerCommand {


    public GroupByDateCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command) {
        try {
            Response response = udpClient.sendRequestAndGetResponse(new GroupByDateRequest());
            outputHandler.println(response);
        } catch (IOException | ClassNotFoundException e) {
            outputHandler.println("Коллекция не была очищена!");
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("group_counting_by_creation_date : сгруппировать элементы коллекции по" +
                " значению поля creationDate, вывести количество элементов в каждой группе");
    }
}
