package console.commads;

import console.commads.generalCommands.Command;
import console.commads.generalCommands.DefaultCommand;
import core.OutputHandler;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * HistoryCommand shows last 8 commands, which was inputted from console
 *
 * @see console.Console
 * @see Command
 */
public class HistoryCommand extends DefaultCommand {
    private final ArrayList<String> commandsHistory;

    public HistoryCommand(OutputHandler outputHandler, ArrayList<String> commandsHistory) {
        super(outputHandler);
        this.commandsHistory = commandsHistory;
    }

    @Override
    public void execute(String command, HashMap<String, String> user) {
        outputHandler.println(this.commandsHistory.toString());
    }

    @Override
    public void description() {
        outputHandler.println("history : вывести последние 8 команд (без их аргументов)");
    }
}
