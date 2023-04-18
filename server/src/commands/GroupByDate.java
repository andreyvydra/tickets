package commands;

import application.DataApp;
import data.Ticket;
import requests.Request;
import responses.GroupByDateResponse;
import responses.Response;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class GroupByDate extends Command {
    public GroupByDate(Logger logger, DataApp dataApp) {
        super(logger, dataApp);
    }

    @Override
    public Response execute(Request request) {
        StringBuilder sb = new StringBuilder();
        Map<ZonedDateTime, List<Ticket>> groups = dataApp.getTicketGroupsByDate();
        if (groups.size() == 0) {
            return new GroupByDateResponse("Групп не найдено!");
        }
        for (Map.Entry<ZonedDateTime, List<Ticket>> entry : groups.entrySet()) {
            ZonedDateTime key = entry.getKey();
            int value = entry.getValue().size();
            sb.append("Группа: ").append(key).append(", кол-во: ").append(value).append("\n");
        }
        return new GroupByDateResponse(sb.toString());
    }
}
