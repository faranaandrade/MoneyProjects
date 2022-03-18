package com.faaya.moneyprojects.beans;

import com.faaya.moneyprojects.database.Queries;

import java.io.Serializable;

public class FullCalendar implements Serializable {
    private Long id;
    private Double costo;
    private Long date;
    private Elementos elemento;
    private Obras obra;

    public FullCalendar(Queries queries, Calendar calendar) {
        setElemento(queries.getElemento(calendar.getElemento()));
        setObra(queries.getObra(calendar.getObra()));
        setCosto(calendar.getCosto());
        setDate(calendar.getDate());
        setId(calendar.getId());
    }

    public FullCalendar() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Elementos getElemento() {
        return elemento;
    }

    public void setElemento(Elementos elemento) {
        this.elemento = elemento;
    }

    public Obras getObra() {
        return obra;
    }

    public void setObra(Obras obra) {
        this.obra = obra;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Calendar buildCalendar() {
        Calendar calendar = new Calendar();
        calendar.setDate(getDate());
        calendar.setCosto(getCosto());
        calendar.setId(getId());
        calendar.setElemento(getElemento().getId());
        calendar.setObra(getObra().getId());
        return calendar;
    }
}
