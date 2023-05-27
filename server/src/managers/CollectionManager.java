package managers;

import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.TicketWasNotFound;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

/**
 * CollectionManager operates collection and their items.
 */
public class CollectionManager {
    private final ConcurrentSkipListSet<Ticket> collection = new ConcurrentSkipListSet<>();
    private final LocalDateTime initDateTime;

    public CollectionManager() {
        this.initDateTime = LocalDateTime.now();
    }

    public ConcurrentSkipListSet<Ticket> getCollection() {
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

    public boolean addTicket(Ticket ticket) {
        return this.collection.add(ticket);
    }

    public boolean updateTicket(long id, Ticket inpTicket) {
        Ticket curTicket = getTicketById(id);
        try {
            curTicket.setName(inpTicket.getName());
            curTicket.setType(inpTicket.getType());
            curTicket.setPerson(inpTicket.getPerson());
            curTicket.setPrice(inpTicket.getPrice());
            curTicket.setCoordinates(inpTicket.getCoordinates());
            return true;
        } catch (ValueIsNotPositiveException | EmptyNameException | EmptyFieldException e) {
            return false;
        }
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