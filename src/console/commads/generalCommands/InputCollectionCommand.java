package console.commads.generalCommands;

import application.App;
import core.InputTicket;


/**
 * InputCollectionCommand needs because we have classes, which
 * communicate with collection and do input classes.
 *
 * @see core.CollectionManager
 * @see CollectionCommand
 * @see InputTicket
 * @see App
 */
public abstract class InputCollectionCommand extends CollectionCommand {
    private InputTicket inputTicket;

    public InputCollectionCommand(App app) {
        super(app.getCollectionManager());
        this.inputTicket = new InputTicket(app);
    }

    public InputTicket getInputTicket() {
        return inputTicket;
    }
}
