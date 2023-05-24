package application;

import core.exceptions.TicketWasNotFound;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;
import db.DBManager;
import managers.CollectionManager;

import java.sql.SQLException;
import java.time.ZonedDateTime;
import java.util.*;

public class DataApp {
    private final CollectionManager collectionManager;
    private final DBManager dbManager;

    public DataApp(DBManager dbManager) {
        collectionManager = new CollectionManager();
        this.dbManager = dbManager;
        ArrayList<Ticket> tickets = dbManager.getAllTickets();
        collectionManager.loadTickets(tickets);
    }

    public CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public boolean addTicketToCollection(Ticket ticket) {
        try {
            long idt = dbManager.addTicket(ticket);
            if (idt < 1) {
                return false;
            }
            return collectionManager.addTicket(ticket);
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
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

    public boolean addTicketToCollectionWithoutId(Ticket ticket) throws ValueIsNotPositiveException {
        return addTicketToCollection(ticket);
    }
}