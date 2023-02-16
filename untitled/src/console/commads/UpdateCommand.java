package console.commads;

import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

import java.util.Objects;

public class UpdateCommand implements Command {
    private InputTicket inputTicket;
    private CollectionManager collectionManager;

    public UpdateCommand(CollectionManager collectionManager) {
        this.inputTicket = new InputTicket(collectionManager);
        this.collectionManager = collectionManager;
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[1]);
        Ticket curTicket = this.collectionManager.getTicketById(id);
        if (Objects.isNull(curTicket)) {
            System.out.println("Ticket не был найден");
            return;
        }
        System.out.println(curTicket);
        Ticket inpTicket = this.inputTicket.getTicketFromConsole();
        curTicket.setName(inpTicket.getName());
        curTicket.setType(inpTicket.getType());
        curTicket.setPerson(inpTicket.getPerson());
        curTicket.setPrice(inpTicket.getPrice());
        curTicket.setCoordinates(inpTicket.getCoordinates());
        System.out.println("Ticket успешно изменён!");
    }
}
