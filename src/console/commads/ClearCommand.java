package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;

/**
 * ClearCommand deletes all tickets from collection.
 *
 * @see CollectionManager
 */
public class ClearCommand extends CollectionCommand {

    public ClearCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String command) {
        this.getCollectionManager().clear();
    }
}
