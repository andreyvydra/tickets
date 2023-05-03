package console.commads;

import console.commads.generalCommands.Command;
import console.commads.generalCommands.DefaultCommand;
import core.OutputHandler;

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
    public void execute(String command) {
        outputHandler.println("До новой встречи!");
        System.exit(0);
    }

    @Override
    public void description() {
        outputHandler.println("exit : завершить программу (без сохранения в файл)");
    }
}
