package console.commads;

import application.App;
import core.CollectionManager;
import core.InputTicket;
import data.Ticket;

import java.util.Objects;

public class UpdateCommand implements Command {
    private InputTicket inputTicket;
    private CollectionManager collectionManager;

    public UpdateCommand(App app) {
        this.inputTicket = new InputTicket(app);
        this.collectionManager = app.getCollectionManager();
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
