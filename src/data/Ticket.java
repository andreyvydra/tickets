package data;

import core.exceptions.ValueIsNotPositiveException;
import org.json.JSONException;

import java.time.ZonedDateTime;
import java.util.HashMap;

public class Ticket implements Comparable<Ticket> {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null

    public void setId(long id) throws ValueIsNotPositiveException {
        if (id <= 0) {
            throw new ValueIsNotPositiveException("Id должен быть положительным");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name.isEmpty()) {

        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setPrice(long price) {
        if (price <= 0) {
            throw new ValueIsNotPositiveException("Price is negative");
        }
        this.price = price;
    }

    public void setType(TicketType type) {
        this.type = type;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @Override
    public int compareTo(Ticket ticket) {
        return Long.compare(this.price, ticket.price);
    }

    @Override
    public String toString() {
        return "Ticket(id=" + this.id + ", name=" + this.name +
                ", coordinates=" + this.coordinates + ", creationDate=" + this.creationDate +
                ", price=" + this.price + ", type=" + this.type + ", person=" + this.person + ")";
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public long getPrice() {
        return price;
    }

    public Person getPerson() {
        return person;
    }

    public TicketType getType() {
        return type;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("id", this.id);
        hm.put("name", this.name);
        hm.put("coordinates", this.coordinates.getMappedValues());
        hm.put("creationDate", this.creationDate.toString());
        hm.put("price", this.price);
        hm.put("type", this.type.toString());
        hm.put("person", this.person.getMappedValues());
        return hm;
    }
}