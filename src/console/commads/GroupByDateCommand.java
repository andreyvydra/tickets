package console.commads;

import core.CollectionManager;
import data.Ticket;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupByDateCommand implements Command {
    private CollectionManager collectionManager;

    public GroupByDateCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String command) {
        Map<ZonedDateTime, List<Ticket>> groups = this.collectionManager.getGroupsByDate();
        for (Map.Entry<ZonedDateTime, List<Ticket>> entry : groups.entrySet()) {
            ZonedDateTime key = entry.getKey();
            int value = entry.getValue().size();
            System.out.println("Группа: " + key + ", кол-во: " + value);
        }
    }
}
