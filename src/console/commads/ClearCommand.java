package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.OutputHandler;

/**
 * ClearCommand deletes all tickets from collection.
 *
 * @see CollectionManager
 */
public class ClearCommand extends DataAppCommand {


    public ClearCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        this.dataApp.clearCollectionManger();
        outputHandler.println("Коллекция быда очищена!");
    }

    @Override
    public void printHelp() {
        outputHandler.println("clear : очистить коллекцию");
    }
}
