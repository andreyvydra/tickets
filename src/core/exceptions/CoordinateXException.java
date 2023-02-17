package core.exceptions;

/**
 * CoordinateXException is a special case for coordinate X
 *
 * @see CoordinateException
 */
public class CoordinateXException extends CoordinateException {
    public CoordinateXException() {
        super("X должен быть больше -873!");
    }
}
