package console;

import application.App;
import application.DataApp;
import console.commads.generalCommands.Command;
import core.exceptions.CommandWasNotFound;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static core.Globals.COMMAND_POSITION;
import static core.Globals.CommandNames.commandMap;
import static core.Globals.HISTORY_SIZE;

/**
 * Console executes every command and collect story of commands
 *
 * @see App
 */
public class Console {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final ArrayList<String> commandsHistory = new ArrayList<>(8);

    public Console(DataApp dataApp) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        for (Map.Entry<String, Class<? extends Command>> entry : commandMap.entrySet()) {
            String key = entry.getKey();
            Class<? extends Command> value = entry.getValue();
            Constructor<?> constructor = value.getConstructors()[0];
            if (constructor.getParameterCount() == 1) {
                if (constructor.getParameterTypes()[0].equals(DataApp.class)) {
                    commands.put(key, (Command) constructor.newInstance(dataApp));
                } else if (constructor.getParameterTypes()[0].equals(ArrayList.class)) {
                    commands.put(key, (Command) constructor.newInstance(commandsHistory));
                } else {
                    commands.put(key, (Command) constructor.newInstance(commands));
                }
            } else {
                commands.put(key, (Command) constructor.newInstance());
            }
        }
    }

    public void execute(String command) throws InvocationTargetException, IllegalAccessException, NullPointerException {
        String commandName = command.split(" ")[COMMAND_POSITION];
        if (!commandMap.containsKey(commandName)) {
            throw new CommandWasNotFound();
        }
        commands.get(commandName).execute(command);
        if (commandsHistory.size() == HISTORY_SIZE) {
            commandsHistory.remove(0);
        }
        commandsHistory.add(command);
    }
}
