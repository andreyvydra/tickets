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
        commands = commandHashMap;
    }

    public Command getCommand(String commandName) {
        return commands.get(commandName);
    }

}
