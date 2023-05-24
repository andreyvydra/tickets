package db.serializers;

import core.exceptions.EmptyFieldException;
import data.Location;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationSerializer extends Serializer {
    public LocationSerializer(ResultSet rs) {
        super(rs);
    }

    @Override
    public Object getObject() throws SQLException, EmptyFieldException {
        ResultSet resultSet = getResultSet();
        Location location = new Location();
        location.setX(resultSet.getDouble("lx"));
        location.setY(resultSet.getInt("ly"));
        location.setZ(resultSet.getFloat("lz"));
        return location;
    }
}
