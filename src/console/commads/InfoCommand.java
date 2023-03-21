package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.OutputHandler;

/**
 * InfoCommand shows info about CollectionManager
 *
 * @see CollectionManager
 */
public class InfoCommand extends DataAppCommand {

    public InfoCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        outputHandler.println(dataApp.getCollectionManager());
    }

    @Override
    public void printHelp() {
        outputHandler.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }
}
