package core;

import org.json.JSONException;

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
}

