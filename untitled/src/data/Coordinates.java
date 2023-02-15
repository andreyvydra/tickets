package data;

import org.json.JSONException;

import java.util.HashMap;

public class Coordinates {
    private Float x; //Значение поля должно быть больше -873, Поле не может быть null
    private Float y; //Поле не может быть null

    public Coordinates(Float x, Float y) {
        if (x <= -873) {
            throw new JSONException("X is lower then -873");
        }
        this.x = x;
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

