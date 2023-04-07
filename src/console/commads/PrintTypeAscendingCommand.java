package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.OutputHandler;
import data.Ticket;

/**
 * PrintTypeAscendingCommand prints every ticket's type ascending.
 *
 * @see CollectionManager
 */
public class PrintTypeAscendingCommand extends DataAppCommand {


    public PrintTypeAscendingCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        for (Ticket ticket : dataApp.getCollection()) {
            outputHandler.println(ticket.getType());
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("print_field_ascending_type : вывести значения поля type" +
                " всех элементов в порядке возрастания");
    }
}
