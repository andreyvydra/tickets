package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;
import data.Ticket;
import data.TicketType;

/**
 * GreaterThenTypeCommand shows tickets, which have larger
 * TicketType than inputted type.
 *
 * @see Ticket
 * @see CollectionManager
 * @see CollectionCommand
 */
public class GreaterThenTypeCommand extends CollectionCommand {

    public GreaterThenTypeCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String command) {
        try {
            TicketType type = TicketType.valueOf(command.split(" ")[1]);
            int count = 0;
            for (Ticket ticket : this.getCollectionManager().getCollection()) {
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
