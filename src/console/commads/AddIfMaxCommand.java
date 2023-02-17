package console.commads;

import application.App;
import console.commads.generalCommands.InputCollectionCommand;
import data.Ticket;

/**
 * AddIfMaxCommand adds inputted element if it's max of collection.
 *
 * @see InputCollectionCommand
 * @see App
 * @see core.CollectionManager
 */
public class AddIfMaxCommand extends InputCollectionCommand {

    public AddIfMaxCommand(App app) {
        super(app);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = this.getInputTicket().getTicketFromConsole();
        if (ticket.compareTo(this.getCollectionManager().getMaxTicket()) > 0) {
            this.getCollectionManager().addTicket(ticket);
            System.out.println("Ticket успешно добавлен");
        } else {
            System.out.println("Ticket слишком маленький");
        }
    }
}
