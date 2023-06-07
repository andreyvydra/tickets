import application.ServerApp;

import java.net.SocketException;
import java.sql.SQLException;


public class Server {
    public static void main(String[] args) throws SQLException {
        try {
            ServerApp serverApp = new ServerApp();
            serverApp.run();
        } catch (SocketException e) {
            System.out.println("Сервер не был запущен. Нужно изменить" +
                    " номер порта сервера в Globals");
        }
    }
}