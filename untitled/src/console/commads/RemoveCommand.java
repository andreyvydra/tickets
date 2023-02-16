package console.commads;

import core.CollectionManager;
import core.InputTicket;

public class RemoveCommand implements Command {

    private CollectionManager collectionManager;

    public RemoveCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[1]);
        try {
            this.collectionManager.removeTicket(id);
            System.out.println("Ticket " + id + " успешно удалён!");
        } catch (NullPointerException e) {
            System.out.println("Ticket " + id + " не был найден!");
        }
    }
}
