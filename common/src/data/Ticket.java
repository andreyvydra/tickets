package data;

import core.Globals;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Objects;

/**
 * Ticket is a class of ticket
 */
public class Ticket implements Comparable<Ticket>, Serializable {
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long price; //Значение поля должно быть больше 0
    private TicketType type; //Поле не может быть null
    private Person person; //Поле не может быть null

    public Ticket() {
        id = 1;
        name = "ticket";
        coordinates = new Coordinates();
        creationDate = ZonedDateTime.now();
        price = 1000;
        type = TicketType.USUAL;
        person = new Person();
    }

    public void setId(long id) throws ValueIsNotPositiveException {
        if (id <= 0) {
            throw new ValueIsNotPositiveException();
        }
        this.id = id;
    }

    public void setName(String name) throws EmptyNameException {
        if (name.isEmpty()) {
            throw new EmptyNameException();
        }
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) throws EmptyFieldException {
        if (Objects.isNull(coordinates)) {
            throw new EmptyFieldException();
        }
        this.coordinates = coordinates;
    }

    public void setCreationDate(ZonedDateTime creationDate) throws EmptyFieldException {
        if (Objects.isNull(creationDate)) {
            throw new EmptyFieldException();
        }
        this.creationDate = creationDate;
    }

    public void setPrice(long price) throws ValueIsNotPositiveException {
        if (price <= 0) {
            throw new ValueIsNotPositiveException();
        }
        this.price = price;
    }

    public void setType(TicketType type) throws EmptyFieldException {
        if (Objects.isNull(type)) {
            throw new EmptyFieldException();
        }
        this.type = type;
    }

    public void setPerson(Person person) throws EmptyFieldException {
        if (Objects.isNull(person)) {
            throw new EmptyFieldException();
        }
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
        hm.put(Globals.TicketFields.ID, this.id);
        hm.put(Globals.TicketFields.NAME, this.name);
        hm.put(Globals.TicketFields.COORDINATES, this.coordinates.getMappedValues());
        hm.put(Globals.TicketFields.CREATION_DATE, this.creationDate.toString());
        hm.put(Globals.TicketFields.PRICE, this.price);
        hm.put(Globals.TicketFields.TYPE, this.type.name());
        hm.put(Globals.TicketFields.PERSON, this.person.getMappedValues());
        return hm;
    }
}