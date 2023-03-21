package core;

import core.exceptions.CoordinateXException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;

import static core.Globals.JSONFields.PERSON;
import static core.Globals.JSONFields.TICKETS;
import static core.Globals.LocationFields.*;
import static core.Globals.PersonFields.*;
import static core.Globals.TicketFields.*;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

/**
 * JSONParser converts data from json file to java objects.
 *
 * @see JSONObject
 * @see JSONArray
 */
public class JSONParser {
    private final JSONObject jObject;
    private final OutputHandler outputHandler = new OutputHandler();

    public JSONParser(JSONObject jObject) {
        this.jObject = jObject;
    }

    public ArrayList<Ticket> parse() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        if (this.jObject.has(TICKETS)) {
            for (Object item : (JSONArray) this.jObject.get(TICKETS)) {
                try {
                    JSONObject jsonObject = (JSONObject) item;
                    Ticket ticket = this.parseTicket(jsonObject);
                    tickets.add(ticket);
                } catch (ValueIsNotPositiveException | EmptyNameException | CoordinateXException e) {
                    outputHandler.println(e);
                } catch (JSONException e) {
                    outputHandler.println("Некорректный файл!");
                } catch (NullPointerException e) {
                    outputHandler.println("Поле не может быть null");
                } catch (IllegalArgumentException e) {
                    outputHandler.println("Некорректное поле enum");
                }
            }
        }

        return tickets;
    }

    public Ticket parseTicket(JSONObject jObject) throws ValueIsNotPositiveException, EmptyNameException, CoordinateXException {
        Ticket ticket = new Ticket();
        try {
            ticket.setId(this.getIdTicket(jObject));
            ticket.setName(this.getStringName(jObject));
            ticket.setCoordinates(this.getCoordinates(jObject));
            ticket.setCreationDate(this.getCreationDate(jObject));
            ticket.setPrice(this.getPrice(jObject));
            ticket.setType(this.getType(jObject));
            ticket.setPerson(this.getPerson(jObject));
        } catch (JSONException e) {
            outputHandler.println("Некорректный файл!");
        }
        return ticket;
    }

    public Person getPerson(JSONObject jObject) {
        Person person = new Person();
        JSONObject jPerson = (JSONObject) jObject.get(PERSON);

        if (jPerson.isNull(BIRTHDAY) || !jPerson.has(BIRTHDAY)) {
            person.setBirthday(null);
        } else {
            person.setBirthday(LocalDateTime.parse(jPerson.getString(BIRTHDAY), ISO_LOCAL_DATE_TIME));
        }

        person.setEyeColor(Color.valueOf(jPerson.getString(EYE_COLOR)));

        if (jPerson.isNull(HAIR_COLOR) || !jPerson.has(HAIR_COLOR)) {
            person.setHairColor(null);
        } else {
            person.setHairColor(Color.valueOf(jPerson.getString(HAIR_COLOR)));
        }

        person.setNationality(Country.valueOf(jPerson.getString(NATIONALITY)));

        if (jPerson.isNull(LOCATION) || !jPerson.has(LOCATION)) {
            person.setLocation(null);
        } else {
            JSONObject jLocation = (JSONObject) jPerson.get(LOCATION);
            Location location = new Location();

            location.setX(jLocation.getDouble(X));
            location.setY(jLocation.getInt(Y));
            location.setZ(jLocation.getFloat(Z));

            person.setLocation(location);
        }
        return person;
    }

    public TicketType getType(JSONObject jObject) {
        return TicketType.valueOf(jObject.getString(TYPE));
    }

    public long getPrice(JSONObject jObject) {
        return jObject.getLong(PRICE);
    }

    public ZonedDateTime getCreationDate(JSONObject jObject) {
        return ZonedDateTime.parse(jObject.getString(CREATION_DATE), ISO_ZONED_DATE_TIME);
    }

    public Coordinates getCoordinates(JSONObject jObject) throws CoordinateXException {
        JSONObject jCoordinate = (JSONObject) jObject.get(COORDINATES);
        return new Coordinates(jCoordinate.getFloat(Globals.CoordinatesFields.X), jCoordinate.getFloat(Globals.CoordinatesFields.Y));
    }

    public long getIdTicket(JSONObject jObject) {
        return jObject.getLong(ID);
    }

    public String getStringName(JSONObject jObject) {
        return jObject.getString(NAME);
    }
}
