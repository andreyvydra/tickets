package data;

/**
 * TicketType is enum of ticket types
 */
public enum TicketType {
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
        return ticketTypeName;
    }
}
