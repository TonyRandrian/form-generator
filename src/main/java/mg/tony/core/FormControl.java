package mg.tony.core;

import java.lang.reflect.Field;
import java.util.Map;

public class FormControl {
    // classe de retour (Person, Chien ou Chat)
    protected final Class<?> clazz;
    protected Field field;
    protected String label;
    protected String id;
    //class HTML
    protected String className;
    protected Map<String, String> options;

    protected FormControl(Class<?> clazz, String fieldName, String label, String id, String className) {
        this.clazz = clazz;
        this.setField(fieldName)
            .setLabel(label);
        this.id = id;
        this.className = className;
    }
    protected FormControl(Class<?> clazz, Field field, String label) {
        this.clazz = clazz;
        this.field = field;
        this.setLabel(label);
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public String getId() {
        return id;
    }

    public FormControl setId(String id) {
        this.id = id;
        return this;
    }
    public String getClassName() {
        return className;
    }
    Field getField() {
        return field;
    }
    public FormControl setClassName(String className) {
        this.className = className;
        return this;
    }
    private FormControl setField(String fieldName) {
        if (fieldName == null) {
            throw new NullPointerException("Le nom de champ ne doit pas être null");
        }
        fieldName = fieldName.strip();
        if (fieldName.isBlank()) {
            throw new IllegalArgumentException("Le nom de champ ne doit pas être vide");
        }

        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException(new NoSuchFieldException(
                String.format(
                    "%s n'est pas un champ de la classe %s", fieldName, clazz.getName()
                )
            ));
        }

        return this;
    }
    String getLabel() {
        return label;
    }
    private FormControl setLabel(String label) {
        if (label == null || label.strip().isBlank()) {
            this.label = field.getName();

            return this;
        }

        this.label = label;

        return this;
    }
    public String generateHTML(){
        return null;
    }
    @Override
    public String toString() {
        return "FormControl{" +
            "label='" + label + '\'' +
            ", id='" + id + '\'' +
            ", className='" + className + '\'' +
            ", options=" + options +
            '}';
    }
}

