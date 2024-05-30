package mg.tony.exception;

public class UnsupportedFieldTypeException extends RuntimeException {
    public UnsupportedFieldTypeException(Class<?> unsupportedType) {
        super(String.format(
            "Le type %s ne correspond à aucun type de form-control", unsupportedType.getName()
        ));
    }
}
