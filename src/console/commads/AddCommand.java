package console.commads;

import application.App;
import core.CollectionManager;
import core.InputTicket;
import data.*;

import java.util.Collections;

public class AddCommand implements Command {
    private InputTicket inputTicket;
    private CollectionManager collectionManager;

    public AddCommand(App app) {
        this.inputTicket = new InputTicket(app);
        this.collectionManager = app.getCollectionManager();
    }

    @Override
    public void execute(String command) {
        Ticket ticket = this.inputTicket.getTicketFromConsole();
        this.collectionManager.addTicket(ticket);
        System.out.println("Ticket успешно добавлен");
    }
}
