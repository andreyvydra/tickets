package console.commads;

import console.commads.generalCommands.Command;
import console.commads.generalCommands.DefaultCommand;
import core.OutputHandler;

import java.util.HashMap;
import java.util.Objects;

import static core.Globals.Network.USERNAME;

/**
 * ExitCommand is a command for exiting application
 *
 * @see Command
 */
public class ExitCommand extends DefaultCommand {

    public ExitCommand(OutputHandler outputHandler) {
        super(outputHandler);
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        if (!Objects.isNull(user.get(USERNAME))) {
            outputHandler.println("До новой встречи, " + user.get(USERNAME) + "!");
            System.exit(0);
        }
        outputHandler.println("До новой встречи!");
        System.exit(0);
    }

    @Override
    public void description() {
        outputHandler.println("exit : завершить программу (без сохранения в файл)");
    }
}
