package data;

import core.exceptions.CoordinateXException;

import java.util.HashMap;
import java.util.Objects;

import static core.Globals.COORDINATE_X_MIN_LIMIT;
import static core.Globals.CoordinatesFields.*;

/**
 * Coordinates stores x, y values
 *
 * @see Ticket
 */
public class Coordinates {
    private Float x; //Значение поля должно быть больше -873, Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(Float x, Float y) throws CoordinateXException {
        this.setX(x);
        this.setY(y);
    }

    public Coordinates() {
        super();
    }

    public void setX(Float x) throws CoordinateXException {
        if (Objects.isNull(x)) {
            throw new NullPointerException();
        }
        if (x <= COORDINATE_X_MIN_LIMIT) {
            throw new CoordinateXException();
        }
        this.x = x;
    }

    public void setY(Float y) {
        if (Objects.isNull(y)) {
            throw new NullPointerException();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates(" + X + "=" + this.x + ", " + Y + "=" + this.y + ")";
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put(X, this.x);
        hm.put(Y, this.y);
        return hm;
    }
}

