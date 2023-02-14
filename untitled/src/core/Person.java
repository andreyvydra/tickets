package core;

import java.time.LocalDateTime;

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
}
