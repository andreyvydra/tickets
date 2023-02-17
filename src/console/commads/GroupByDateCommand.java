package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;
import data.Ticket;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

/**
 * GroupByDateCommand group tickets by creationDate.
 *
 * @see Ticket
 * @see CollectionManager
 * @see CollectionCommand
 */
public class GroupByDateCommand extends CollectionCommand {

    public GroupByDateCommand(CollectionManager collectionManager) {
        super(collectionManager);
    }

    @Override
    public void execute(String command) {
        Map<ZonedDateTime, List<Ticket>> groups = this.getCollectionManager().getGroupsByDate();
        for (Map.Entry<ZonedDateTime, List<Ticket>> entry : groups.entrySet()) {
            ZonedDateTime key = entry.getKey();
            int value = entry.getValue().size();
            System.out.println("Группа: " + key + ", кол-во: " + value);
        }
    }
}
