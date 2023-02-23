package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import data.Ticket;

/**
 * PrintTypeAscendingCommand prints every ticket's type ascending.
 *
 * @see CollectionManager
 */
public class PrintTypeAscendingCommand extends DataAppCommand {

    public PrintTypeAscendingCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        for (Ticket ticket : dataApp.getCollection()) {
            System.out.println(ticket.getType());
        }
    }

    @Override
    public void printHelp() {
        System.out.println("print_field_ascending_type : вывести значения поля type" +
                " всех элементов в порядке возрастания");
    }
}
