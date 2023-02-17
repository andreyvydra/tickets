package console.commads;

import core.CollectionManager;
import data.Ticket;

import java.util.Collections;
import java.util.stream.Collectors;

public class PrintTypeAscendingCommand implements Command {
    private CollectionManager collectionManager;

    public PrintTypeAscendingCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String command) {
        for (Ticket ticket : this.collectionManager.getCollection()) {
            System.out.println(ticket.getType());
        }
    }
}
