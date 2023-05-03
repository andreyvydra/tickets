package core.exceptions;

import static core.Globals.COORDINATE_X_MIN_LIMIT;

/**
 * CoordinateXException is a special case for coordinate X
 *
 * @see CoordinateException
 */
public class CoordinateXException extends CoordinateException {
    public CoordinateXException() {
        super("X должен быть больше " + COORDINATE_X_MIN_LIMIT + "!");
    }
}
