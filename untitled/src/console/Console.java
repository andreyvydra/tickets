package console;

import application.App;
import console.commads.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class Console {
    private HashMap<String, Command> commands = new HashMap<>();
    private ArrayList<String> commandsHistory = new ArrayList<>(8);

    public Console(App app) {
        this.commands.put("help", new HelpCommand());
        this.commands.put("info", new InfoCommand(app.getCollectionManager()));
        this.commands.put("show", new ShowCommand(app.getCollectionManager()));
        this.commands.put("add", new AddCommand(app.getCollectionManager()));
        this.commands.put("update", new UpdateCommand(app.getCollectionManager()));
        this.commands.put("remove_by_id", new RemoveCommand(app.getCollectionManager()));
        this.commands.put("clear", new ClearCommand(app.getCollectionManager()));
        this.commands.put("save", new SaveCommand(app.getCollectionManager(), app.getFileManager()));
        this.commands.put("exit", new ExitCommand());
        this.commands.put("history", new HistoryCommand(this.commandsHistory));
    }

    public void execute(String command) throws InvocationTargetException, IllegalAccessException, NullPointerException {
        this.commands.get(command.split(" ")[0]).execute(command);
        if (commandsHistory.size() == 8) {
            this.commandsHistory.remove(0);
        }
        this.commandsHistory.add(command);
    }
}
