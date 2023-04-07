package console.commads.generalCommands;

import application.DataApp;
import core.OutputHandler;

abstract public class DataAppCommand extends DefaultCommand {
    protected DataApp dataApp;

    public DataAppCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler);
        this.dataApp = dataApp;
    }
}
