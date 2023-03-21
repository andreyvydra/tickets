package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;

import java.util.ArrayList;

/**
 * RemoveLowerCommand deletes all tickets which are lower than inputted ticket.
 *
 * @see InputTicket
 * @see CollectionManager
 */
public class RemoveLowerCommand extends DataAppCommand {


    public RemoveLowerCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = InputTicket.getTicketFromConsole(dataApp.getCollectionManager());
        int count = 0;
        ArrayList<Ticket> ticketsToDelete = new ArrayList<>();
        for (Ticket ticket1 : dataApp.getCollection()) {
            if (ticket.compareTo(ticket1) > 0) {
                ticketsToDelete.add(ticket1);
                count += 1;
            }
        }
        dataApp.removeTickets(ticketsToDelete);
        outputHandler.println("Было удалено: " + count + " тикетов.");
    }

    @Override
    public void printHelp() {
        outputHandler.println("remove_lower {element} : удалить из коллекции все элементы, меньшие, чем заданный");
    }
}
