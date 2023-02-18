package com.nanosoft.agenda_studenti;

import java.util.Objects;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class Appuntamenti {

    private @Id @GeneratedValue Long id;
    private LocalDate data;
    private LocalTime ora;
    private TipoAppuntamento tipo;
    private Uffici ufficio;
    private String descrizione;

    Appuntamenti() {
    }

    Appuntamenti(LocalDate data, LocalTime ora, TipoAppuntamento tipo, Uffici ufficio, String descrizione) {
        this.data = data;
        this.ora = ora;
        this.tipo = tipo;
        this.ufficio = ufficio;
        this.descrizione = descrizione;
    }

    public Long getId() {
        return this.id;
    }

    public LocalDate getDate() {
        return this.data;
    }

    public LocalTime getOra() {
        return this.ora;
    }

    public Uffici getUfficio() {
        return this.ufficio;
    }

    public TipoAppuntamento getTipoAppuntamento() {
        return this.tipo;
    }

    public String getDescrizione() {
        return this.descrizione;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public void setTipoAppuntamento(TipoAppuntamento tipo) {
        this.tipo = tipo;
    }

    public void setUfficio(Uffici ufficio) {
        this.ufficio = ufficio;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public boolean equals (Objects o) {
        
        if(this == o)
            return true;

        if(!(o instaceof Appuntamenti))
            return false;

        Appuntamenti appuntamenti = (Appuntamenti) o;
        return Objects.equals(this.id, appuntamenti.id) && Objects.equals(this.data, appuntamenti.data) && Objects.equals(this.ora, appuntamenti.ora)
                && Objects.equals(this.tipo, appuntamenti.tipo) && Objects.equals(this.ufficio, appuntamenti.ufficio)
                && Objects.equals(this.descrizione, appuntamenti.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.data, this.ora, this.tipo, this.ufficio, this.descrizione);
    }

    @Override
    public String toString() {
        return "Appuntamento{" + "id=" + this.id + ", data=" + this.data + ", ora='" + this.ora
                + "', tipo_appuntamento='" + this.tipo
                + "', ufficio='" + this.ufficio + "', descrzione='" + this.descrizione + "'}";
    }
}