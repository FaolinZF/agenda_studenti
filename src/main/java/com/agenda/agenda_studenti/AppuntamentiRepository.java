package com.agenda.agenda_studenti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
interface AppuntamentiRepository extends JpaRepository<Appuntamenti, Long> {

    @Query(value = "SELECT * FROM appuntamenti e WHERE e.data = :data ORDER BY e.ora", nativeQuery = true)
    List<Appuntamenti> findByDataOrderByOraAsc(@Param("data") LocalDate data);

    @Query(value = "SELECT * FROM appuntamenti e WHERE e.tipo = :#{#tipo.name()} ORDER BY e.data ASC, e.ora ASC", nativeQuery = true)
    List<Appuntamenti> findByTipoAppuntamentiOrderByDataAndOraAsc(@Param("tipo") TipoAppuntamento tipo);

    @Query(value = "SELECT * FROM appuntamenti e WHERE e.data = :data AND e.ora = :ora", nativeQuery = true)
    Appuntamenti findByDataAndOra(@Param("data") LocalDate data, @Param("ora") LocalTime ora);

    @Query(value = "SELECT * FROM appuntamenti e WHERE e.descrizione LIKE %:descrizione% ORDER BY e.data ASC, e.ora ASC", nativeQuery = true)
    List<Appuntamenti> findByDescrizioneOrderByDataAscAndOraAsc(@Param("descrizione") String descrizione);

    void deleteByDataAndOra(LocalDate data, LocalTime ora);

}