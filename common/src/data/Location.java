package data;

import core.Globals;
import core.exceptions.EmptyFieldException;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;


/**
 * Location stores x, y, z value for person
 *
 * @see Person
 */
public class Location implements Serializable {
    @Serial
    private static final long serialVersionUID = 12L;

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

    public double getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put(Globals.LocationFields.X, this.x);
        hm.put(Globals.LocationFields.Y, this.y);
        hm.put(Globals.LocationFields.Z, this.z);
        return hm;
    }

    @Override
    public String toString() {
        return "Location(" + Globals.LocationFields.X + "=" + this.x +
                ", " + Globals.LocationFields.Y + "=" + this.y +
                ", " + Globals.LocationFields.Z + "=" + this.z + ")";
    }
}
