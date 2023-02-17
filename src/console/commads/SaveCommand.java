package console.commads;

import console.commads.generalCommands.CollectionCommand;
import core.CollectionManager;
import core.FileManager;

/**
 * Class SaveCommand extends Collection command, and
 * it's main func is saving data. It invokes method from FileManager,
 * which translate data to json.
 *
 * @see FileManager
 * @see CollectionCommand
 */
public class SaveCommand extends CollectionCommand {
    private FileManager fileManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        super(collectionManager);
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String command) {
        this.fileManager.saveJSONObjectToFile(this.getCollectionManager());
    }
}
