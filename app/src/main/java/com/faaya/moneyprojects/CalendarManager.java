package com.faaya.moneyprojects;


import com.faaya.moneyprojects.beans.FullCalendar;

interface CalendarManager {
    void add(FullCalendar calendar);

    void delete(Long id);

    void goObras(FullCalendar calendar);

    void goElementos(FullCalendar calendar);
}
