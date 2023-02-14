package core;

import org.json.JSONException;
import org.json.JSONObject;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket implements Comparable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null

    public void setId(long id) {
        if (id <= 0) {
            throw new JSONException("Id is lower or equals 0");
        }
        this.id = id;
    }

    public void setName(String name) {
        if (name.equals("")) {
            throw new JSONException("Name is equals empty string");
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
            throw new JSONException("Price is negative");
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
    public int compareTo(Object o) {
        Ticket ticket = (Ticket) o;
        return Long.compare(this.id, ticket.id);
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
}