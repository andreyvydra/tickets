package core;

import data.Ticket;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionManager {
    private TreeSet<Ticket> collection = new TreeSet<>();
    private LocalDateTime initDateTime;

    public CollectionManager() {
        this.initDateTime = LocalDateTime.now();
    }

    public LocalDateTime getInitDateTime() {
        return this.initDateTime;
    }

    public TreeSet<Ticket> getCollection() {
        return collection;
    }

    public Map<ZonedDateTime, List<Ticket>> getGroupsByDate() {
        return collection.stream().collect(Collectors.groupingBy(Ticket::getCreationDate));
    }



    public void loadTickets(ArrayList<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            if (this.checkId(ticket.getId())) {
                this.collection.add(ticket);
            }
        }
    }

    public boolean checkId(long id) {
        for (Ticket ticket : this.collection) {
            if (ticket.getId() == id) {
                return false;
            }
        }
        return true;
    }

    public long getNewId() {
        long id = 0;
        for (Ticket ticket : this.collection) {
            if (ticket.getId() > id) {
                id = ticket.getId();
            }
        }
        return id + 1;
    }

    public void addTicket(Ticket ticket) {
        this.collection.add(ticket);
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

    public void removeTicket(long id) {
        this.collection.remove(this.getTicketById(id));
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