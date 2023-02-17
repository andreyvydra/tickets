package console.commads;

import application.App;
import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

import java.util.ArrayList;

public class RemoveLowerCommand implements Command {
    private CollectionManager collectionManager;
    private InputTicket inputTicket;

    public RemoveLowerCommand(App app) {
        this.inputTicket = new InputTicket(app);
        this.collectionManager = app.getCollectionManager();
    }

    @Override
    public void execute(String command) {
        Ticket ticket = this.inputTicket.getTicketFromConsole();
        int count = 0;
        ArrayList<Ticket> ticketsToDelete = new ArrayList<>();
        for (Ticket ticket1 : this.collectionManager.getCollection()) {
            if (ticket.compareTo(ticket1) > 0) {
                ticketsToDelete.add(ticket1);
                count += 1;
            }
        }
        this.collectionManager.removeTickets(ticketsToDelete);
        System.out.println("Было удалено: " + count + " тикетов.");
    }
}
