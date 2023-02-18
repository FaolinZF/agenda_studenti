package com.nanosoft.agenda_studenti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AppuntamentiRepository repository) {
        return arg -> {
            repository.save(new Appuntamenti("2023-04-01", "13:45", TipoAppuntamento.CON_DIRETTORE, Uffici.UFFICIO2,
                    "appuntamento con il dr. Rossi per discutere il risultato dell'esame di Fisica"));
            repository.save(new Appuntamenti("2023-03-19", "15:30", TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO5,
                    "appuntamento con la sig.ra Palermo per discutere l'assegnazione dell'alloggio"));
            repository.findAll().forEach(appuntamenti -> log.info("Preload " + appuntamenti));
        };
    }
}