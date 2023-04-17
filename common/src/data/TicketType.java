package data;

import java.io.Serializable;

/**
 * TicketType is enum of ticket types
 */
public enum TicketType implements Serializable {
    CHEAP("Дешёвый"),
    BUDGETARY("Бюджетный"),
    USUAL("Обычный"),
    VIP("VIP");

    public final String ticketTypeName;
    TicketType(String name) {
        this.ticketTypeName = name;
    }

    @Override
    public String toString() {
        return name() + " (" + ticketTypeName + ")";
    }
}
