package mg.tony.formcontroltype;

import mg.tony.core.FormControl;

import java.lang.reflect.Field;

public class Input extends FormControl {
    public enum InputType{
        TEXT, NUMBER, DATE, RADIO
    }
    private InputType type;

    public Input(Class<?> clazz, String fieldName, String label, String id, String className, InputType type) {
        super(clazz, fieldName, label, id, className);
        this.type = type;
    }
    public Input(Class<?> clazz, Field field, String label, InputType type) {
        super(clazz, field, label);
        this.type = type;
    }
    @Override
    public String generateHTML() {
        StringBuilder valiny = new StringBuilder();
        valiny.append(String.format("<label for=\"%s\">%s</label>\n", id, label))
            .append("<input ")
            .append(String.format("class=\"%s\" ", className))
            .append(String.format("id=\"%s\" ", id))
            .append(
                String.format("type=\"%s\" ", type.toString().toLowerCase())
            )
            .append(
                String.format("name=\"%s\"", field.getName())
            )
            .append("/>");

        return valiny.toString();
    }
    @Override
    public String toString() {
        return "Input{" +
            "type=" + type +
            ", label='" + label + '\'' +
            ", id='" + id + '\'' +
            ", className='" + className + '\'' +
            ", options=" + options +
            '}';
    }
}
