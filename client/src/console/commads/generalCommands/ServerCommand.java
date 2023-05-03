package console.commads.generalCommands;

import core.OutputHandler;
import network.UDPClient;

abstract public class ServerCommand extends DefaultCommand {
    protected UDPClient udpClient;

    public ServerCommand(OutputHandler outputHandler, UDPClient udpClient) {
        super(outputHandler);
        this.udpClient = udpClient;
    }

}
