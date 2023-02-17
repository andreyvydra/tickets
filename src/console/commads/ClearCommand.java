package console.commads;

import core.CollectionManager;

public class ClearCommand implements Command {

    private CollectionManager collectionManager;

    public ClearCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String command) {
        this.collectionManager.clear();
    }
}
