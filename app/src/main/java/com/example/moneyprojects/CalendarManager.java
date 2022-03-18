package com.example.moneyprojects;


import com.example.moneyprojects.beans.Calendar;
import com.example.moneyprojects.beans.FullCalendar;
import com.example.moneyprojects.beans.Obras;

interface CalendarManager {
    void add(FullCalendar calendar);
    void delete(Long id);
    void goObras(FullCalendar calendar);
    void goElementos(FullCalendar calendar);
}
