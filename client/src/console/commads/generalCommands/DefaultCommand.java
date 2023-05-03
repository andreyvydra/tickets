package console.commads.generalCommands;

import core.OutputHandler;

abstract public class DefaultCommand implements Command {
    protected OutputHandler outputHandler;

    public DefaultCommand(OutputHandler outputHandler) {
        this.outputHandler = outputHandler;
    }
    abstract public void description();
}
