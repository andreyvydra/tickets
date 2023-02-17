package console.commads;

import core.CollectionManager;
import data.Ticket;

public class ShowCommand implements Command{
    private CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String command) {
        System.out.println("Collection items:");
        for (Ticket ticket : this.collectionManager.getCollection()) {
            System.out.println(ticket);
        }
    }
}
