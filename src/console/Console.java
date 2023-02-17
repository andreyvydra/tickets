package console;

import application.App;
import console.commads.*;
import console.commads.generalCommands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Console executes every command and collect story of commands
 *
 * @see App
 */
public class Console {
    private HashMap<String, Command> commands = new HashMap<>();
    private ArrayList<String> commandsHistory = new ArrayList<>(8);

    public Console(App app) {
        this.commands.put("help", new HelpCommand());
        this.commands.put("info", new InfoCommand(app.getCollectionManager()));
        this.commands.put("show", new ShowCommand(app.getCollectionManager()));
        this.commands.put("add", new AddCommand(app));
        this.commands.put("update", new UpdateCommand(app));
        this.commands.put("remove_by_id", new RemoveCommand(app.getCollectionManager()));
        this.commands.put("clear", new ClearCommand(app.getCollectionManager()));
        this.commands.put("save", new SaveCommand(app.getCollectionManager(), app.getFileManager()));
        this.commands.put("execute_script", new ExecuteScriptCommand(app));
        this.commands.put("add_if_max", new AddIfMaxCommand(app));
        this.commands.put("remove_lower", new RemoveLowerCommand(app));
        this.commands.put("exit", new ExitCommand());
        this.commands.put("history", new HistoryCommand(this.commandsHistory));
        this.commands.put("group_counting_by_creation_date", new GroupByDateCommand(app.getCollectionManager()));
        this.commands.put("count_greater_than_type", new GreaterThenTypeCommand(app.getCollectionManager()));
        this.commands.put("print_field_ascending_type", new PrintTypeAscendingCommand(app.getCollectionManager()));
    }

    public void execute(String command) throws InvocationTargetException, IllegalAccessException, NullPointerException {
        this.commands.get(command.split(" ")[0]).execute(command);
        if (commandsHistory.size() == 8) {
            this.commandsHistory.remove(0);
        }
        this.commandsHistory.add(command);
    }
}
