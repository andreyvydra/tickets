package application;

import managers.CollectionManager;
import network.UDPServer;

import java.net.InetSocketAddress;
import java.net.SocketException;

public class ServerApp {
    private final UDPServer udpServer;
    private final DataApp dataApp;

    public ServerApp(String filename) throws SocketException {
        dataApp = new DataApp(filename);
        udpServer = new UDPServer(new InetSocketAddress(8000), dataApp);
    }

    public void run() {
        udpServer.run();
    }
}
