package data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;

import static core.Globals.PersonFields.*;


/**
 * Person is a class of person
 *
 * @see Ticket
 */
public class Person {
    private java.time.LocalDateTime birthday; //Поле может быть null
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

    public void setEyeColor(Color eyeColor) {
        if (Objects.isNull(eyeColor)) {
            throw new NullPointerException();
        }
        this.eyeColor = eyeColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setNationality(Country nationality) {
        if (Objects.isNull(nationality)) {
            throw new NullPointerException();
        }
        this.nationality = nationality;
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put(BIRTHDAY, !Objects.isNull(this.birthday) ? this.birthday.toString() : null);
        hm.put(EYE_COLOR, this.eyeColor.name());
        hm.put(HAIR_COLOR, !Objects.isNull(this.hairColor) ? this.hairColor.name() : null);
        hm.put(NATIONALITY, this.nationality.name());
        hm.put(LOCATION, !Objects.isNull(this.location) ? this.location.getMappedValues() : null);
        return hm;
    }

    @Override
    public String toString() {
        return "Person(birthday=" + this.birthday + ", eyeColor=" + this.eyeColor +
                ", hairColor=" + this.hairColor + ", nationality=" +
                this.nationality + ", location=" + this.location + ")";
    }
}
