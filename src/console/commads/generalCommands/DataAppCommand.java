package console.commads.generalCommands;

import application.DataApp;

abstract public class DataAppCommand extends DefaultCommand {
    protected DataApp dataApp;

    public DataAppCommand(DataApp dataApp) {
        this.dataApp = dataApp;
    }
}
