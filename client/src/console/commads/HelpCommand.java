package console.commads;

import console.commads.generalCommands.Command;
import console.commads.generalCommands.DefaultCommand;
import core.OutputHandler;

import java.util.HashMap;

/**
 * Help command prints info about commands
 *
 * @see Command
 * @see console.Console
 */
public class HelpCommand extends DefaultCommand {
    private final HashMap<String, Command> commands;

    public HelpCommand(OutputHandler outputHandler, HashMap<String, Command> commands) {
        super(outputHandler);
        this.commands = commands;
    }

    @Override
    public void execute(String command) {
        for (Command command1 : commands.values()) {
            ((DefaultCommand) command1).description();
        }
    }

    @Override
    public void description() {
        outputHandler.println("help : вывести справку по доступным командам");
    }
}
