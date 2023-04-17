package data;

import java.io.Serializable;

/**
 * Enum of colors
 *
 * @see Person
 */
public enum Color implements Serializable {
    GREEN("Зелёный"),
    YELLOW("Жёлтый"),
    ORANGE("Оранжевый"),
    RED("Красный"),
    BLUE("Синий"),
    BROWN("Коричневый");

    public final String colorName;
    Color(String name) {
        this.colorName = name;
    }

    @Override
    public String toString() {
        return name() + " (" + colorName + ")";
    }
}