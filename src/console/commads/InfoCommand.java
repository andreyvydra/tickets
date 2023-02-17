package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;

/**
 * InfoCommand shows info about CollectionManager
 *
 * @see CollectionManager
 * @see CollectionCommand
 */
public class InfoCommand extends CollectionCommand {

    public InfoCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String command) {
        System.out.println(this.getCollectionManager());
    }
}
