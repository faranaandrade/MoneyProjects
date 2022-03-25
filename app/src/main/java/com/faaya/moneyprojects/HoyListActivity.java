package com.faaya.moneyprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.TextView;

import com.faaya.moneyprojects.beans.Calendar;
import com.faaya.moneyprojects.beans.Elementos;
import com.faaya.moneyprojects.beans.FullCalendar;
import com.faaya.moneyprojects.beans.Obras;
import com.faaya.moneyprojects.database.Queries;
import com.faaya.moneyprojects.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

public class HoyListActivity extends AppCompatActivity {

    private Elementos elementos = null;
    public static final String ELEMENTO = "ELEMENTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoy_list);

        Bundle extras = getIntent().getExtras();
        elementos = (Elementos) extras.getSerializable(ELEMENTO);

        Queries queries = new Queries(this);

        RecyclerView recyclerView = findViewById(R.id.hoyRecyclerView);
        List<Obras> allObras = queries.getAllObras();
        ShowEdit<Obras> showEdit = new ShowEdit<Obras>() {
            @Override
            public void show(Obras obras) {
                queries.saveOrUpdateCalendar(buildCalendar(obras));
                comeBack();
            }
        };
        HoyObrasAdapter adapter = new HoyObrasAdapter(allObras, showEdit);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @NotNull
    private Calendar buildCalendar(Obras obras) {
        Calendar calendar = new Calendar();
        calendar.setDate(Utils.getPurgeCalendar().getTimeInMillis());
        calendar.setElemento(elementos.getId());
        if (elementos.getTipo().equals(Elementos.CUADRILLA)) {
            calendar.setCosto(elementos.getCosto()/5);
        } else {
            calendar.setCosto(elementos.getCosto());
        }
        calendar.setObra(obras.getId());
        return calendar;
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
        Intent intent = new Intent(HoyListActivity.this, HoyActivity.class);
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