package org.springframework.samples.petclinic.views.fragments;

import htmlflow.DynamicHtml;
import htmlflow.HtmlView;

public class SelectField {

    public static class DS {
        final Iterable data;
        final String selected;

        public DS(Iterable data, String selected) {
            this.data = data;
            this.selected = selected;
        }
        public static DS of(Iterable data, String selected) {
            return new DS(data, selected);
        }
    }

    public static HtmlView<DS> view = DynamicHtml.view(SelectField::template).threadSafe();

    public static void template(DynamicHtml<DS> view, DS src) {
        view
            .div()
                .attrClass("form-group")
                .label().attrClass("col-sm-2 control-label")
                    .text("Type")
                .__() //label
                .div().attrClass("col-sm-10")
                    .select().attrId("type").attrName("type")
                        .of(select -> src.data.forEach(item -> select
                            .option()
                                .attrValue(item.toString())
                                .dynamic(opt -> {
                                    if(src.selected != null && src.selected.equals(item.toString()))
                                        opt.attrSelected(true);
                                })
                                .text(item)
                            .__() //option
                        ))
                    .__() //select
                    .span()
                        .attrClass("glyphicon glyphicon-ok form-control-feedback")
                        .addAttr("aria-hidden","true")
                    .__() //span
                .__() //div
            .__(); //div
    }
}
