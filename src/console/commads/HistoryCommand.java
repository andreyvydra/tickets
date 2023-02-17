package console.commads;

import console.commads.generalCommands.Command;

import java.util.ArrayList;

/**
 * HistoryCommand shows last 8 commands, which was inputted from console
 *
 * @see console.Console
 * @see Command
 */
public class HistoryCommand implements Command {
    private ArrayList<String> commandsHistory;

    public HistoryCommand(ArrayList<String> commandsHistory) {
        this.commandsHistory = commandsHistory;
    }

    @Override
    public void execute(String command) {
        System.out.println(this.commandsHistory.toString());
    }
}
