package application;

import core.exceptions.TicketWasNotFound;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;
import managers.CollectionManager;

import java.time.ZonedDateTime;
import java.util.*;

public class DataApp {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;

    public DataApp(String filename) {
        collectionManager = new CollectionManager();
        fileManager = new FileManager(filename);
        JSONParser jsonParser = new JSONParser(fileManager.getJSONObjectFromFile());
        ArrayList<Ticket> tickets = jsonParser.parse();
        collectionManager.loadTickets(tickets);
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public void saveJSONObjectToFile() {
        fileManager.saveJSONObjectToFile(collectionManager);
    }


    public void addTicketToCollection(Ticket ticket) {
        collectionManager.addTicket(ticket);
    }

    public void setIdToTicket(Ticket ticket) throws ValueIsNotPositiveException {
        ticket.setId(getCollectionManager().getNewId());
    }

    public Ticket getMaxTicket() {
        return collectionManager.getMaxTicket();
    }

    public void clearCollectionManger() {
        collectionManager.clear();
    }

    public TreeSet<Ticket> getCollection() {
        return collectionManager.getCollection();
    }

    public int getCollectionLen() {
        return collectionManager.getLen();
    }

    public void removeTicketById(long id) throws TicketWasNotFound {
        collectionManager.removeTicket(id);
    }

    public Ticket getTicketById(long id) {
        return collectionManager.getTicketById(id);
    }

    public void removeTickets(ArrayList<Ticket> tickets) {
        collectionManager.removeTickets(tickets);
    }

    public Map<ZonedDateTime, List<Ticket>> getTicketGroupsByDate() {
        return collectionManager.getGroupsByDate();
    }

    public void addTicketToCollectionWithoutId(Ticket ticket) throws ValueIsNotPositiveException {
        setIdToTicket(ticket);
        addTicketToCollection(ticket);
    }
}