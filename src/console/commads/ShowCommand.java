package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;
import data.Ticket;

/**
 * This class shows full info about every ticket at collection.
 *
 * @see CollectionCommand
 * @see CollectionManager
 */
public class ShowCommand extends CollectionCommand {


    public ShowCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String command) {
        System.out.println("Collection items:");
        for (Ticket ticket : this.getCollectionManager().getCollection()) {
            System.out.println(ticket);
        }
    }
}
