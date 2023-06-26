package console;

import application.ClientApp;
import console.commads.*;
import console.commads.InfoCommand;
import console.commads.generalCommands.Command;
import core.OutputHandler;
import core.exceptions.CommandWasNotFound;
import network.UDPClient;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

import static core.Globals.COMMAND_POSITION;
import static core.Globals.CommandNames.*;
import static core.Globals.HISTORY_SIZE;

/**
 * Console executes every command and collect story of commands
 *
 * @see ClientApp
 */
public class Console {
    private final HashMap<String, Command> commands = new HashMap<>();
    private final ArrayList<String> commandsHistory = new ArrayList<>(8);

    private final OutputHandler outputHandler;

    public static Map<String, Class<? extends Command>> commandMap = new HashMap<>();
    static {
        commandMap.put(HELP, HelpCommand.class);
        commandMap.put(INFO, InfoCommand.class);
        commandMap.put(SHOW, ShowCommand.class);
        commandMap.put(ADD, AddCommand.class);
        commandMap.put(UPDATE, UpdateCommand.class);
        commandMap.put(REMOVE_BY_ID, RemoveCommand.class);
        commandMap.put(CLEAR, ClearCommand.class);
        commandMap.put(EXECUTE_SCRIPT, ExecuteScriptCommand.class);
        commandMap.put(ADD_IF_MAX, AddIfMaxCommand.class);
        commandMap.put(REMOVE_LOWER, RemoveLowerCommand.class);
        commandMap.put(EXIT, ExitCommand.class);
        commandMap.put(HISTORY, HistoryCommand.class);
        commandMap.put(GROUP_COUNTING_BY_CREATION_DATE, GroupByDateCommand.class);
        commandMap.put(COUNT_GREATER_THAN_TYPE, GreaterThenTypeCommand.class);
        commandMap.put(PRINT_FIELD_ASCENDING_TYPE, PrintTypeAscendingCommand.class);
        commandMap.put(LOGIN, LoginCommand.class);
        commandMap.put(REGISTER, RegisterCommand.class);
        commandMap.put(LOGOUT, LogoutCommand.class);
    }

    public Console(OutputHandler outputHandler, UDPClient udpClient, Queue<String> commandBuffer) {
        this.outputHandler = outputHandler;
        for (Map.Entry<String, Class<? extends Command>> entry : commandMap.entrySet()) {
            String key = entry.getKey();
            try {
                Class<? extends Command> value = entry.getValue();
                Constructor<?> constructor = value.getConstructors()[0];
                if (constructor.getParameterCount() == 2) {
                    if (constructor.getParameterTypes()[1].equals(UDPClient.class)) {
                        commands.put(key, (Command) constructor.newInstance(outputHandler, udpClient));
                    } else if (constructor.getParameterTypes()[1].equals(ArrayList.class)) {
                        commands.put(key, (Command) constructor.newInstance(outputHandler, commandsHistory));
                    } else if (constructor.getParameterTypes()[1].equals(Queue.class)) {
                        commands.put(key, (Command) constructor.newInstance(outputHandler, commandBuffer));
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

    public void execute(String command, HashMap<String, String> user) throws CommandWasNotFound {
        String commandName = command.split(" ")[COMMAND_POSITION];
        if (!commandMap.containsKey(commandName)) {
            throw new CommandWasNotFound();
        }

        try {
            commands.get(commandName).execute(command, user);
        } catch (IOException e) {
            outputHandler.println("Ошибка при передачи данных! " + e);
        } catch (ClassNotFoundException e) {
            outputHandler.println("Класс не был найден! " + e);
        }

        addToHistory(command);
    }

    public void addToHistory(String command) {
        if (commandsHistory.size() == HISTORY_SIZE) {
            commandsHistory.remove(0);
        }
        commandsHistory.add(command);
    }
}
