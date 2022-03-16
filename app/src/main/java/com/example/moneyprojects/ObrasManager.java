package com.example.moneyprojects;


import com.example.moneyprojects.beans.Elementos;
import com.example.moneyprojects.beans.Obras;

interface ObrasManager {
    void add(Obras obras);
    void delete(Long id);
}
