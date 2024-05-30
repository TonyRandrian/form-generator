package mg.tony.formcontroltype;

import mg.tony.core.FormControl;

import java.util.Map;

public class Select extends FormControl {
    private String name;
    private Map<String, String> choices;

    public Select(Class<?> clazz, String fieldName, String label, String id, String className) {
        super(clazz, fieldName, label, id, className);
    }
}
