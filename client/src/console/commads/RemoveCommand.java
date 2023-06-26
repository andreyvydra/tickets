package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.RemoveRequest;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;

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
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        String[] arguments = command.split(" ");
        if (arguments.length == 1) {
            outputHandler.println("Введите id для remove.");
        } else {
            long id = Long.parseLong(command.split(" ")[ARGUMENT_POSITION]);
            Response response = udpClient.sendRequestAndGetResponse(new RemoveRequest(id, user));
            outputHandler.println(response);
        }
    }

    @Override
    public void description() {
        outputHandler.println("remove_by_id id : удалить элемент из коллекции по его id");
    }
}
