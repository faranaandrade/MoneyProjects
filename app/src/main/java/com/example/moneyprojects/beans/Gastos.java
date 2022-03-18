package com.example.moneyprojects.beans;

import java.util.List;

public class Gastos {
    private Obras obras;
    private List<Calendar> calendars;
    private Elementos elementos;

    public Gastos(Obras obras, List<Calendar> calendars, Elementos elementos){
        this.obras = obras;
        this.calendars = calendars;
        this.elementos = elementos;
    }

    public Obras getObras() {
        return obras;
    }

    public void setObras(Obras obras) {
        this.obras = obras;
    }

    public List<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }

    public Elementos getElementos() {
        return elementos;
    }

    public void setElementos(Elementos elementos) {
        this.elementos = elementos;
    }
}
