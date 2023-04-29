import application.ServerApp;

import java.net.SocketException;

import static core.Globals.FILENAME_POSITION;

public class Server {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Передайте имя файла!");
        } else {
            try {
                ServerApp serverApp = new ServerApp(args[FILENAME_POSITION]);
                serverApp.run();
            } catch (SocketException e) {
                System.out.println("Данный сокет не может быть создан, существует конфликт");
            }

        }
    }
}