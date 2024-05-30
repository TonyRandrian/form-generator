package mg.tony;

import java.util.List;

import mg.tony.core.Form;
import mg.tony.core.FormGenerator;
import mg.tony.core.FormControl;

public class Main {
    public static void main(String[] args) {
        /*
         * addFormControl raha champ précis
         * generateFormControls raha camps rehetra
         */
        List<FormControl> formControls = new FormGenerator(Person.class, "#", "POST")
                .generateFormControls()
                .getFormControls();

        //System.out.println(formControls);

        Form f = new FormGenerator(Person.class, "#", "POST")
            .generateFormControls()
            .buildForm();

        System.out.println(
            f.section("form-control", "fc")
        );
        System.out.println(
            f.rest()
        );


        /*
         * FormGenerator formGen = new FormGenerator(Person.class);
         * formGen.addFormControl(fieldName, label, formControlType);
         * formGen.all();
         * Form f = formGenerator.buildForm();
         * f.display
         * f.begin
         *
         * <div class="d-flex" t>
         * f.row
         * f.row
         * <div/>
         * 
         */
    }
}