package mg.tony.exception;

public class NoSuchControlTypeException extends RuntimeException {
    public NoSuchControlTypeException(String controlType) {
        super(String.format(
            "No such control type: %s", controlType
        ));
    }
}
