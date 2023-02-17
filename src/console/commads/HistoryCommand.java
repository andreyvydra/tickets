package console.commads;

import console.Console;

import java.util.ArrayList;

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
