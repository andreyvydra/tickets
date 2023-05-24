package db;

import core.OutputHandler;
import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.Ticket;
import db.serializers.TicketSerializer;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class DBManager {
    private final Connection connectionDB;
    private final OutputHandler outputHandler;

    public DBManager(Connection connectionDB, OutputHandler outputHandler) {
        this.connectionDB = connectionDB;
        this.outputHandler = outputHandler;
    }

    public ArrayList<Ticket> getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT T.id, T.name, T.coordinates, T.creation_date, T.price," +
                    "       T.person, T.creator, T.type, C.x AS cx, C.y AS cy," +
                    "       P.birthday, P.location, P.hair_color," +
                    "       P.eye_color, P.nationality, L.x AS lx," +
                    "       L.y AS ly, L.x AS lz FROM \"Ticket\" as T " +
                    "LEFT JOIN \"Coordinates\" as C on C.id = T.coordinates " +
                    "LEFT JOIN \"Person\" as P on P.id = T.person " +
                    "LEFT JOIN \"Location\" as L on L.id = P.location " +
                    "LEFT JOIN \"User\" as U on U.id = T.creator");
            while (resultSet.next()) {
                try {
                    TicketSerializer ticketSerializer = new TicketSerializer(resultSet);
                    Ticket ticket = ticketSerializer.getObject();
                    tickets.add(ticket);
                } catch (SQLException e) {
                    outputHandler.println("Что-то не так с запросом.");
                } catch (ValueIsNotPositiveException | EmptyFieldException | EmptyNameException |
                         CoordinateXException e) {
                    outputHandler.println(e);
                }

            }
            return tickets;
        } catch (SQLException e) {
            outputHandler.println("Что-то не так с запросом.");
        }
        return tickets;
    }

    public long addTicket(Ticket ticket) throws SQLException {
        long idCoords = addCoordinates(ticket);
        if (idCoords < 0) {
            return -1;
        }
        long idPerson = addPerson(ticket);
        System.out.println(idPerson);
        if (idPerson < 0) {
            return -1;
        }
        try {
            PreparedStatement statement = connectionDB.prepareStatement("INSERT INTO \"Ticket\" (name," +
                    " coordinates, creation_date, price, person, creator, type) VALUES(?, ?, ?, ?, ?, 1, ?::ticket_type)", new String[]{"id"});
            statement.setString(1, ticket.getName());
            statement.setLong(2, idCoords);
            ZonedDateTime zonedDateTime = ticket.getCreationDate();
            statement.setTimestamp(3, Timestamp.valueOf(zonedDateTime.toLocalDateTime()));
            statement.setLong(4, ticket.getPrice());
            statement.setLong(5, idPerson);
            statement.setString(6, ticket.getType().name());
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            long id = rs.getLong(1);
            ticket.setId(id);
            return id;
        } catch (SQLException | ValueIsNotPositiveException e) {
            return -1;
        }
    }

    public long addCoordinates(Ticket ticket) {
        try {
            float x = ticket.getCoordinates().getX();
            float y = ticket.getCoordinates().getY();
            PreparedStatement statement = connectionDB.prepareStatement("INSERT INTO \"Coordinates\" (x, y) VALUES(?, ?)", new String[]{"id"});
            statement.setFloat(1, x);
            statement.setFloat(2, y);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            return -1;
        }
    }

    public long addPerson(Ticket ticket) {
        try {
            LocalDateTime birthday = ticket.getPerson().getBirthday();
            String eyeColor = ticket.getPerson().getEyeColor().name();
            String hairColor = ticket.getPerson().getHairColor().name();
            String nationality = ticket.getPerson().getNationality().name();
            Long location = addLocation(ticket);
            PreparedStatement statement;
            if (Objects.isNull(location)) {
                statement = connectionDB.prepareStatement("INSERT INTO \"Person\" (birthday," +
                        " eye_color, hair_color, nationality) VALUES(?, ?::color, ?::color, ?::nationality)", new String[]{"id"});
                if (Objects.isNull(birthday)) {
                    statement.setObject(1, null);
                } else {
                    statement.setTimestamp(1, Timestamp.valueOf(birthday));
                }
                statement.setString(2, eyeColor);
                statement.setString(3, hairColor);
                statement.setObject(4, nationality);
            } else {
                statement = connectionDB.prepareStatement("INSERT INTO \"Person\" (birthday," +
                        " eye_color, hair_color, location, nationality) VALUES(?, ?::color, ?::color, ?, ?::nationality)", new String[]{"id"});
                if (Objects.isNull(birthday)) {
                    statement.setObject(1, null);
                } else {
                    statement.setTimestamp(1, Timestamp.valueOf(birthday));
                }
                statement.setString(2, eyeColor);
                statement.setString(3, hairColor);
                statement.setLong(4, location);
                statement.setString(5, nationality);
            }
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            return -1;
        }
    }

    public Long addLocation(Ticket ticket) {
        try {
            if (Objects.isNull(ticket.getPerson().getLocation())) {
                return null;
            }
            double x = ticket.getPerson().getLocation().getX();
            Integer y = ticket.getPerson().getLocation().getY();
            float z = ticket.getPerson().getLocation().getZ();
            PreparedStatement statement = connectionDB.prepareStatement("INSERT INTO \"Location\" (x, y, z) " +
                    "VALUES(?, ?, ?)", new String[]{"id"});
            statement.setDouble(1, x);
            statement.setInt(2, y);
            statement.setFloat(3, z);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            return null;
        }
    }
}
