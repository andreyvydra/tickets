package application;

import core.OutputHandler;
import network.UDPServer;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

import static core.Globals.CommandNames.EXIT;
import static core.Globals.CommandNames.SAVE;
import static core.Globals.SERVER_PORT;

public class ServerApp {
    private final UDPServer udpServer;
    private final DataApp dataApp;
    private final OutputHandler outputHandler = new OutputHandler();

    public ServerApp(String filename) throws SocketException {
        dataApp = new DataApp(filename);
        udpServer = new UDPServer(new InetSocketAddress(SERVER_PORT), dataApp);
    }

    public void run() {
        Thread thread = new Thread(() -> {
            outputHandler.println("Введите exit для того, чтобы выйти из " +
                    "программы или save, чтобы сохранить данные в файл.");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals(SAVE)) {
                    dataApp.saveJSONObjectToFile();
                } else if (input.equals(EXIT)) {
                    dataApp.saveJSONObjectToFile();
                    outputHandler.println("До скорой встречи!");
                    udpServer.stop();
                    break;
                }
            }
        });
        thread.start();
        udpServer.run();
    }
}
