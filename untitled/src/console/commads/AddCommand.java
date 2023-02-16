package console.commads;

import core.CollectionManager;
import core.InputTicket;
import data.*;

public class AddCommand implements Command {
    private InputTicket inputTicket;
    private CollectionManager collectionManager;

    public AddCommand(CollectionManager collectionManager) {
        this.inputTicket = new InputTicket(collectionManager);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String command) {
        Ticket ticket = this.inputTicket.getTicketFromConsole();
        this.collectionManager.addTicket(ticket);
        System.out.println("Ticket успешно добавлен");
    }
}
