package data;

import java.util.HashMap;


/**
 * Location stores x, y, z value for person
 *
 * @see Person
 */
public class Location {
    private double x;
    private Integer y; //Поле не может быть null
    private float z;

    public void setX(double x) {
        this.x = x;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public HashMap<String, Object> getMappedValues() {
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("x", this.x);
        hm.put("y", this.y);
        hm.put("z", this.z);
        return hm;
    }

    @Override
    public String toString() {
        return "Location(x=" + this.x + ", y=" + this.y + ", z=" + this.z + ")";
    }
}
