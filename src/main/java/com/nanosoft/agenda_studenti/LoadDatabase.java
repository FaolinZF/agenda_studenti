package com.nanosoft.agenda_studenti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;
import java.time.LocalTime;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(AppuntamentiRepository repository) {
        return arg -> {
            repository.save(new Appuntamenti(LocalDate.parse("2023-04-01"), LocalTime.parse("13:45"),
                    TipoAppuntamento.CON_DIRETTORE, Uffici.UFFICIO2,
                    "appuntamento con il dr. Rossi per discutere il risultato dell'esame di Fisica"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-03-19"), LocalTime.parse("15:30"),
                    TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO5,
                    "appuntamento con la sig.ra Palermo per discutere l'assegnazione dell'alloggio"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-03-19"), LocalTime.parse("15:00"),
                    TipoAppuntamento.CON_DIRETTORE, Uffici.UFFICIO7,
                    "appuntamento con il sig. Mario Bianchi per discutere la tesi"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-03-19"), LocalTime.parse("16:50"),
                    TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO1,
                    "appuntamento con la sig.ra Cosenza per discutere del tirocinio"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-04-17"), LocalTime.parse("08:30"),
                    TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO6,
                    "appuntamento con l'ing. Verdi per discutere il progetto di fine corso"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-05-03"), LocalTime.parse("10:45"),
                    TipoAppuntamento.CON_DIRETTORE, Uffici.UFFICIO2,
                    "appuntamento con la sig.ra Roma per discutere sullo stato della tesi"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-05-03"), LocalTime.parse("13:45"),
                    TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO2,
                    "appuntamento con il sig. Francesco Bianchi per discutere il risultato dell'esame di Matematica 1"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-05-19"), LocalTime.parse("09:30"),
                    TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO1,
                    "appuntamento con la sig.ra Cosenza per discutere del tirocinio"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-05-27"), LocalTime.parse("11:00"),
                    TipoAppuntamento.CON_DIRETTORE, Uffici.UFFICIO3,
                    "appuntamento con il dr. Azzurri per discutere l'andamento del corso di laurea"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-03-03"), LocalTime.parse("13:31"),
                    TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO9,
                    "appuntamento con il dr. Bianchi per discutere il risultato dell'esame di Geologia"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-03-05"), LocalTime.parse("18:00"),
                    TipoAppuntamento.CON_DIRETTORE, Uffici.UFFICIO4,
                    "appuntamento con la dr.ssa Calabria per gestione richiesta di cambio corso di laurea"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-09-11"), LocalTime.parse("09:00"),
                    TipoAppuntamento.CON_DIRETTORE, Uffici.UFFICIO4,
                    "appuntamento con il dr. Rossi per discutere dell'accoglienza dei nuovi studenti"));

            repository.save(new Appuntamenti(LocalDate.parse("2023-06-30"), LocalTime.parse("12:00"),
                    TipoAppuntamento.CON_CONSULENTE, Uffici.UFFICIO4,
                    "appuntamento con la ing. Brescia per l'esame orale di Chimica"));

            repository.findAll().forEach(appuntamenti -> log.info("Preload " + appuntamenti));

        };
    }
}