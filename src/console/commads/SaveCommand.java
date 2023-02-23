package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.FileManager;

/**
 * Class SaveCommand extends Collection command, and
 * it's main func is saving data. It invokes method from FileManager,
 * which translate data to json.
 *
 * @see FileManager
 */
public class SaveCommand extends DataAppCommand {

    public SaveCommand(DataApp dataApp) {
        super(dataApp);
    }

    @Override
    public void execute(String command) {
        dataApp.saveJSONObjectToFile();
    }

    @Override
    public void printHelp() {
        System.out.println("save : сохранить коллекцию в файл");
    }
}
