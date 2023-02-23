package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.InputTicket;
import data.Ticket;

/**
 * AddIfMaxCommand adds inputted element if it's max of collection.
 *
 * @see DataApp
 * @see core.CollectionManager
 */
public class AddIfMaxCommand extends DataAppCommand {

    public AddIfMaxCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = InputTicket.getTicketFromConsole(dataApp.getCollectionManager());
        if (ticket.compareTo(dataApp.getMaxTicket()) > 0) {
            dataApp.addTicketToCollection(ticket);
            System.out.println("Ticket успешно добавлен");
        } else {
            System.out.println("Ticket слишком маленький");
        }
    }

    @Override
    public void printHelp() {
        System.out.println("add_if_max {element} : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
    }
}
