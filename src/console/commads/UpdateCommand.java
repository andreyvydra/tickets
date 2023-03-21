package console.commads;

import application.DataApp;
import console.commads.generalCommands.DataAppCommand;
import core.CollectionManager;
import core.InputTicket;
import core.OutputHandler;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;

import java.util.Objects;

import static core.Globals.ARGUMENT_POSITION;

/**
 * UpdateCommand updates ticket by id and extends Input
 * collection command. It finds changeable item
 * and update its attributes.
 *
 * @see CollectionManager
 * @see InputTicket
 */
public class UpdateCommand extends DataAppCommand {

    public UpdateCommand(OutputHandler outputHandler, DataApp dataApp) {
        super(outputHandler, dataApp);
    }

    @Override
    public void execute(String command) {
        long id = Long.parseLong(command.split(" ")[ARGUMENT_POSITION]);
        Ticket curTicket = dataApp.getTicketById(id);
        if (Objects.isNull(curTicket)) {
            outputHandler.println("Ticket не был найден");
            return;
        }
        outputHandler.println(curTicket);
        try {
            Ticket inpTicket = InputTicket.getTicketFromConsole(dataApp.getCollectionManager());
            curTicket.setName(inpTicket.getName());
            curTicket.setType(inpTicket.getType());
            curTicket.setPerson(inpTicket.getPerson());
            curTicket.setPrice(inpTicket.getPrice());
            curTicket.setCoordinates(inpTicket.getCoordinates());
            outputHandler.println("Ticket успешно изменён!");
        } catch (ValueIsNotPositiveException | EmptyNameException e) {
            outputHandler.println(e);
        }
    }

    @Override
    public void printHelp() {
        outputHandler.println("update id {element} : обновить значение элемента коллекции," +
                " id которого равен заданному");
    }
}
