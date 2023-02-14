package core;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME;
import static java.time.format.DateTimeFormatter.ISO_ZONED_DATE_TIME;

public class JSONParser {
    private JSONObject jObject;

    public JSONParser(JSONObject jObject) {
        this.jObject = jObject;
    }

    public ArrayList<Ticket> parse() {
        ArrayList<Ticket> tickets = new ArrayList<>();

        for (Object item : (JSONArray) this.jObject.get("tickets")) {
            try {
                JSONObject jObj = (JSONObject) item;
                Ticket ticket = this.parseTicket(jObj);
                tickets.add(ticket);
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return tickets;
    }

    public Ticket parseTicket(JSONObject jObject) {
        Ticket ticket = new Ticket();
        ticket.setId(this.getIdTicket(jObject));
        ticket.setName(this.getStringName(jObject));
        ticket.setCoordinates(this.getCoordinates(jObject));
        ticket.setCreationDate(this.getCreationDate(jObject));
        ticket.setPrice(this.getPrice(jObject));
        ticket.setType(this.getType(jObject));
        ticket.setPerson(this.getPerson(jObject));
        return ticket;
    }

    public Person getPerson(JSONObject jObject) {
        Person person = new Person();
        JSONObject jPerson = (JSONObject) jObject.get("person");

        if (jPerson.isNull("birthday") || !jPerson.has("birthday")) {
            person.setBirthday(null);
        } else {
            person.setBirthday(LocalDateTime.parse(jPerson.getString("birthday"), ISO_LOCAL_DATE_TIME));
        }

        person.setEyeColor(Color.valueOf(jPerson.getString("eyeColor")));

        if (jPerson.isNull("hairColor") || !jPerson.has("hairColor")) {
            person.setHairColor(null);
        } else {
            person.setHairColor(Color.valueOf(jPerson.getString("hairColor")));
        }

        person.setNationality(Country.valueOf(jPerson.getString("nationality")));

        if (jPerson.isNull("location") || !jPerson.has("location")) {
            person.setLocation(null);
        } else {
            JSONObject jLocation = (JSONObject) jPerson.get("location");
            Location location = new Location();

            location.setX(jLocation.getDouble("x"));
            location.setY(jLocation.getInt("y"));
            location.setZ(jLocation.getFloat("z"));

            person.setLocation(location);
        }
        return person;
    }

    public TicketType getType(JSONObject jObject) {
        return TicketType.valueOf(jObject.getString("type"));
    }

    public long getPrice(JSONObject jObject) {
        return jObject.getLong("price");
    }

    public ZonedDateTime getCreationDate(JSONObject jObject) {
        return ZonedDateTime.parse(jObject.getString("creationDate"), ISO_ZONED_DATE_TIME);
    }

    public Coordinates getCoordinates(JSONObject jObject) {
        JSONObject jCoordinate = (JSONObject) jObject.get("coordinates");
        return new Coordinates(jCoordinate.getFloat("x"), jCoordinate.getFloat("y"));
    }

    public long getIdTicket(JSONObject jObject) {
        return jObject.getLong("id");
    }

    public String getStringName(JSONObject jObject) {
        return jObject.getString("name");
    }
}
