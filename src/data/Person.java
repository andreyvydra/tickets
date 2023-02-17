package data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;


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

    public void setBirthday(LocalDateTime birthday) {
        this.birthday = birthday;
    }

    public void setEyeColor(Color eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(Color hairColor) {
        this.hairColor = hairColor;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("birthday", !Objects.isNull(this.birthday) ? this.birthday.toString() : null);
        hm.put("eyeColor", this.eyeColor.toString());
        hm.put("hairColor", !Objects.isNull(this.hairColor) ? this.hairColor.toString() : null);
        hm.put("nationality", this.nationality.toString());
        hm.put("location", !Objects.isNull(this.location) ? this.location.getMappedValues() : null);
        return hm;
    }

    @Override
    public String toString() {
        return "Person(birthday=" + this.birthday + ", eyeColor=" + this.eyeColor +
                ", hairColor=" + this.hairColor + ", nationality=" +
                this.nationality + ", location=" + this.location + ")";
    }
}
