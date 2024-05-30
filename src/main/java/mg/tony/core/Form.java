package mg.tony.core;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.List;

public class Form {
    // classe de retour (Person, Chien ou Chat)
    private final Class<?> clazz;
    private final List<FormControl> formControls;
    private int position = -1;
    private final String action;
    private final String method;

    public Form(List<FormControl> formControls, String action, String method) {
        this.clazz = formControls.get(0).getClazz();
        this.action = action;
        this.method = method;
        this.formControls = formControls;
    }

    public String formStart() {
        return String.format("<form action=\"%s\" method=\"%s\">", this.action, this.method);
    }

    public String display() {
        StringBuilder valiny = new StringBuilder();

        for (FormControl fc : formControls) {
            valiny.append(fc.generateHTML()).append('\n');
        }

        return valiny.toString();
    }

    public String formEnd() {
        return "</form>";
    }

    public String section(String className, String id) {
        position++;
        FormControl fc = formControls.get(position);
        fc.setClassName(className)
            .setId(id);

        return formControls.get(position).generateHTML();
    }

    public String rest() {
        StringBuilder strBuild = new StringBuilder();
        for (int i = position + 1; i < formControls.size(); i++) {
            strBuild.append(formControls.get(i).generateHTML()).append('\n');
        }

        return strBuild.toString();
    }

    public Object convertString(String value, Class<?> toThis) {
        Object valiny = new Object();
        if (toThis.equals(int.class) || toThis.equals(Integer.class)) {
            valiny = Integer.parseInt(value);
        } else if (toThis.equals(String.class)) {
            valiny = value;
        } else if (toThis.equals(boolean.class) || toThis.equals(Boolean.class)) {
            if ("true".equals(value)) {
                valiny = true;
            } else if ("false".equals(value)) {
                valiny = false;
            }
        }

        return valiny;
    }

    public Object treat(HttpServletRequest request) {
        /*int size = formControls.size();
        Object[] attributs = new Object[size];

        for (int i = 0; i < size; i++) {
            String param = request.getParameter(formControls.get(i).getField().getName());
            Class<?> clazz = formControls.get(i).getField().getType();

            attributs[i] = convertString(param, clazz);
        }*/
        Object valiny;
        try {
            valiny = clazz.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la création d'une instance dans Form.java ligne 81", e);
        }

        for (FormControl fc : formControls) {
            Field f = fc.getField();
            f.setAccessible(true);

            //obj.fieldName = value
            try {
                //mi-set valeur-an'ny field iray
                f.set(valiny, convertString(
                    request.getParameter(f.getName()),
                    f.getType()
                ));
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Erreur lors du set ", e);
            }
        }

        return valiny;
    }

    /*
     * boucle par formControls, request.getParameter(formControl.getName())
     * checker si il y a un champ vide + erreur
     * creation de l'instance de l'objet
     * choix: na manao setter mandray string na manao conversion mihitsy
     * en fonction an'ilay type (mandray objet vonona)
     *
     *
     */ //request.setAttribute("nomdeAttribut", Object u);
    //request.getMethod();
}
