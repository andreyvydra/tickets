package core.exceptions;

/**
 * CoordinateException is general exception for incorrect coordinate
 */
public class CoordinateException extends RuntimeException {
    public CoordinateException(String s) {
        super(s);
    }
}
