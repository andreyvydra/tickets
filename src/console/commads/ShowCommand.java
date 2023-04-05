package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.OutputHandler;
import data.Ticket;

/**
 * This class shows full info about every ticket at collection.
 *
 * @see CollectionManager
 */
public class ShowCommand extends DataAppCommand {


    public ShowCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        if (dataApp.getCollectionLen() == 0) {
            outputHandler.println("Коллекция пуста!");
        } else {
            outputHandler.println("Collection items:");
            for (Ticket ticket : dataApp.getCollection()) {
                outputHandler.println(ticket);
            }
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("show : вывести в стандартный поток вывода все элементы" +
                " коллекции в строковом представлении");
    }
}
