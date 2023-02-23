package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;

/**
 * InfoCommand shows info about CollectionManager
 *
 * @see CollectionManager
 */
public class InfoCommand extends DataAppCommand {

    public InfoCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        System.out.println(dataApp.getCollectionManager());
    }

    @Override
    public void printHelp() {
        System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
    }
}
