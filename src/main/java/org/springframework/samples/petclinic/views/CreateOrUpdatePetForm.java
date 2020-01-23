package org.springframework.samples.petclinic.views;

import htmlflow.DynamicHtml;
import org.springframework.samples.petclinic.owner.Pet;
import org.springframework.samples.petclinic.owner.PetRepository;
import org.springframework.samples.petclinic.owner.PetType;
import org.springframework.samples.petclinic.views.fragments.InputField;
import org.springframework.samples.petclinic.views.fragments.SelectField;
import org.springframework.stereotype.Component;
import org.xmlet.htmlapifaster.EnumMethodType;
import org.xmlet.htmlapifaster.EnumTypeButtonType;
import org.xmlet.htmlapifaster.EnumTypeInputType;

import java.util.List;
import java.util.function.Function;

@Component
public class CreateOrUpdatePetForm {

    private final PetRepository pets;
    private final List<PetType> petTypes;
    public final DynamicHtml<Pet> view;

    public CreateOrUpdatePetForm(PetRepository pets) {
        this.pets = pets;
        this.petTypes = pets.findPetTypes();
        this.view = DynamicHtml.view(this::template);
    }

    public void template(DynamicHtml<Pet> view, Pet pet) {
        view
            .div()
                .h2()
                    .dynamic(h2 -> {if(pet == null) h2.text("New "); })
                    .text("Pet")
                .__() //h2
                .form().attrClass("form-horizontal").attrMethod(EnumMethodType.POST)
                    .input().attrType(EnumTypeInputType.HIDDEN).attrName("id").attrValue("")
                    .__() //input
                    .div().attrClass("form-group has-feedback")
                        .div().attrClass("form-group")
                            .label().attrClass("col-sm-2 control-label")
                                .text("Owner")
                            .__() //label
                            .div().attrClass("col-sm-10")
                                .span()
                                    .text(from(pet, p -> p.getOwner() != null ? p.getOwner().getFirstName() + " " + p.getOwner().getLastName() : ""))
                                .__() //span
                            .__() //div
                        .__() //div
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("Name", "name", from(pet, o -> o.getName()))))
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("Birth Date", "birthDate", from(pet, p -> p.getBirthDate()))))
                        .dynamic(div -> view.addPartial(SelectField.view, SelectField.DS.of(petTypes, from(pet, p -> p.getType()))))
                    .__() //div
                    .div().attrClass("form-group")
                        .div().attrClass("col-sm-offset-2 col-sm-10")
                            .button().attrClass("btn btn-default").attrType(EnumTypeButtonType.SUBMIT)
                                .dynamic(bt -> {
                                    if(pet == null || pet.getName() == null) bt.text("Add Pet");
                                    else bt.text("Update Pet");
                                })
                            .__() //button
                        .__() //div
                    .__() //div
                .__() //form
            .__();
    }

    static <T, R> String from(T target, Function<T, R> getter) {
        Object val = target == null ? "" : getter.apply(target);
        return val != null ? val.toString() : "";
    }
}
