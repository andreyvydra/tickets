package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.InputTicket;
import core.OutputHandler;
import data.Ticket;

/**
 * AddIfMaxCommand adds inputted element if it's max of collection.
 *
 * @see DataApp
 * @see core.CollectionManager
 */
public class AddIfMaxCommand extends DataAppCommand {


    public AddIfMaxCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        Ticket ticket = InputTicket.getTicketFromConsole(dataApp.getCollectionManager());
        if (ticket.compareTo(dataApp.getMaxTicket()) > 0) {
            dataApp.addTicketToCollection(ticket);
            outputHandler.println("Ticket успешно добавлен");
        } else {
            outputHandler.println("Ticket слишком маленький");
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("add_if_max {element} : добавить новый элемент в коллекцию, " +
                "если его значение превышает значение наибольшего элемента этой коллекции");
    }
}
