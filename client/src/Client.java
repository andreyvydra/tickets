
import application.ClientApp;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            ClientApp clientApp = new ClientApp();
            clientApp.start(stage);
        } catch (IOException e) {
            System.out.println("Не удалось запустить клиентское приложение!" +
                    "Вам следует повторить запуск");
        }
    }

}