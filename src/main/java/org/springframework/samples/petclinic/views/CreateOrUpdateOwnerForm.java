package org.springframework.samples.petclinic.views;

import htmlflow.DynamicHtml;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.views.fragments.InputField;
import org.xmlet.htmlapifaster.EnumMethodType;
import org.xmlet.htmlapifaster.EnumTypeButtonType;

import java.util.function.Function;

public class CreateOrUpdateOwnerForm {

    public static final DynamicHtml<Owner> view = (DynamicHtml<Owner>) DynamicHtml
        .view(CreateOrUpdateOwnerForm::template)
        .threadSafe();

    static void template(DynamicHtml<Owner> view, Owner owner) {
        view
            .div()
                .h2()
                    .text("Owner")
                .__() //h2
                .form().attrClass("form-horizontal").attrId("add-owner-form").attrMethod(EnumMethodType.POST)
                    .div().attrClass("form-group has-feedback")
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("First Name", "firstName", from(owner, o -> o.getFirstName()))))
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("Last Name", "lastName", from(owner, o -> o.getLastName()))))
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("Address", "address", from(owner, o -> o.getAddress()))))
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("City", "city", from(owner, o -> o.getCity()))))
                        .dynamic(div -> view.addPartial(InputField.view, InputField.LV.of("Telephone", "telephone", from(owner, o -> o.getTelephone()))))
                    .__() //div
                    .div().attrClass("form-group")
                        .div().attrClass("col-sm-offset-2 col-sm-10")
                            .button().attrClass("btn btn-default").attrType(EnumTypeButtonType.SUBMIT)
                                .dynamic(bt -> {
                                    if(owner == null) bt.text("Add Owner");
                                    else bt.text("Update Owner");
                                })
                            .__() //button
                        .__() //div
                    .__() //div
                .__() //form
            .__(); // div
    }

    static <T, R> String from(T target, Function<T, R> getter) {
        return target == null ? "" : getter.apply(target).toString();
    }
}
