package console.commads;

import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

public class AddIfMaxCommand implements Command {
    private CollectionManager collectionManager;

    public AddIfMaxCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String command) {
        InputTicket inputTicket = new InputTicket(this.collectionManager);
        Ticket ticket = inputTicket.getTicketFromConsole();
        if (ticket.compareTo(this.collectionManager.getMaxTicket()) > 0) {
            this.collectionManager.addTicket(ticket);
            System.out.println("Ticket успешно добавлен");
        } else {
            System.out.println("Ticket слишком маленький");
        }
    }
}
