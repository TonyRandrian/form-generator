package mg.tony.formcontroltype;

import mg.tony.core.FormControl;

import java.lang.reflect.Field;

public class InputRadio extends FormControl {
    public InputRadio(Class<?> clazz, String fieldName, String label, String id, String className) {
        super(clazz, fieldName, label, id, className);
    }
    public InputRadio(Class<?> clazz, Field field, String label) {
        super(clazz, field, label);
    }

    public String generateHTML() {
        StringBuilder valiny = new StringBuilder();
        valiny.append(String.format("<label for=\"%s\">%s(oui)</label>\n", id, label))
            .append("<input ")
            .append(String.format("class=\"%s\" ", className))
            .append(String.format("id=\"%s\" ", id))
            .append("type=\"radio\" value=\"true\" ")
            .append(
                String.format("name=\"%s\"", field.getName())
            )
            .append("/>")
            .append(String.format("<label for=\"%s\">%s(non)</label>\n", id, label))
            .append("<input ")
            .append(String.format("class=\"%s\" ", className))
            .append(String.format("id=\"%s\" ", id))
            .append("type=\"radio\" value=\"false\" ")
            .append(
                String.format("name=\"%s\"", field.getName())
            )
            .append("/>");

        return valiny.toString();
    }
}
