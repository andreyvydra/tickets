package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import data.Ticket;
import network.UDPClient;
import requests.GroupByDateRequest;
import responses.GroupByDateResponse;

import java.io.IOException;
import java.util.HashMap;

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
    public void execute(String command, HashMap<String, String> user) {
        try {
            GroupByDateResponse response = (GroupByDateResponse) udpClient.sendRequestAndGetResponse(new GroupByDateRequest(user));
            outputHandler.println(response);
            for (Object group : response.getData()) {
                outputHandler.println(group);
            }
        } catch (IOException  e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }
    }

    @Override
    public void description() {
        outputHandler.println("group_counting_by_creation_date : сгруппировать элементы коллекции по" +
                " значению поля creationDate, вывести количество элементов в каждой группе");
    }
}
