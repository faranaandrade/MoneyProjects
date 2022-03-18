package com.example.moneyprojects;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.moneyprojects.beans.Elementos;
import com.example.moneyprojects.beans.FullCalendar;
import com.example.moneyprojects.beans.Obras;
import com.example.moneyprojects.database.Queries;

import java.io.Serializable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListActivity extends AppCompatActivity {

    public static final String FULL_CALENDAR = "FullCalendar";
    public static final String SHOW = "SHOW";
    public static final String OBRAS = "OBRAS";
    public static final String ELEMENTOS = "ELEMENTOS";
    public static final String DATE = "DATE";
    private FullCalendar fullCalendar;
    private Serializable date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle extras = getIntent().getExtras();
        fullCalendar = (FullCalendar) extras.getSerializable(FULL_CALENDAR);

        Queries queries = new Queries(this);
        RecyclerView recyclerView = findViewById(R.id.generic_list);

        boolean obras = extras.getBoolean(OBRAS, false);
        boolean elementos = extras.getBoolean(ELEMENTOS, false);
        if (obras) {
            showObras(queries, recyclerView);
        }
        if (elementos) {
            showElementos(queries, recyclerView);
        }
        date = extras.getSerializable(DATE);

    }

    private void showElementos(Queries queries, RecyclerView recyclerView) {
        GoBack<Elementos> goBack = new GoBack<Elementos>() {
            @Override
            public void comeBack(Elementos elementos) {
                fullCalendar.setElemento(elementos);
                ListActivity.this.comeBack();
            }
        };
        GenericListAdapter<Elementos> adapter = new GenericListAdapter(queries.getAllElementos(), goBack);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void showObras(Queries queries, RecyclerView recyclerView) {
        GoBack<Obras> goBack = new GoBack<Obras>() {
            @Override
            public void comeBack(Obras obras) {
                fullCalendar.setObra(obras);
                ListActivity.this.comeBack();
            }
        };
        GenericListAdapter<Obras> adapter = new GenericListAdapter(queries.getAllObras(), goBack);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                comeBack();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void comeBack() {
        Intent intent = new Intent(ListActivity.this, CalendarActivity.class);
        intent.putExtra(SHOW, true);
        intent.putExtra(FULL_CALENDAR, fullCalendar);
        intent.putExtra(DATE, date);
        startActivity(intent);
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            comeBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}