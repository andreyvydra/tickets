package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import data.Ticket;

/**
 * This class shows full info about every ticket at collection.
 *
 * @see CollectionManager
 */
public class ShowCommand extends DataAppCommand {


    public ShowCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        System.out.println("Collection items:");
        for (Ticket ticket : dataApp.getCollection()) {
            System.out.println(ticket);
        }
    }

    @Override
    public void printHelp() {
        System.out.println("show : вывести в стандартный поток вывода все элементы" +
                " коллекции в строковом представлении");
    }
}
