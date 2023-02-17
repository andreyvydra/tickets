package console.commads;

import application.App;
import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

public class AddIfMaxCommand implements Command {
    private CollectionManager collectionManager;
    private InputTicket inputTicket;

    public AddIfMaxCommand(App app) {
        this.inputTicket = new InputTicket(app);
        this.collectionManager = app.getCollectionManager();
    }

    @Override
    public void execute(String command) {
        Ticket ticket = this.inputTicket.getTicketFromConsole();
        if (ticket.compareTo(this.collectionManager.getMaxTicket()) > 0) {
            this.collectionManager.addTicket(ticket);
            System.out.println("Ticket успешно добавлен");
        } else {
            System.out.println("Ticket слишком маленький");
        }
    }
}
