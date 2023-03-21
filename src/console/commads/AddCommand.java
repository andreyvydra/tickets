package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.InputTicket;
import core.OutputHandler;
import data.*;

/**
 * AddCommand adds new item to collection.
 *
 * @see DataApp
 * @see core.InputTicket
 */
public class AddCommand extends DataAppCommand {

    public AddCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = InputTicket.getTicketFromConsole(dataApp.getCollectionManager());
        dataApp.addTicketToCollection(ticket);
        outputHandler.println("Ticket успешно добавлен");
    }

    public void printHelp() {
        outputHandler.println("add {element} : добавить новый элемент в коллекцию");
    }
}
