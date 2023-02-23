package application;

import core.CollectionManager;
import core.FileManager;
import core.JSONParser;
import data.Ticket;

import java.time.ZonedDateTime;
import java.util.*;

public class DataApp {
    private final CollectionManager collectionManager;
    private final FileManager fileManager;
    private final Queue<String> commandBuffer = new LinkedList<>();

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

    public int getCommandBufferSize() {
        return commandBuffer.size();
    }

    public String getCommandFromBuffer() {
        return Objects.requireNonNull(commandBuffer.poll()).trim();
    }

    public void addTicketToCollection(Ticket ticket) {
        collectionManager.addTicket(ticket);
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

    public void removeTicketById(long id) {
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

    public void setCommandBuffer(List<String> commands) {
        commandBuffer.addAll(commands);
    }
}
