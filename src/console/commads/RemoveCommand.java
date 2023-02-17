package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;

/**
 * RemoveCommand deletes ticket by id.
 *
 * @see CollectionManager
 * @see CollectionCommand
 */
public class RemoveCommand extends CollectionCommand {

    public RemoveCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[1]);
        try {
            this.getCollectionManager().removeTicket(id);
            System.out.println("Ticket " + id + " успешно удалён!");
        } catch (NullPointerException e) {
            System.out.println("Ticket " + id + " не был найден!");
        }
    }
}
