package application;

import core.OutputHandler;
import db.ConnectionDB;
import db.DBManager;
import network.UDPServer;

import java.net.InetSocketAddress;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.Scanner;

import static core.Globals.CommandNames.EXIT;
import static core.Globals.SERVER_PORT;

public class ServerApp {
    private final UDPServer udpServer;
    private final DataApp dataApp;
    private final OutputHandler outputHandler = new OutputHandler();

    public ServerApp() throws SocketException, SQLException {
        DBManager dbManager = new DBManager(ConnectionDB.getConnection(), outputHandler);
        dataApp = new DataApp(dbManager);
        udpServer = new UDPServer(new InetSocketAddress(SERVER_PORT), dataApp);
    }

    public void run() {
        Thread threadConsole = new Thread(() -> {
            outputHandler.println("Введите exit для того, чтобы выйти из " +
                    "программы.");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine().trim().toLowerCase();
                if (input.equals(EXIT)) {
                    outputHandler.println("До скорой встречи!");
                    udpServer.stop();
                    break;
                }
            }
        });
        threadConsole.start();
        udpServer.run();
    }
}
