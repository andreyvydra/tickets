package console.commads;

import application.App;
import console.commads.generalCommands.InputCollectionCommand;
import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

import java.util.ArrayList;

/**
 * RemoveLowerCommand deletes all tickets which are lower than inputted ticket.
 *
 * @see InputCollectionCommand
 * @see InputTicket
 * @see App
 * @see CollectionManager
 */
public class RemoveLowerCommand extends InputCollectionCommand {

    public RemoveLowerCommand(App app) {
        super(app);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = this.getInputTicket().getTicketFromConsole();
        int count = 0;
        ArrayList<Ticket> ticketsToDelete = new ArrayList<>();
        for (Ticket ticket1 : this.getCollectionManager().getCollection()) {
            if (ticket.compareTo(ticket1) > 0) {
                ticketsToDelete.add(ticket1);
                count += 1;
            }
        }
        this.getCollectionManager().removeTickets(ticketsToDelete);
        System.out.println("Было удалено: " + count + " тикетов.");
    }
}
