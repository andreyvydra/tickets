import application.ServerApp;

import java.net.SocketException;
import java.sql.SQLException;


public class Server {
    public static void main(String[] args) throws SQLException {
        if (args.length == 0) {
            System.out.println("Передайте имя файла в командной строке!");
        } else {
            try {
                ServerApp serverApp = new ServerApp();
                serverApp.run();
            } catch (SocketException e) {
                System.out.println("Сервер не был запущен. Нужно изменить" +
                        " номер порта сервера в Globals");
            }

        }
    }
}