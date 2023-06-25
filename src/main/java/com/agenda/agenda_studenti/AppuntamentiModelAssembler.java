package com.agenda.agenda_studenti;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class AppuntamentiModelAssembler
        implements RepresentationModelAssembler<Appuntamenti, EntityModel<Appuntamenti>> {

    @Override
    public EntityModel<Appuntamenti> toModel(Appuntamenti appuntamento) {

        return EntityModel.of(appuntamento,
                linkTo(methodOn(AppuntamentiController.class)
                        .appuntamentoSpecifico(appuntamento.getData(), appuntamento.getOra())).withSelfRel(),
                linkTo(methodOn(AppuntamentiController.class).all()).withRel("appuntamenti"));

    }

}