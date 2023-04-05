package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.FileManager;
import core.OutputHandler;

/**
 * Class SaveCommand extends Collection command, and
 * it's main func is saving data. It invokes method from FileManager,
 * which translate data to json.
 *
 * @see FileManager
 */
public class SaveCommand extends DataAppCommand {


    public SaveCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        dataApp.saveJSONObjectToFile();
    }

    @Override
    public void printHelp() {
        outputHandler.println("save : сохранить коллекцию в файл");
    }
}
