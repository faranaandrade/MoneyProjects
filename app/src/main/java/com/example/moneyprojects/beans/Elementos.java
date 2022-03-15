package com.example.moneyprojects.beans;

import android.database.Cursor;

public class Elementos {
    public static final String GRUA = "GRUA";
    public static final int DESTAJO = 0;
    public static final int NOMINA = 1;
    public static final String CUADRILLA = "CUADRILLA";
    private Long id;
    private String name;
    private Double costo;
    private String clasificacion;
    private String tipo;
    private Integer nomina;

    public Elementos() {

    }

    public Elementos(Cursor cursor) {
        setId(cursor.getLong(0));
        setName(cursor.getString(1));
        setClasificacion(cursor.getString(2));
        setCosto(cursor.getDouble(3));
        setTipo(cursor.getString(4));
        setNomina(cursor.getInt(5));
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

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getNomina() {
        return nomina;
    }

    public void setNomina(Integer nomina) {
        this.nomina = nomina;
    }
}
