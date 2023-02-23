package console.commads;

import console.commads.generalCommands.Command;
import console.commads.generalCommands.DefaultCommand;

/**
 * ExitCommand is a command for exiting application
 *
 * @see Command
 */
public class ExitCommand extends DefaultCommand {

    @Override
    public void execute(String command) {
        System.out.println("До новой встречи!");
        System.exit(0);
    }

    @Override
    public void printHelp() {
        System.out.println("exit : завершить программу (без сохранения в файл)");
    }
}
