package com.nanosoft.agenda_studenti;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@Repository
@Transactional
interface AppuntamentiRepository extends JpaRepository<Appuntamenti, Long> {

    @Query(value = "SELECT * FROM appuntamenti e WHERE e.data = :data ORDER BY e.ora", nativeQuery = true)
    // List<Appuntamenti> findByDate(@Param("data") String date);
    List<Appuntamenti> findByDataOrderByOraAsc(@Param("data") LocalDate data);

    @Query(value = "SELECT * FROM appuntamenti e WHERE e.tipo = :#{#tipo.name()} ORDER BY e.data ASC, e.ora ASC", nativeQuery = true)
    // List<Appuntamenti> findByUfficio(@Param("ufficio") String ufficio);
    List<Appuntamenti> findByTipoAppuntamentiOrderByDataAndOraAsc(@Param("tipo") TipoAppuntamento tipo);

    @Query(value = "SELECT * FROM appuntamenti e WHERE e.data = :data AND e.ora = :ora", nativeQuery = true)
    // List<Appuntamenti> findByDateOra(@Param("data") String date, @Param("ora")
    // String ora);
    Appuntamenti findByDataAndOra(@Param("data") LocalDate data, @Param("ora") LocalTime ora);

    /*
     * @Modifying
     * 
     * @Query(value =
     * "DELETE FROM appuntamenti e WHERE e.data = :data AND e.ora = :ora",
     * nativeQuery = true)
     */
    void deleteByDataAndOra(LocalDate data, LocalTime ora);

}