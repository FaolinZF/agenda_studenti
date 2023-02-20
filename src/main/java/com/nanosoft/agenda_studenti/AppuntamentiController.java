package com.nanosoft.agenda_studenti;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.web.bind.annotation.*;

@RestController
public class AppuntamentiController {

    private final AppuntamentiRepository repository;

    AppuntamentiController(AppuntamentiRepository repository) {
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/appuntamenti")
    List<Appuntamenti> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    // inserisci appuntamento
    @PostMapping("appuntamenti")
    Appuntamenti newAppuntamenti(@RequestBody Appuntamenti newAppuntamenti) {
        return repository.save(newAppuntamenti);
    }

    // appuntamenti per data
    @GetMapping("appuntamenti/data/{data}")
    List<Appuntamenti> perData(@PathVariable String data) {
        List<Appuntamenti> l = repository.findByDataOrderByOraAsc(LocalDate.parse(data));
        if (l.isEmpty()) {
            throw new AppuntamentoPerDataNotFoundException("Non ho trovato appuntamenti per il giorno " + data);
        }
        return l;
    }

    // appuntamenti per ufficio
    @GetMapping("appuntamenti/uffici/{ufficio}")
    List<Appuntamenti> perUffico(@PathVariable String ufficio) {
        List<Appuntamenti> l = repository.findByUfficioOrderByDataAndOraAsc(Uffici.valueOf(ufficio));
        if (l.isEmpty()) {
            throw new AppuntamentoPerUfficioNotFoundException("Non ho trovato appuntamenti per l'ufficio: " + ufficio);
        }
        return l;
    }

    // appuntamento per data e ora
    @GetMapping("appuntamenti/dataora/{data}/{ora}")
    Appuntamenti appuntamentoSpecifico(@PathVariable String data, @PathVariable String ora) {
        Appuntamenti a = repository.findByDataAndOra(LocalDate.parse(data), LocalTime.parse(ora));
        if (a == null) {
            throw new AppuntamentoPerDataOraNotFoundException(
                    "Non ho trovato l'appuntamento per il giorno " + data + " alle ore " + ora);
        }
        return a;
    }

    @PutMapping("appuntamenti/dataora/{data}/{ora}")
    Appuntamenti replaceAppuntamentoAppuntamento(@RequestBody Appuntamenti newAppuntamento, @PathVariable String data,
            @PathVariable String ora) {

        if (repository.findByDataAndOra(LocalDate.parse(data), LocalTime.parse(ora)) != null) {
            newAppuntamento.setData(newAppuntamento.getData());
            newAppuntamento.setOra(newAppuntamento.getOra());
            newAppuntamento.setTipoAppuntamento(newAppuntamento.getTipoAppuntamento());
            newAppuntamento.setUfficio(newAppuntamento.getUfficio());
            newAppuntamento.setDescrizione(newAppuntamento.getDescrizione());
            return repository.save(newAppuntamento);
        }

        Appuntamenti appuntamento = new Appuntamenti(newAppuntamento.getData(), newAppuntamento.getOra(),
                newAppuntamento.getTipoAppuntamento(), newAppuntamento.getUfficio(), newAppuntamento.getDescrizione());

        return repository.save(appuntamento);
    }

    @DeleteMapping("/appuntamenti/delete/dataora/{data}/{ora}")
    void deleteAppuntamento(@PathVariable String data, @PathVariable String ora) {
        repository.deleteByDataAndOra(LocalDate.parse(data), LocalTime.parse(ora));
    }
}
