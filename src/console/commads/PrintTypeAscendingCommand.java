package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;
import data.Ticket;

/**
 * PrintTypeAscendingCommand prints every ticket's type ascending.
 *
 * @see CollectionCommand
 * @see CollectionManager
 */
public class PrintTypeAscendingCommand extends CollectionCommand {

    public PrintTypeAscendingCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String command) {
        for (Ticket ticket : this.getCollectionManager().getCollection()) {
            System.out.println(ticket.getType());
        }
    }
}
