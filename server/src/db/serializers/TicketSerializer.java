package db.serializers;

import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.Coordinates;
import data.Person;
import data.Ticket;
import data.TicketType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZonedDateTime;


public class TicketSerializer extends Serializer {
    public TicketSerializer(ResultSet rs) {
        super(rs);
    }

    public Ticket getObject() throws SQLException, ValueIsNotPositiveException,
            EmptyNameException, EmptyFieldException, CoordinateXException {
        ResultSet resultSet = getResultSet();
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setName(resultSet.getString("name"));
        ticket.setPrice(resultSet.getLong("price"));
        ticket.setType(TicketType.valueOf(resultSet.getString("type")));
        ticket.setCreationDate(ZonedDateTime.parse(resultSet.getString("creation_date").replace(" ", "T")));
        CoordinatesSerializer cs = new CoordinatesSerializer(resultSet);
        ticket.setCoordinates((Coordinates) cs.getObject());
        PersonSerializer ps = new PersonSerializer(resultSet);
        ticket.setPerson((Person) ps.getObject());
        ticket.setCreatorLogin(resultSet.getString("creator"));
        return ticket;
    }
}
