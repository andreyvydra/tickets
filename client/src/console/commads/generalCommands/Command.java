package console.commads.generalCommands;

/**
 * Command interface with one method execute. Every abstract command class
 * is implements it.
 */
public interface Command {
    void execute(String command);
}
