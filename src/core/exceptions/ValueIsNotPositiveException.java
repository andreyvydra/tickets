package core.exceptions;

public class ValueIsNotPositiveException extends RuntimeException {
    public ValueIsNotPositiveException(String msgIsNegative) {
        super(msgIsNegative);
    }
}
