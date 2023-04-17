package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.RemoveRequest;
import responses.Response;

import java.io.IOException;

import static core.Globals.ARGUMENT_POSITION;

/**
 * RemoveCommand deletes ticket by id.
 *
 */
public class RemoveCommand extends ServerCommand {


    public RemoveCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[ARGUMENT_POSITION]);
        try {
            Response response = udpClient.sendRequestAndGetResponse(new RemoveRequest(id));
            outputHandler.println(response);
        } catch (IOException | ClassNotFoundException e) {
            outputHandler.println("Коллекция не была очищена!");
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("remove_by_id id : удалить элемент из коллекции по его id");
    }
}
