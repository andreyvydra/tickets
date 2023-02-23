package core.exceptions;

/**
 * ValueIsNotPositiveException is exception for non-positive numbers
 */
public class ValueIsNotPositiveException extends RuntimeException {
    public ValueIsNotPositiveException() {
        super("Число не может быть отрицательным!");
    }
}
