package com.nanosoft.agenda_studenti;

import org.springframework.data.jpa.repository.JpaRepository;

interface AppuntamentiRepository extends JpaRepository<Appuntamenti, Long> {
    
}
