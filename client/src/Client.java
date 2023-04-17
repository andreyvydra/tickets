
import application.ClientApp;

import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException {
        ClientApp clientApp = new ClientApp();
        clientApp.execute();
    }
}