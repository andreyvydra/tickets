package managers;

import application.DataApp;
import commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static core.Globals.CommandNames.*;

public class CommandManager {
    public Map<String, ? extends Command> commands;

    public CommandManager(Logger logger, DataApp dataApp) {
        HashMap<String, Command> commandHashMap = new HashMap<>();
        commandHashMap.put(ADD, new Add(logger, dataApp));
        commandHashMap.put(ADD_IF_MAX, new AddIfMax(logger, dataApp));
        commandHashMap.put(INFO, new Info(logger, dataApp));
        commandHashMap.put(SHOW, new Show(logger, dataApp));
        commandHashMap.put(CLEAR, new Clear(logger, dataApp));
        commandHashMap.put(COUNT_GREATER_THAN_TYPE, new GreaterThanType(logger, dataApp));
        commandHashMap.put(GROUP_COUNTING_BY_CREATION_DATE, new GroupByDate(logger, dataApp));
        commandHashMap.put(PRINT_FIELD_ASCENDING_TYPE, new PrintTypeAscending(logger, dataApp));
        commandHashMap.put(REMOVE_BY_ID, new Remove(logger, dataApp));
        commandHashMap.put(REMOVE_LOWER, new RemoveLower(logger, dataApp));
        commandHashMap.put(UPDATE, new Update(logger, dataApp));
        commandHashMap.put(TICKET_EXIST, new TicketExist(logger, dataApp));
        commandHashMap.put(REGISTER, new Register(logger, dataApp));
        commands = commandHashMap;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
