package console.commads;

import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

import java.util.ArrayList;

public class RemoveLowerCommand implements Command {
    private CollectionManager collectionManager;

    public RemoveLowerCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String command) {
        InputTicket inputTicket = new InputTicket(this.collectionManager);
        Ticket ticket = inputTicket.getTicketFromConsole();
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
