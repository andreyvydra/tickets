package data;



/**
 * Enum of Countries
 *
 * @see Person
 */
public enum Country {
    USA("США"),
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
