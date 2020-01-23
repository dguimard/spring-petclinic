package org.springframework.samples.petclinic.views;

import htmlflow.DynamicHtml;
import htmlflow.HtmlView;

public class Error {

    public static HtmlView<Exception> view = DynamicHtml.view(Error::template).threadSafe();

    static void template(DynamicHtml<Exception> view, Exception ex) {
        view
            .div()
                .img().attrClass("img-responsive").attrSrc("/resources/images/pets.png")
                .__()
                .h2()
                    .text("Something happened...")
                .__()
                .p()
                    .dynamic(p -> p.text(ex.getMessage()))
                .__()
            .__();
    }
}
