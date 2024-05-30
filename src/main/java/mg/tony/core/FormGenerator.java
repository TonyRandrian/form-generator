package mg.tony.core;

import mg.tony.formcontroltype.Input;
import mg.tony.formcontroltype.InputRadio;
import mg.tony.formcontroltype.Select;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormGenerator {
    private Class<?> clazz;
    private final List<FormControl> formControls;
    private String action;
    private String method;

    public FormGenerator(Class<?> clazz, String action, String method) {
        this.setClazz(clazz);
        this.action = action;
        this.method = method;
        formControls = new ArrayList<>();
    }
    public Class<?> getClazz() {
        return clazz;
    }
    public void setClazz(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("L'argument clazz ne doit pas être nulle");
        }

        if (clazz.getClassLoader() == null) {
            throw new IllegalArgumentException("L'argument clazz ne doit pas être une classe standard de Java");
        }

        this.clazz = clazz;
    }
    public FormGenerator addInput(String fieldName, String label, String id, String className,Input.InputType type) {
        formControls.add(
            new Input(clazz, fieldName, label, id, className, type)
        );

        return this;
    }
    public FormGenerator addSelect(String fieldName, String label, String id, String className) {
        formControls.add(
            new Select(clazz, fieldName, label, id, className)
        );

        return this;
    }
    public List<FormControl> getFormControls() {
        return formControls;
    }
    public FormGenerator generateFormControls() {
        if (!formControls.isEmpty()) {
            return this;
        }

        for (Field field : clazz.getDeclaredFields()) {
            formControls.add(getAppropriateFormControl(field));
        }

        return this;
    }
    private FormControl getAppropriateFormControl(Field field) {
        Class<?> typ = field.getType();
        String fieldName = field.getName();
        FormControl valiny;

        if (typ == String.class || typ == Double.class || typ == double.class || typ == Float.class || typ == float.class) {
            valiny = new Input(clazz, field, fieldName, Input.InputType.TEXT);
        } else if (typ == Integer.class || typ == int.class) {
            valiny = new Input(clazz, field, fieldName, Input.InputType.NUMBER);
        } else if (typ == Boolean.class || typ == boolean.class) {
            //valiny = new Input(clazz, field, fieldName, Input.InputType.RADIO);
            valiny = new InputRadio(clazz, field, fieldName);
        } else {
            throw new IllegalArgumentException(
                String.format("Impossible de générer à partir du type %s du champ %s", typ.getName(), fieldName)
            );
        }

        return valiny;
    }
    public Form buildForm() {
        return new Form(formControls, action, method);
    }
}

