package console.commads;

import core.CollectionManager;
import core.FileManager;

public class SaveCommand implements Command {
    private CollectionManager collectionManager;
    private FileManager fileManager;

    public SaveCommand(CollectionManager collectionManager, FileManager fileManager) {
        this.collectionManager = collectionManager;
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String command) {
        this.fileManager.saveJSONObjectToFile(this.collectionManager);
    }
}
