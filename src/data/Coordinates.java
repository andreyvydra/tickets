package data;

import core.exceptions.CoordinateXException;

import java.util.HashMap;

/**
 * Coordinates stores x, y values
 *
 * @see Ticket
 */
public class Coordinates {
    private Float x; //Значение поля должно быть больше -873, Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(Float x, Float y) {
        this.setX(x);
        this.setY(y);
    }

    public Coordinates() {
        super();
    }

    public void setX(Float x) throws CoordinateXException {
        if (x <= -873) {
            throw new CoordinateXException();
        }
        this.x = x;
    }

    public void setY(Float y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates(x=" + this.x + ", y=" + this.y + ")";
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("x", this.x);
        hm.put("y", this.y);
        return hm;
    }
}

