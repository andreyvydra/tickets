package core.exceptions;

public class CoordinateXException extends CoordinateException {
    public CoordinateXException() {
        super("X должен быть больше -873!");
    }
}
