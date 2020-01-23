package org.springframework.samples.petclinic.views;

import htmlflow.HtmlView;
import htmlflow.StaticHtml;
import org.xmlet.htmlapifaster.EnumMethodType;
import org.xmlet.htmlapifaster.EnumTypeButtonType;

public class OwnersFind {
    public static final HtmlView view = StaticHtml
        .view()
            .div()
                .h2()
                    .text("Find Owners")
                .__() //h2
                .form()
                    .attrAction("/owners")
                    .attrMethod(EnumMethodType.GET)
                    .attrClass("form-horizontal")
                    .attrId("search-owner-form")
                    .div().attrClass("form-group")
                        .div().attrClass("control-group").attrId("lastNameGroup")
                            .label().attrClass("col-sm-2 control-label")
                                .text("Last name ")
                            .__() //label
                            .div().attrClass("col-sm-10")
                                .input()
                                    .attrClass("form-control")
                                    .attrSize(Long.valueOf(30L))
                                    .attrMaxlength(Long.valueOf(80L))
                                    .attrId("lastName")
                                    .attrName("lastName")
                                    .attrValue("")
                                .__() //input
                                .span().attrClass("help-inline")
                                .__() //span
                            .__() //div
                        .__() //div
                    .__() //div
                    .div().attrClass("form-group")
                        .div().attrClass("col-sm-offset-2 col-sm-10")
                            .button().attrType(EnumTypeButtonType.SUBMIT).attrClass("btn btn-default")
                                .text("Find Owner")
                            .__() //button
                        .__() //div
                    .__() //div
                .__() //form
                .br().__() //br
                .a().attrClass("btn btn-default").attrHref("/owners/new")
                    .text("Add Owner")
                .__() //a
            .__();
}
