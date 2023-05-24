package db.serializers;

import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;
import core.exceptions.EmptyNameException;
import core.exceptions.ValueIsNotPositiveException;

import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class Serializer {
    private final ResultSet resultSet;

    public Serializer(ResultSet rs) {
        resultSet = rs;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    abstract public Object getObject() throws SQLException, ValueIsNotPositiveException, EmptyNameException, EmptyFieldException, CoordinateXException;
}
