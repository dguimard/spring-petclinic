package org.springframework.samples.petclinic.views;

import htmlflow.DynamicHtml;
import htmlflow.HtmlView;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.views.fragments.InputField;
import org.xmlet.htmlapifaster.EnumMethodType;
import org.xmlet.htmlapifaster.EnumTypeButtonType;
import org.xmlet.htmlapifaster.EnumTypeInputType;

import java.time.LocalDate;

public class CreateOrUpdateVisitForm {

    public static HtmlView<Pet> view = DynamicHtml.view(CreateOrUpdateVisitForm::template).threadSafe();

    static void template(DynamicHtml<Pet> view, Pet pet) {
        view
            .div()
                .h2()
                    .text("New Visit")
                .__() //h2
                .b()
                    .text("Pet")
                .__() //b
                .table().attrClass("table table-striped")
                    .thead()
                        .tr()
                            .th()
                                .text("Name")
                            .__() //th
                            .th()
                                .text("Birth Date")
                            .__() //th
                            .th()
                                .text("Type")
                            .__() //th
                            .th()
                                .text("Owner")
                            .__() //th
                        .__() //tr
                    .__() //thead
                    .tbody()
                        .tr()
                            .td().dynamic(td -> td
                                .text(pet != null && pet.getName() != null ? pet.getName() : ""))
                            .__() //td
                            .td().dynamic(td -> td
                                .text(pet != null && pet.getBirthDate() != null ? pet.getBirthDate() : ""))
                            .__() //td
                            .td().dynamic(td -> td
                                .text(pet != null && pet.getType() != null ? pet.getType() : ""))
                            .__() //td
                            .td().dynamic(td -> td
                                .text(pet != null && pet.getOwner() != null ? pet.getOwner().getFirstName() + " " + pet.getOwner().getLastName() : ""))
                            .__() //td
                        .__() //tr
                    .__() //tbody
                .__() //table
                .form().attrClass("form-horizontal").attrMethod(EnumMethodType.POST)
                    .div().attrClass("form-group has-feedback")
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("Date", "date", LocalDate.now())))
                        .of(div -> view.addPartial(InputField.view, InputField.LV.of("Description", "description", "")))
                    .__() //div
                    .div().attrClass("form-group")
                        .div().attrClass("col-sm-offset-2 col-sm-10")
                            .input()
                                .attrType(EnumTypeInputType.HIDDEN)
                                .attrName("petId")
                                .dynamic(in -> in.attrValue(pet != null && pet.getId() != null ? pet.getId().toString() : ""))
                            .__() //input
                            .button().attrClass("btn btn-default").attrType(EnumTypeButtonType.SUBMIT)
                                .text("Add Visit")
                            .__() //button
                        .__() //div
                    .__() //div
                .__() //form
                .br().__() //br
                .b().text("Previous Visits").__() //b
                .table().attrClass("table table-striped")
                    .tbody()
                        .tr()
                            .th()
                                .text("Date")
                            .__() //th
                            .th()
                                .text("Description")
                            .__() //th
                        .__() //tr
                        .dynamic(tbody -> pet.getVisits().forEach(v -> tbody
                            .tr()
                                .td()
                                    .text(v.getDate())
                                .__() //td
                                .td()
                                    .text(v.getDescription())
                                .__() //td
                            .__() //tr
                        ))
                    .__() //tbody
                .__() //table
            .__();
    }
}
