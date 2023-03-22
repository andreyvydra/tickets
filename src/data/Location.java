package data;

import core.exceptions.EmptyFieldException;

import java.util.HashMap;
import java.util.Objects;
import static core.Globals.LocationFields.*;


/**
 * Location stores x, y, z value for person
 *
 * @see Person
 */
public class Location {
    private double x;
    private Integer y; //Поле не может быть null
    private float z;

    public Location() {
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Integer y) throws EmptyFieldException {
        if (Objects.isNull(y)) {
            throw new EmptyFieldException();
        }
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put(X, this.x);
        hm.put(Y, this.y);
        hm.put(Z, this.z);
        return hm;
    }

    @Override
    public String toString() {
        return "Location(" + X + "=" + this.x + ", " + Y + "=" + this.y + ", " + Z + "=" + this.z + ")";
    }
}
