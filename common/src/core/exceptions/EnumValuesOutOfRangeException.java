package core.exceptions;

public class EnumValuesOutOfRangeException extends Exception {
    public EnumValuesOutOfRangeException() {
        super("Введённое чилсо вне диапазона");
    }
}
