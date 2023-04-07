package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.OutputHandler;
import data.Ticket;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

/**
 * GroupByDateCommand group tickets by creationDate.
 *
 * @see Ticket
 * @see CollectionManager
 */
public class GroupByDateCommand extends DataAppCommand {


    public GroupByDateCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        Map<ZonedDateTime, List<Ticket>> groups = dataApp.getTicketGroupsByDate();
        for (Map.Entry<ZonedDateTime, List<Ticket>> entry : groups.entrySet()) {
            ZonedDateTime key = entry.getKey();
            int value = entry.getValue().size();
            outputHandler.println("Группа: " + key + ", кол-во: " + value);
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("group_counting_by_creation_date : сгруппировать элементы коллекции по" +
                " значению поля creationDate, вывести количество элементов в каждой группе");
    }
}
