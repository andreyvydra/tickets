package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.OutputHandler;
import data.Ticket;
import data.TicketType;

import static core.Globals.ARGUMENT_POSITION;

/**
 * GreaterThenTypeCommand shows tickets, which have larger
 * TicketType than inputted type.
 *
 * @see Ticket
 * @see CollectionManager
 */
public class GreaterThenTypeCommand extends DataAppCommand {


    public GreaterThenTypeCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        try {
            TicketType type = TicketType.valueOf(command.split(" ")[ARGUMENT_POSITION]);
            int count = 0;
            for (Ticket ticket : dataApp.getCollectionManager().getCollection()) {
                if (ticket.getType().compareTo(type) > 0) {
                    count += 1;
                }
            }
            outputHandler.println("Кол-во элементов, у которых type больше: " + count);
        } catch (ArrayIndexOutOfBoundsException e) {
            outputHandler.println("Аргумен не был введён!");
        } catch (IllegalArgumentException e) {
            outputHandler.println("Такого type не существует!");
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("count_greater_than_type type : вывести количество элементов, значение" +
                " поля type которых больше заданного");
    }
}
