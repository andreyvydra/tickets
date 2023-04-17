package console.commads;

import console.commads.generalCommands.ServerCommand;
import core.OutputHandler;
import network.UDPClient;
import requests.PrintTypeAscendingRequest;
import responses.Response;

import java.io.IOException;

/**
 * PrintTypeAscendingCommand prints every ticket's type ascending.
 *
 */
public class PrintTypeAscendingCommand extends ServerCommand {


    public PrintTypeAscendingCommand(OutputHandler outputHandler, UDPClient client) {
        super(outputHandler, client);
    }

    @Override
    public void execute(String command) {
        try {
            Response response = udpClient.sendRequestAndGetResponse(new PrintTypeAscendingRequest());
            outputHandler.println(response);
        } catch (IOException | ClassNotFoundException e) {
            outputHandler.println("Коллекция не была очищена!");
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("print_field_ascending_type : вывести значения поля type" +
                " всех элементов в порядке возрастания");
    }
}
