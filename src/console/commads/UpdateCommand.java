package console.commads;

import application.App;
import console.commads.generalCommands.InputCollectionCommand;
import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

import java.util.Objects;

/**
 * UpdateCommand updates ticket by id and extends Input
 * collection command. It finds changeable item
 * and update its attributes.
 *
 * @see InputCollectionCommand
 * @see CollectionManager
 * @see InputTicket
 */
public class UpdateCommand extends InputCollectionCommand {
    public UpdateCommand(App app) {
        super(app);
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[1]);
        Ticket curTicket = this.getCollectionManager().getTicketById(id);
        if (Objects.isNull(curTicket)) {
            System.out.println("Ticket не был найден");
            return;
        }
        System.out.println(curTicket);
        Ticket inpTicket = this.getInputTicket().getTicketFromConsole();
        curTicket.setName(inpTicket.getName());
        curTicket.setType(inpTicket.getType());
        curTicket.setPerson(inpTicket.getPerson());
        curTicket.setPrice(inpTicket.getPrice());
        curTicket.setCoordinates(inpTicket.getCoordinates());
        System.out.println("Ticket успешно изменён!");
    }
}
