package db;

import core.OutputHandler;
import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;
import data.*;
import db.serializers.TicketSerializer;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static core.Globals.Network.PASSWORD;
import static core.Globals.Network.USERNAME;

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
                    "LEFT JOIN \"User\" as U on U.username = T.creator");
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
                    " coordinates, creation_date, price, person, creator, type) VALUES(?, ?, ?, ?, ?, ?, ?::ticket_type)", new String[]{"id"});
            statement.setString(1, ticket.getName());
            statement.setLong(2, idCoords);
            ZonedDateTime zonedDateTime = ticket.getCreationDate();
            statement.setTimestamp(3, Timestamp.valueOf(zonedDateTime.toLocalDateTime()));
            statement.setLong(4, ticket.getPrice());
            statement.setLong(5, idPerson);
            statement.setString(6, ticket.getCreatorLogin());
            statement.setString(7, ticket.getType().name());
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
            Color hairColor = ticket.getPerson().getHairColor();
            String nationality = ticket.getPerson().getNationality().name();
            Long location = addLocation(ticket);
            PreparedStatement statement;

            statement = connectionDB.prepareStatement("INSERT INTO \"Person\" (birthday," +
                    " eye_color, hair_color, location, nationality) VALUES(?, ?::color, ?::color, ?, ?::nationality)", new String[]{"id"});
            if (Objects.isNull(birthday)) {
                statement.setObject(1, null);
            } else {
                statement.setTimestamp(1, Timestamp.valueOf(birthday));
            }
            statement.setString(2, eyeColor);
            if (Objects.isNull(hairColor)) {
                statement.setObject(3, null);
            } else {
                statement.setString(3, hairColor.name());
            }
            statement.setObject(4, location);
            statement.setString(5, nationality);

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

    public boolean removeTicket(long id, HashMap<String, String> user) {
        try {
            PreparedStatement statement = connectionDB.prepareStatement("DELETE FROM \"Ticket\" WHERE id = ? AND creator = ?");
            statement.setLong(1, id);
            statement.setString(2, user.get(USERNAME));
            int deleted = statement.executeUpdate();
            return deleted > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updateTicket(long id, Ticket inpTicket) {
        try {
            Statement statement = connectionDB.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT t.person, t.coordinates, p.location FROM" +
                    " \"Ticket\" t LEFT JOIN \"Person\" P on P.id = t.person WHERE t.id = " + id);
            resultSet.next();
            long personId = resultSet.getLong(1);
            long coordinatesId = resultSet.getLong(2);
            long locationId = resultSet.getLong(3);

            PreparedStatement statementTicket = connectionDB.prepareStatement("UPDATE \"Ticket\" SET name = ?," +
                    " type = ?::ticket_type, price = ? WHERE id = ? AND creator = ?");
            statementTicket.setString(1, inpTicket.getName());
            statementTicket.setString(2, inpTicket.getType().name());
            statementTicket.setLong(3, inpTicket.getPrice());
            statementTicket.setLong(4, id);
            statementTicket.setString(5, inpTicket.getCreatorLogin());
            int updated = statementTicket.executeUpdate();
            if (updated == 0) {
                return false;
            }

            PreparedStatement statementPerson = connectionDB.prepareStatement("UPDATE \"Person\" SET birthday = ?," +
                    " hair_color = ?::color, eye_color = ?::color, nationality = ?::nationality WHERE id = ?");
            Person person = inpTicket.getPerson();
            LocalDateTime birthday = person.getBirthday();
            if (Objects.isNull(birthday)) {
                statementPerson.setObject(1, null);
            } else {
                statementPerson.setString(1, String.valueOf(birthday));
            }
            if (Objects.isNull(person.getHairColor())) {
                statementPerson.setObject(2, null);
            } else {
                statementPerson.setString(2, person.getHairColor().name());
            }
            statementPerson.setString(3, person.getEyeColor().name());
            statementPerson.setString(4, person.getNationality().name());
            statementPerson.setLong(5, personId);
            updated = statementPerson.executeUpdate();
            if (updated == 0) {
                return false;
            }

            PreparedStatement statementCoords = connectionDB.prepareStatement("UPDATE \"Coordinates\" SET x = ?," +
                    " y = ? WHERE id = ?");
            Coordinates coordinates = inpTicket.getCoordinates();
            statementCoords.setFloat(1, coordinates.getX());
            statementCoords.setFloat(2, coordinates.getY());
            statementCoords.setLong(3, coordinatesId);
            updated = statementCoords.executeUpdate();
            if (updated == 0) {
                return false;
            }

            PreparedStatement statementLocation = connectionDB.prepareStatement("UPDATE \"Location\" SET x = ?," +
                    " y = ?, z = ? WHERE id = ?");
            Location location = person.getLocation();
            if (!Objects.isNull(location)) {
                statementLocation.setDouble(1, location.getX());
                statementLocation.setInt(2, location.getY());
                statementLocation.setFloat(3, location.getZ());
                statementLocation.setLong(4, locationId);
                updated = statementLocation.executeUpdate();
                return updated != 0;
            }
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

        public boolean checkUser(HashMap<String, String> user) {
        try {
            PreparedStatement statement = connectionDB.prepareStatement("SELECT * FROM \"User\" WHERE username = ?");
            statement.setString(1, user.get(USERNAME));
            ResultSet rs = statement.executeQuery();
            rs.next();
            String password = "#^1,'5p6-" + user.get(PASSWORD) + rs.getString("salt");
            String passwordSHA256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            return rs.getString("password").equals(passwordSHA256);
        } catch (SQLException e) {
            return false;
        }
    }

    public long createUser(HashMap<String, String> user) {
        try {
            String salt = org.apache.commons.lang3.RandomStringUtils.randomNumeric(7);
            String passwordSHA256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex("#^1,'5p6-" + user.get(PASSWORD) + salt);
            PreparedStatement statement = connectionDB.prepareStatement("INSERT INTO \"User\" (username," +
                    " password, salt) VALUES(?, ?, ?)", new String[]{"id"});
            statement.setString(1, user.get(USERNAME));
            statement.setString(2, passwordSHA256);
            statement.setString(3, salt);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            return -1;
        }
    }
}
