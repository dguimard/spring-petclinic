package org.springframework.samples.petclinic.views;

import htmlflow.*;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.owner.Pet;

public class OwnerDetails {

    public static final HtmlView<Owner> view = DynamicHtml.view(OwnerDetails::template).threadSafe();

    static void template(DynamicHtml<Owner> view, Owner owner) {
        view
            .div()
                .h2()
                    .text("Owner Information")
                .__() //h2
                .table().attrClass("table table-striped")
                     .tbody()
                        .tr()
                            .th()
                                .text("Name")
                            .__() //th
                            .td()
                                .b()
                                    .dynamic(b -> b.text(owner.getFirstName() + " " + owner.getLastName()))
                                .__()
                            .__() //td
                        .__() //tr
                        .tr()
                            .th()
                                .text("Address")
                            .__() //th
                            .td()
                                .dynamic(td -> td.text(owner.getAddress()))
                            .__()
                        .__() //tr
                        .tr()
                            .th()
                                .text("City")
                            .__() //th
                            .td()
                                .dynamic(td -> td.text(owner.getCity()))
                            .__() //td
                        .__() //tr
                        .tr()
                            .th()
                                .text("Telephone")
                            .__() //th
                            .td()
                                .dynamic(td -> td.text(owner.getTelephone()))
                            .__() //td
                        .__() //tr
                    .__() //tbody
                .__() //table
                .a().dynamic(a -> a.attrHref(owner.getId() + "/edit").attrClass("btn btn-default"))
                    .text("Edit Owner")
                .__() //a
                .a().dynamic(a -> a.attrHref(owner.getId() + "/pets/new").attrClass("btn btn-default"))
                    .text("Add New Pet")
                .__() //a
                .br().__() //br
                .br().__() //br
                .br().__() //br
                .h2().text("Pets and Visits").__() //h2
                .table().attrClass("table table-striped")
                    .tbody()
                        .dynamic(tbody -> owner.getPets().forEach(pet -> tbody
                            .tr()
                                .td().addAttr("valign","top")
                                    .dl().attrClass("dl-horizontal")
                                        .dt().text("Name").__() //dt
                                        .dd().text(pet.getName()).__() //dd
                                        .dt().text("Birth Date").__() //dt
                                        .dd().text(pet.getBirthDate().toString()).__() //dd
                                        .dt().text("Type").__() //dt
                                        .dd().text(pet.getType().toString()).__() //dd
                                    .__() //dl
                                .__() //td
                                .td().addAttr("valign","top")
                                    .table().attrClass("table-condensed")
                                        .thead()
                                            .tr()
                                                .th().text("Visit Date").__() //th
                                                .th().text("Description").__() //th
                                            .__() //tr
                                        .__() //thead
                                        .tbody()
                                            .of(innerbody -> pet.getVisits().forEach(vs -> innerbody
                                                .tr()
                                                    .td().text(vs.getDate()).__() //td
                                                    .td().text(vs.getDescription()).__() //td
                                                .__() //tr
                                            ))
                                            .tr()
                                                .td()
                                                    .a().attrHref(path(owner, pet) + "/edit")
                                                        .text("Edit Pet")
                                                    .__() //a
                                                .__() //td
                                                .td()
                                                    .a().attrHref(path(owner, pet) + "/visits/new")
                                                        .text("Add Visit")
                                                    .__()
                                                .__()
                                            .__()
                                        .__()
                                    .__()
                                .__()
                            .__()
                        ))
                        .__()
                    .__()
                .__();
    }

    static String path(Owner owner, Pet pet) {
        return owner.getId() + "/pets/" + pet.getId() ;
    }
}
