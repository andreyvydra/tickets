package core.exceptions;

/**
 * ValueIsNotPositiveException is exception for non-positive numbers
 */
public class ValueIsNotPositiveException extends RuntimeException {
    public ValueIsNotPositiveException(String msgIsNegative) {
        super(msgIsNegative);
    }
}
