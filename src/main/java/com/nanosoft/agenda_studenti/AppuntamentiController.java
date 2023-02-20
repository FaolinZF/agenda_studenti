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
    @GetMapping("appuntamenti/data")
    List<Appuntamenti> perData(@RequestParam LocalDate data) {
        List<Appuntamenti> l = repository.findByDataOrderByOraAsc(data);
        if (l.isEmpty()) {
            throw new AppuntamentoPerDataNotFoundException("Non ho trovato appuntamenti per il giorno " + data);
        }
        return l;
    }

    // appuntamenti per ufficio
    @GetMapping("appuntamenti/ufficio")
    List<Appuntamenti> perTipoAppuntamento(@RequestParam TipoAppuntamento tipo) {
        List<Appuntamenti> l = repository.findByTipoAppuntamentiOrderByDataAndOraAsc(tipo);
        if (l.isEmpty()) {
            throw new AppuntamentoPerTipoAppuntamentiNotFoundException("Non ho trovato appuntamenti di tipo: " + tipo);
        }
        return l;
    }

    // appuntamento per data e ora
    @GetMapping("appuntamenti/")
    Appuntamenti appuntamentoSpecifico(@RequestParam LocalDate data, @RequestParam LocalTime ora) {
        Appuntamenti a = repository.findByDataAndOra(data, ora);
        if (a == null) {
            throw new AppuntamentoPerDataOraNotFoundException(
                    "Non ho trovato l'appuntamento per il giorno " + data + " alle ore " + ora);
        }
        return a;
    }

    @PutMapping("appuntamenti/put")
    Appuntamenti replaceAppuntamentoAppuntamento(@RequestBody Appuntamenti newAppuntamento,
            @RequestParam LocalDate data,
            @RequestParam LocalTime ora) {

        Appuntamenti findedAppuntamento = repository.findByDataAndOra(data, ora);

        if (findedAppuntamento != null) {
            findedAppuntamento.setData(newAppuntamento.getData());
            findedAppuntamento.setOra(newAppuntamento.getOra());
            findedAppuntamento.setTipoAppuntamento(newAppuntamento.getTipoAppuntamento());
            findedAppuntamento.setUfficio(newAppuntamento.getUfficio());
            findedAppuntamento.setDescrizione(newAppuntamento.getDescrizione());
            return repository.save(findedAppuntamento);
        }

        return newAppuntamenti(newAppuntamento);
    }

    @DeleteMapping("/appuntamenti/delete")
    void deleteAppuntamento(@RequestParam LocalDate data, @RequestParam LocalTime ora) {
        repository.deleteByDataAndOra(data, ora);
    }
}
