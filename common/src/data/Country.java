package data;


import java.io.Serializable;

/**
 * Enum of Countries
 *
 * @see Person
 */
public enum Country implements Serializable {
    USA("Америка"),
    GERMANY("Германия"),
    CHINA("Китай"),
    THAILAND("Тайланд"),
    JAPAN("Япония");

    public final String countryName;
    Country(String name) {
        this.countryName = name;
    }

    @Override
    public String toString() {
        return name() + " (" + countryName + ")";
    }
}
