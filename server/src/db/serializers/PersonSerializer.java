package db.serializers;

import core.exceptions.EmptyFieldException;
import data.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;

public class PersonSerializer extends Serializer {
    public PersonSerializer(ResultSet rs) {
        super(rs);
    }

    @Override
    public Object getObject() throws SQLException, EmptyFieldException {
        ResultSet resultSet = getResultSet();
        Person person = new Person();
        person.setNationality(Country.valueOf(resultSet.getString("nationality")));
        String hairColor = resultSet.getString("hair_color");
        if (Objects.isNull(hairColor)) {
            person.setHairColor(null);
        } else {
            person.setHairColor(Color.valueOf(hairColor));
        }
        person.setEyeColor(Color.valueOf(resultSet.getString("eye_color")));
        String birthday = resultSet.getString("birthday");
        if (Objects.isNull(birthday)) {
            person.setBirthday(null);
        } else {
            person.setBirthday(LocalDateTime.parse(resultSet.getString("birthday").replace(" ", "T").split("\\+")[0]));
        }
        if (!Objects.isNull(resultSet.getObject("location"))) {
            LocationSerializer ls = new LocationSerializer(resultSet);
            person.setLocation((Location) ls.getObject());
        }

        return person;
    }
}
