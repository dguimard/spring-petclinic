package org.springframework.samples.petclinic.views;

import htmlflow.DynamicHtml;
import htmlflow.HtmlView;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.vet.Specialty;
import org.springframework.samples.petclinic.vet.Vet;

import java.util.List;
import java.util.stream.Collectors;

public class VetList {

    public static HtmlView<Iterable<Vet>> view = DynamicHtml.view(VetList::template).threadSafe();

    static void template(DynamicHtml<Iterable<Vet>> view, Iterable<Vet> vets) {
        view
            .div()
                .h2()
                    .text("Veterinarians")
                .__() //h2
                .table().attrId("vets").attrClass("table table-striped")
                    .thead()
                        .tr()
                            .th()
                                .text("Name")
                            .__() //th
                            .th()
                                .text("Specialties")
                            .__() //th
                        .__() //tr
                    .__() //thead
                    .tbody()
                        .dynamic(tbody -> vets.forEach(v -> tbody
                            .tr()
                                .td()
                                    .text(v.getFirstName() + " " + v.getLastName())
                                .__() //td
                                .td()
                                    .span()
                                        .text(format(v.getSpecialties()))
                                    .__() //span
                                .__() //td
                            .__() //tr
                        ))
                    .__() //tbody
                .__() //table
            .__();
    }

    private static String format(List<Specialty> specialties) {
        if(specialties.isEmpty()) return "none";
        return specialties
            .stream()
            .map(NamedEntity::toString)
            .collect(Collectors.joining(" "));
    }
}
