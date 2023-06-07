
import application.ClientApp;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try {
            ClientApp clientApp = new ClientApp();
            clientApp.execute();

        } catch (IOException e) {
            System.out.println("Не удалось запустить клиентское приложение!" +
                    "Вам следует повторить запуск");
        }
    }
}