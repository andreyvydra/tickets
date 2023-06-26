package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.ClearRequest;
import responses.Response;

import java.io.IOException;
import java.util.HashMap;

/**
 * ClearCommand deletes all tickets from collection.
 *
 */
public class ClearCommand extends ServerCommand {


    public ClearCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler, udpClient);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        Response response = udpClient.sendRequestAndGetResponse(new ClearRequest(user));
        outputHandler.println(response);
    }

    @Override
    public void description() {
        outputHandler.println("clear : очистить коллекцию");
    }
}
