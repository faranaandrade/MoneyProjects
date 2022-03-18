package com.example.moneyprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.moneyprojects.beans.Calendar;
import com.example.moneyprojects.beans.Elementos;
import com.example.moneyprojects.beans.Gastos;
import com.example.moneyprojects.beans.Obras;
import com.example.moneyprojects.database.Queries;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GastosActivity extends AppCompatActivity {

    public static final String OBRA = "OBRA";

    private Obras obras;
    private List<Gastos> gastos;
    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gastos);

        Bundle extras = getIntent().getExtras();
        obras = (Obras) extras.getSerializable(OBRA);

        Queries queries = new Queries(this);
        List<Calendar> allCalendarByObra = queries.getAllCalendarByObra(obras.getId());

        TextView textViewTituloGastos = findViewById(R.id.textViewTituloGastos);
        textViewTituloGastos.setText(obras.getName());

        getGastos(queries, allCalendarByObra);
        GastosAdapter gastosAdapater = new GastosAdapter(gastos);
        RecyclerView recyclerView = findViewById(R.id.gastoRecyclerView);
        recyclerView.setAdapter(gastosAdapater);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TextView textViewTotal = findViewById(R.id.textViewTotal);
        textViewTotal.setText("TOTAL: $" + total);
    }

    @NotNull
    private void getGastos(Queries queries, List<Calendar> allCalendarByObra) {
        Map<Long, List<Calendar>> map = new HashMap<Long, List<Calendar>>();
        for (Calendar calendar : allCalendarByObra) {
            total += calendar.getCosto();
            if (!map.containsKey(calendar.getElemento())){
                map.put(calendar.getElemento(), new ArrayList<>());
            }
            map.get(calendar.getElemento()).add(calendar);
        }

        gastos = new ArrayList<>();
        for (Map.Entry<Long, List<Calendar>> entry : map.entrySet()){
            Elementos elemento = queries.getElemento(entry.getKey());
            gastos.add(new Gastos(obras, entry.getValue(), elemento));
        }
    }
}