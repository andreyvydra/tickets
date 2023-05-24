package managers;

import core.exceptions.TicketWasNotFound;
import data.Ticket;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * CollectionManager operates collection and their items.
 */
public class CollectionManager {
    private final TreeSet<Ticket> collection = new TreeSet<>();
    private final LocalDateTime initDateTime;

    public CollectionManager() {
        this.initDateTime = LocalDateTime.now();
    }

    public TreeSet<Ticket> getCollection() {
        return collection;
    }

    public Map<ZonedDateTime, List<Ticket>> getGroupsByDate() {
        return collection.stream().collect(Collectors.groupingBy(Ticket::getCreationDate));
    }

    public int getLen() {
        return collection.size();
    }

    public void loadTickets(ArrayList<Ticket> tickets) {
        this.collection.addAll(tickets);
    }

    public boolean checkId(long id) {
        for (Ticket ticket : this.collection) {
            if (ticket.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public boolean addTicket(Ticket ticket) {
        return this.collection.add(ticket);
    }

    public Ticket getTicketById(long id) {
        for (Ticket ticket : this.collection) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }
        return null;
    }

    public Ticket getMaxTicket() {
        return Collections.max(this.collection);
    }

    public void removeTicket(long id) throws TicketWasNotFound {
        if (!Objects.isNull(getTicketById(id))) {
            this.collection.remove(this.getTicketById(id));
        } else {
            throw new TicketWasNotFound();
        }
    }

    public void clear() {
        this.collection.clear();
    }

    @Override
    public String toString() {
        return "Type: CollectionManager, InitDate: " + this.initDateTime + ", Items quantity of collection: " + this.collection.size();
    }

    public void removeTickets(ArrayList<Ticket> ticketsToDelete) {
        for (Ticket ticket : ticketsToDelete) {
            this.collection.remove(ticket);
        }
    }
}