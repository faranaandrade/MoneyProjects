package com.example.moneyprojects;


import com.example.moneyprojects.beans.FullCalendar;

interface CalendarManager {
    void add(FullCalendar calendar);

    void delete(Long id);

    void goObras(FullCalendar calendar);

    void goElementos(FullCalendar calendar);
}
