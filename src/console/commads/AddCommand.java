package console.commads;

import application.App;
import console.commads.generalCommands.InputCollectionCommand;
import data.*;

/**
 * AddCommand adds new item to collection.
 *
 * @see InputCollectionCommand
 * @see App
 * @see core.InputTicket
 */
public class AddCommand extends InputCollectionCommand {

    public AddCommand(App app) {
        super(app);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = this.getInputTicket().getTicketFromConsole();
        this.getCollectionManager().addTicket(ticket);
        System.out.println("Ticket успешно добавлен");
    }
}
