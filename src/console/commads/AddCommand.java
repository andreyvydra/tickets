package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.InputTicket;
import data.*;

/**
 * AddCommand adds new item to collection.
 *
 * @see DataApp
 * @see core.InputTicket
 */
public class AddCommand extends DataAppCommand {


    public AddCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = InputTicket.getTicketFromConsole(dataApp.getCollectionManager());
        dataApp.addTicketToCollection(ticket);
        System.out.println("Ticket успешно добавлен");
    }

    public void printHelp() {
        System.out.println("add {element} : добавить новый элемент в коллекцию");
    }
}
