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
import static db.Queries.*;

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
            ResultSet resultSet = statement.executeQuery(SQL_GET_ALL_TICKETS);
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
            return idCoords;
        }
        long idPerson = addPerson(ticket);
        if (idPerson < 0) {
            return idPerson;
        }
        try {
            PreparedStatement statement = connectionDB.prepareStatement(SQL_ADD_TICKET, SQL_RETURN_IDS);
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
            return SQL_EXCEPTION_SIGNAL;
        }
    }

    public long addCoordinates(Ticket ticket) {
        try {
            float x = ticket.getCoordinates().getX();
            float y = ticket.getCoordinates().getY();
            PreparedStatement statement = connectionDB.prepareStatement(SQL_ADD_COORDINATES, SQL_RETURN_IDS);
            statement.setFloat(1, x);
            statement.setFloat(2, y);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            return SQL_EXCEPTION_SIGNAL;
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

            statement = connectionDB.prepareStatement(SQL_ADD_PERSON, SQL_RETURN_IDS);
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
            return SQL_EXCEPTION_SIGNAL;
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
            PreparedStatement statement = connectionDB.prepareStatement(SQL_ADD_LOCATION, SQL_RETURN_IDS);
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
            PreparedStatement statement = connectionDB.prepareStatement(SQL_REMOVE_TICKET);
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
            PreparedStatement statement = connectionDB.prepareStatement(SQL_GET_TICKET);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();

            PreparedStatement statementTicket = connectionDB.prepareStatement(SQL_UPDATE_TICKET);
            statementTicket.setString(1, inpTicket.getName());
            statementTicket.setString(2, inpTicket.getType().name());
            statementTicket.setLong(3, inpTicket.getPrice());
            statementTicket.setLong(4, id);
            statementTicket.setString(5, inpTicket.getCreatorLogin());
            int updated = statementTicket.executeUpdate();
            if (updated == 0) {
                return false;
            }

            long personId = resultSet.getLong(1);
            Person person = inpTicket.getPerson();
            if (!updatePerson(personId, person)) {
                return false;
            }

            long coordinatesId = resultSet.getLong(2);
            Coordinates coordinates = inpTicket.getCoordinates();
            if (!updateCoordinates(coordinatesId, coordinates)) {
                return false;
            }

            long locationId = resultSet.getLong(3);
            Location location = person.getLocation();
            return updateLocation(locationId, location);
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean updatePerson(long personId, Person person) throws SQLException {
        PreparedStatement statementPerson = connectionDB.prepareStatement(SQL_UPDATE_PERSON);
        LocalDateTime birthday = person.getBirthday();
        if (Objects.isNull(birthday)) {
            statementPerson.setObject(1, null);
        } else {
            statementPerson.setTimestamp(1, Timestamp.valueOf(birthday));
        }
        if (Objects.isNull(person.getHairColor())) {
            statementPerson.setObject(2, null);
        } else {
            statementPerson.setString(2, person.getHairColor().name());
        }
        statementPerson.setString(3, person.getEyeColor().name());
        statementPerson.setString(4, person.getNationality().name());
        statementPerson.setLong(5, personId);
        int updated = statementPerson.executeUpdate();
        return updated != 0;
    }

    public boolean updateCoordinates(long coordinatesId, Coordinates coordinates) throws SQLException {
        PreparedStatement statementCoords = connectionDB.prepareStatement(SQL_UPDATE_COORDINATES);
        statementCoords.setFloat(1, coordinates.getX());
        statementCoords.setFloat(2, coordinates.getY());
        statementCoords.setLong(3, coordinatesId);
        int updated = statementCoords.executeUpdate();
        return updated != 0;
    }

    public boolean updateLocation(long locationId, Location location) throws SQLException {
        PreparedStatement statementLocation = connectionDB.prepareStatement(SQL_UPDATE_LOCATION);
        if (!Objects.isNull(location)) {
            statementLocation.setDouble(1, location.getX());
            statementLocation.setInt(2, location.getY());
            statementLocation.setFloat(3, location.getZ());
            statementLocation.setLong(4, locationId);
            int updated = statementLocation.executeUpdate();
            return updated != 0;
        }
        return true;
    }

    public boolean checkUser(HashMap<String, String> user) {
        try {
            PreparedStatement statement = connectionDB.prepareStatement(SQL_GET_USER);
            statement.setString(1, user.get(USERNAME));
            ResultSet rs = statement.executeQuery();
            rs.next();
            String password = "#^1,'5p6-" + user.get(PASSWORD) + rs.getString("salt");
            String passwordSHA256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
            return rs.getString(PASSWORD).equals(passwordSHA256);
        } catch (SQLException e) {
            return false;
        }
    }

    public long createUser(HashMap<String, String> user) {
        try {
            String salt = org.apache.commons.lang3.RandomStringUtils.randomNumeric(7);
            String passwordSHA256 = org.apache.commons.codec.digest.DigestUtils.sha256Hex("#^1,'5p6-" + user.get(PASSWORD) + salt);
            PreparedStatement statement = connectionDB.prepareStatement(SQL_CREATE_USER, SQL_RETURN_IDS);
            statement.setString(1, user.get(USERNAME));
            statement.setString(2, passwordSHA256);
            statement.setString(3, salt);
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            return SQL_EXCEPTION_SIGNAL;
        }
    }
}
