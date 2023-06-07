package db.serializers;

import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;
import data.Coordinates;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoordinatesSerializer extends Serializer {
    public CoordinatesSerializer(ResultSet rs) {
        super(rs);
    }
    @Override
    public Object getObject() throws SQLException, CoordinateXException, EmptyFieldException {
        ResultSet resultSet = getResultSet();
        return new Coordinates(resultSet.getFloat("cx"), resultSet.getFloat("cy"));
    }
}
