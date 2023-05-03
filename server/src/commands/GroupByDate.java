package commands;

import application.DataApp;
import data.Ticket;
import requests.Request;
import responses.GroupByDateResponse;
import responses.Response;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GroupByDate extends Command {
    public GroupByDate(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        ArrayList<String> data = new ArrayList<>();
        Map<ZonedDateTime, List<Ticket>> groups = dataApp.getTicketGroupsByDate();
        if (groups.size() == 0) {
            return new GroupByDateResponse("Групп не найдено!", data.toArray());
        }
        for (Map.Entry<ZonedDateTime, List<Ticket>> entry : groups.entrySet()) {
            ZonedDateTime key = entry.getKey();
            int value = entry.getValue().size();
            data.add("Группа: " + key + ", кол-во: " + value);
        }
        return new GroupByDateResponse("Группы", data.toArray());
    }
}
