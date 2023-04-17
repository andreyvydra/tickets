package data;

import core.Globals;
import core.exceptions.EmptyFieldException;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;


/**
 * Person is a class of person
 *
 * @see Ticket
 */
public class Person implements Serializable {
    private LocalDateTime birthday; //Поле может быть null
    private Color eyeColor; //Поле не может быть null
    private Color hairColor; //Поле может быть null
    private Country nationality; //Поле не может быть null
    private Location location; //Поле может быть null

    public Person() {
        birthday = null;
        eyeColor = Color.BLUE;
        hairColor = null;
        nationality = Country.USA;
        location = null;
    }

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public void setEyeColor(Color eyeColor) throws EmptyFieldException {
        if (Objects.isNull(eyeColor)) {
            throw new EmptyFieldException();
        }
        this.eyeColor = eyeColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setNationality(Country nationality) throws EmptyFieldException {
        if (Objects.isNull(nationality)) {
            throw new EmptyFieldException();
        }
        this.nationality = nationality;
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put(Globals.PersonFields.BIRTHDAY, !Objects.isNull(this.birthday) ? this.birthday.toString() : null);
        hm.put(Globals.PersonFields.EYE_COLOR, this.eyeColor.name());
        hm.put(Globals.PersonFields.HAIR_COLOR, !Objects.isNull(this.hairColor) ? this.hairColor.name() : null);
        hm.put(Globals.PersonFields.NATIONALITY, this.nationality.name());
        hm.put(Globals.PersonFields.LOCATION, !Objects.isNull(this.location) ? this.location.getMappedValues() : null);
        return hm;
    }

    @Override
    public String toString() {
        return "Person(birthday=" + this.birthday + ", eyeColor=" + this.eyeColor +
                ", hairColor=" + this.hairColor + ", nationality=" +
                this.nationality + ", location=" + this.location + ")";
    }
}
