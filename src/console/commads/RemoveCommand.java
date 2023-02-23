package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;

import java.util.Objects;

import static core.Globals.ARGUMENT_POSITION;

/**
 * RemoveCommand deletes ticket by id.
 *
 * @see CollectionManager
 */
public class RemoveCommand extends DataAppCommand {

    public RemoveCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[ARGUMENT_POSITION]);
        try {
            dataApp.removeTicketById(id);
            if (Objects.isNull(dataApp.getTicketById(id))) {
                System.out.println("Ticket " + id + " успешно удалён!");
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            System.out.println("Ticket " + id + " не был найден!");
        }
    }

    @Override
    public void printHelp() {
        System.out.println("remove_by_id id : удалить элемент из коллекции по его id");
    }
}
