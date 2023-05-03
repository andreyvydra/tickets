package data;

import core.Globals;
import core.exceptions.CoordinateXException;
import core.exceptions.EmptyFieldException;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

/**
 * Coordinates stores x, y values
 *
 * @see Ticket
 */
public class Coordinates implements Serializable {
    @Serial
    private static final long serialVersionUID = 13L;

    private Float x; //Значение поля должно быть больше -873, Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(Float x, Float y) throws CoordinateXException, EmptyFieldException {
        this.setX(x);
        this.setY(y);
    }

    public Coordinates() {
        super();
    }

    public void setX(Float x) throws CoordinateXException, EmptyFieldException {
        if (Objects.isNull(x)) {
            throw new EmptyFieldException();
        }
        if (x <= Globals.COORDINATE_X_MIN_LIMIT) {
            throw new CoordinateXException();
        }
        this.x = x;
    }

    public void setY(Float y) throws EmptyFieldException {
        if (Objects.isNull(y)) {
            throw new EmptyFieldException();
        }
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates(" + Globals.CoordinatesFields.X + "=" + this.x + ", " + Globals.CoordinatesFields.Y + "=" + this.y + ")";
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put(Globals.CoordinatesFields.X, this.x);
        hm.put(Globals.CoordinatesFields.Y, this.y);
        return hm;
    }
}

