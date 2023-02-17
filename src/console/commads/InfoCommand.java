package console.commads;

import core.CollectionManager;

public class InfoCommand implements Command{
    private CollectionManager collectionManager;

    public InfoCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public void execute(String command) {
        System.out.println(collectionManager);
    }
}
