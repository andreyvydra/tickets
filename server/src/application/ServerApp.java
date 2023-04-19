package application;

import core.OutputHandler;
import network.UDPServer;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class ServerApp {
    private final UDPServer udpServer;
    private final DataApp dataApp;
    private final OutputHandler outputHandler = new OutputHandler();

    public ServerApp(String filename) throws SocketException {
        dataApp = new DataApp(filename);
        udpServer = new UDPServer(new InetSocketAddress(8000), dataApp);
    }

    public void run() {
        Thread thread = new Thread(() -> {
            outputHandler.println("Введите exit для того, чтобы выйти из " +
                    "программы или save, чтобы сохранить данные в файл.");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals("save")) {
                    dataApp.saveJSONObjectToFile();
                } else if (input.equals("exit")) {
                    dataApp.saveJSONObjectToFile();
                    outputHandler.println("До скорой встречи!");
                    System.exit(0);
                }
            }
        });
        thread.start();
        udpServer.run();
    }
}
