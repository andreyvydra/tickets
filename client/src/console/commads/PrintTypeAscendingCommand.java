package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.PrintTypeAscendingRequest;
import responses.PrintTypeAscendingResponse;

import java.io.IOException;
import java.util.HashMap;

/**
 * PrintTypeAscendingCommand prints every ticket's type ascending.
 *
 */
public class PrintTypeAscendingCommand extends ServerCommand {


    public PrintTypeAscendingCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) throws IOException, ClassNotFoundException {
        PrintTypeAscendingResponse response = (PrintTypeAscendingResponse) udpClient.sendRequestAndGetResponse(new PrintTypeAscendingRequest(user));
        outputHandler.println(response);
        for (Object type : response.getData()) {
            outputHandler.println(type);
        }
    }

    @Override
    public void description() {
        outputHandler.println("print_field_ascending_type : вывести значения поля type" +
                " всех элементов в порядке возрастания");
    }
}
