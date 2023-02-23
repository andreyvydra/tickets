package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;

/**
 * ClearCommand deletes all tickets from collection.
 *
 * @see CollectionManager
 */
public class ClearCommand extends DataAppCommand {

    public ClearCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        this.dataApp.clearCollectionManger();
    }

    @Override
    public void printHelp() {
        System.out.println("clear : очистить коллекцию");
    }
}
