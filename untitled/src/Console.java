import console.commads.Command;
import console.commads.HelpCommand;
import console.commads.InfoCommand;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class Console {
    public HashMap<String, Command> commands = new HashMap<>();

    public Console(App app) {
        this.commands.put("help", new HelpCommand());
        this.commands.put("info", new InfoCommand(app.getCollectionManager()));
    }

    public void execute(String command) throws InvocationTargetException, IllegalAccessException, NullPointerException {
        this.commands.get(command).execute(command);
    }
}
