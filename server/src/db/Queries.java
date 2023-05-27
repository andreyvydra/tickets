package db;

public class Queries {
    public static String SQL_GET_ALL_TICKETS = "SELECT T.id, T.name, T.coordinates, T.creation_date, T.price," +
            "       T.person, T.creator, T.type, C.x AS cx, C.y AS cy," +
            "       P.birthday, P.location, P.hair_color," +
            "       P.eye_color, P.nationality, L.x AS lx," +
            "       L.y AS ly, L.x AS lz FROM \"Ticket\" as T " +
            "LEFT JOIN \"Coordinates\" as C on C.id = T.coordinates " +
            "LEFT JOIN \"Person\" as P on P.id = T.person " +
            "LEFT JOIN \"Location\" as L on L.id = P.location " +
            "LEFT JOIN \"User\" as U on U.username = T.creator";

    public static String SQL_ADD_TICKET = "INSERT INTO \"Ticket\" (name," +
            " coordinates, creation_date, price, person, creator, type) VALUES(?, ?, ?, ?, ?, ?, ?::ticket_type)";

    public static String SQL_ADD_COORDINATES = "INSERT INTO \"Coordinates\" (x, y) VALUES(?, ?)";

    public static String SQL_ADD_PERSON = "INSERT INTO \"Person\" (birthday," +
            " eye_color, hair_color, location, nationality) VALUES(?, ?::color, ?::color, ?, ?::nationality)";

    public static String SQL_ADD_LOCATION = "INSERT INTO \"Location\" (x, y, z) " +
            "VALUES(?, ?, ?)";

    public static String SQL_REMOVE_TICKET = "DELETE FROM \\\"Ticket\\\" WHERE id = ? AND creator = ?\"";

    public static String SQL_GET_TICKET = "SELECT t.person, t.coordinates, p.location FROM" +
            " \"Ticket\" t LEFT JOIN \"Person\" P on P.id = t.person WHERE t.id = ?";

    public static String SQL_UPDATE_TICKET = "UPDATE \"Ticket\" SET name = ?," +
            " type = ?::ticket_type, price = ? WHERE id = ? AND creator = ?";

    public static String SQL_UPDATE_PERSON = "UPDATE \"Person\" SET birthday = ?," +
            " hair_color = ?::color, eye_color = ?::color, nationality = ?::nationality WHERE id = ?";

    public static String SQL_UPDATE_COORDINATES = "UPDATE \"Coordinates\" SET x = ?," +
            " y = ? WHERE id = ?";

    public static String SQL_UPDATE_LOCATION = "UPDATE \"Location\" SET x = ?," +
            " y = ?, z = ? WHERE id = ?";

    public static long SQL_EXCEPTION_SIGNAL = -1;

    public static String SQL_GET_USER = "SELECT * FROM \"User\" WHERE username = ?";

    public static String SQL_CREATE_USER = "INSERT INTO \"User\" (username," +
            " password, salt) VALUES(?, ?, ?)";

    public static String[] SQL_RETURN_IDS = new String[]{"id"};
}
