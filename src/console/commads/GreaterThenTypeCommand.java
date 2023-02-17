package console.commads;

import core.CollectionManager;
import data.Ticket;
import data.TicketType;

public class GreaterThenTypeCommand implements Command {
    private CollectionManager collectionManager;

    public GreaterThenTypeCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String command) {
        try {
            TicketType type = TicketType.valueOf(command.split(" ")[1]);
            int count = 0;
            for (Ticket ticket : this.collectionManager.getCollection()) {
                if (ticket.getType().compareTo(type) > 0) {
                    count += 1;
                }
            }
            System.out.println("Кол-во элементов, у которых type больше: " + count);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Аргумен не был введён!");
        } catch (IllegalArgumentException e) {
            System.out.println("Такого type не существует!");
        }
    }
}
