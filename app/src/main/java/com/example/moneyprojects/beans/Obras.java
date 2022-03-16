package com.example.moneyprojects.beans;

import android.database.Cursor;

public class Obras {

    public static final int TERMINADA = 1;
    public static final int SIN_TERMINAR = 0;

    private Long id;
    private String name;
    private String documento;
    private Integer terminada;

    public Obras(){

    }

    public Obras(Cursor cursor) {
        setId(cursor.getLong(0));
        setName(cursor.getString(1));
        setDocumento(cursor.getString(2));
        setTerminada(cursor.getInt(3));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Integer getTerminada() {
        return terminada;
    }

    public void setTerminada(Integer terminada) {
        this.terminada = terminada;
    }
}
