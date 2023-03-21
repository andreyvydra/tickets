package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.OutputHandler;

import java.util.Objects;

import static core.Globals.ARGUMENT_POSITION;

/**
 * RemoveCommand deletes ticket by id.
 *
 * @see CollectionManager
 */
public class RemoveCommand extends DataAppCommand {


    public RemoveCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[ARGUMENT_POSITION]);
        dataApp.removeTicketById(id);
        if (Objects.isNull(dataApp.getTicketById(id))) {
            outputHandler.println("Ticket " + id + " успешно удалён!");
        } else {
            outputHandler.println("Ticket " + id + " не был найден!");
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("remove_by_id id : удалить элемент из коллекции по его id");
    }
}
