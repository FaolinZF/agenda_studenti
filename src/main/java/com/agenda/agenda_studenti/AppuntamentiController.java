package com.agenda.agenda_studenti;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.IanaLinkRelations;

@RestController
public class AppuntamentiController {

    private final AppuntamentiRepository repository;
    private final AppuntamentiModelAssembler assembler;

    AppuntamentiController(AppuntamentiRepository repository, AppuntamentiModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/appuntamenti")
    CollectionModel<EntityModel<Appuntamenti>> all() {

        List<EntityModel<Appuntamenti>> appuntamenti = repository.findAll().stream().map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(appuntamenti, linkTo(methodOn(AppuntamentiController.class).all()).withSelfRel());
    }
    // end::get-aggregate-root[]

    // inserisci appuntamento
    @PostMapping("appuntamenti")
    ResponseEntity<?> newAppuntamenti(@RequestBody Appuntamenti newAppuntamenti) {

        EntityModel<Appuntamenti> entityModel = assembler.toModel(repository.save(newAppuntamenti));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // appuntamenti per data
    @GetMapping("appuntamenti/data")
    CollectionModel<EntityModel<Appuntamenti>> perData(@RequestParam LocalDate data) {

        List<EntityModel<Appuntamenti>> l = repository.findByDataOrderByOraAsc(data).stream().map(assembler::toModel)
                .collect(Collectors.toList());
        if (l.isEmpty()) {
            throw new AppuntamentoPerDataNotFoundException("Non ho trovato appuntamenti per il giorno " + data);
        }
        return CollectionModel.of(l, linkTo(methodOn(AppuntamentiController.class).perData(data)).withSelfRel());
    }

    // appuntamenti per ufficio
    @GetMapping("appuntamenti/ufficio")
    CollectionModel<EntityModel<Appuntamenti>> perTipoAppuntamento(@RequestParam TipoAppuntamento tipo) {

        List<EntityModel<Appuntamenti>> l = repository.findByTipoAppuntamentiOrderByDataAndOraAsc(tipo).stream()
                .map(assembler::toModel).collect(Collectors.toList());
        if (l.isEmpty()) {
            throw new AppuntamentoPerTipoAppuntamentiNotFoundException("Non ho trovato appuntamenti di tipo: " + tipo);
        }
        return CollectionModel.of(l,
                linkTo(methodOn(AppuntamentiController.class).perTipoAppuntamento(tipo)).withSelfRel());
    }

    // appuntamento per data e ora
    @GetMapping("appuntamenti/")
    EntityModel<Appuntamenti> appuntamentoSpecifico(@RequestParam LocalDate data, @RequestParam LocalTime ora) {

        Appuntamenti a = repository.findByDataAndOra(data, ora);
        if (a == null) {
            throw new AppuntamentoPerDataOraNotFoundException(
                    "Non ho trovato l'appuntamento per il giorno " + data + " alle ore " + ora);
        }
        return assembler.toModel(a);
    }

    // appuntamenti per descrizione
    @GetMapping("appuntamenti/descrizione")
    CollectionModel<EntityModel<Appuntamenti>> perDescrizione(@RequestParam String descrizione) {

        List<EntityModel<Appuntamenti>> l = repository.findByDescrizioneOrderByDataAscAndOraAsc(descrizione).stream()
                .map(assembler::toModel).collect(Collectors.toList());

        if (l.isEmpty()) {
            throw new AppuntamentoPerDescrizioneNotFoundException(
                    "Non ho trovato appuntamenti contenente tutta o parte di questa descizione '" + descrizione + "'");
        }
        return CollectionModel.of(l,
                linkTo(methodOn(AppuntamentiController.class).perDescrizione(descrizione)).withSelfRel());

    }

    @PutMapping("appuntamenti/put")
    ResponseEntity<?> replaceAppuntamentoAppuntamento(@RequestBody Appuntamenti newAppuntamento,
            @RequestParam LocalDate data,
            @RequestParam LocalTime ora) {

        Appuntamenti findedAppuntamento = repository.findByDataAndOra(data, ora);

        if (findedAppuntamento != null) {
            findedAppuntamento.setData(newAppuntamento.getData());
            findedAppuntamento.setOra(newAppuntamento.getOra());
            findedAppuntamento.setTipoAppuntamento(newAppuntamento.getTipoAppuntamento());
            findedAppuntamento.setUfficio(newAppuntamento.getUfficio());
            findedAppuntamento.setDescrizione(newAppuntamento.getDescrizione());

            EntityModel<Appuntamenti> entityModel = assembler.toModel(repository.save(findedAppuntamento));

            return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                    .body(entityModel);
        }

        EntityModel<Appuntamenti> entityModel = assembler.toModel(repository.save(newAppuntamento));

        return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    @DeleteMapping("/appuntamenti/delete")
    void deleteAppuntamento(@RequestParam LocalDate data, @RequestParam LocalTime ora) {
        repository.deleteByDataAndOra(data, ora);
    }
}