package console;

import application.App;
import application.DataApp;
import console.commads.generalCommands.Command;
import core.OutputHandler;
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

    public Console(OutputHandler outputHandler, DataApp dataApp) {
        for (Map.Entry<String, Class<? extends Command>> entry : commandMap.entrySet()) {
            String key = entry.getKey();
            try {
                Class<? extends Command> value = entry.getValue();
                Constructor<?> constructor = value.getConstructors()[0];
                if (constructor.getParameterCount() == 2) {
                    if (constructor.getParameterTypes()[1].equals(DataApp.class)) {
                        commands.put(key, (Command) constructor.newInstance(outputHandler, dataApp));
                    } else if (constructor.getParameterTypes()[1].equals(ArrayList.class)) {
                        commands.put(key, (Command) constructor.newInstance(outputHandler, commandsHistory));
                    } else {
                        commands.put(key, (Command) constructor.newInstance(outputHandler, commands));
                    }
                } else {
                    commands.put(key, (Command) constructor.newInstance(outputHandler));
                }
            } catch (InvocationTargetException e) {
                outputHandler.println("Вызов ошибки внутри конструктора " + key);
            } catch (InstantiationException e) {
                outputHandler.println("Объект не может быть создан " + key);
            } catch (IllegalAccessException e) {
                outputHandler.println("Вызов конструктора запрещён " + key);
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
