package console.commads.generalCommands;

import core.CollectionManager;

/**
 * CollectionCommand extends by classes which collaborate with collection
 *
 * @see Command
 * @see CollectionManager
 */
abstract public class CollectionCommand implements Command {
    private CollectionManager collectionManager;

    public CollectionCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public CollectionManager getCollectionManager() {
        return this.collectionManager;
    }
}
