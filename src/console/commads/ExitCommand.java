package console.commads;

import console.commads.generalCommands.Command;

/**
 * ExitCommand is a command for exiting application
 *
 * @see Command
 */
public class ExitCommand implements Command {

    @Override
    public void execute(String command) {
        System.out.println("До новой встречи!");
        System.exit(0);
    }
}
