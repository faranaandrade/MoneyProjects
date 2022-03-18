package com.faaya.moneyprojects.beans;

import android.database.Cursor;

import java.io.Serializable;
import java.util.Date;

public class Calendar implements Serializable {

    private Long id;
    private Long elemento;
    private Long obra;
    private Double costo;
    private Long date;

    public Calendar() {

    }

    public Calendar(Cursor cursor) {
        setId(cursor.getLong(0));
        setObra(cursor.getLong(1));
        setElemento(cursor.getLong(2));
        setDate(cursor.getLong(3));
        setCosto(cursor.getDouble(4));
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getElemento() {
        return elemento;
    }

    public void setElemento(Long elemento) {
        this.elemento = elemento;
    }

    public Long getObra() {
        return obra;
    }

    public void setObra(Long obra) {
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

    public Date getFecha() {
        return new Date(date);
    }
}
