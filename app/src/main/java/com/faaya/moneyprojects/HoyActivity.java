package com.faaya.moneyprojects;

import android.content.Intent;
import android.os.Bundle;

import com.faaya.moneyprojects.beans.Calendar;
import com.faaya.moneyprojects.beans.Elementos;
import com.faaya.moneyprojects.database.Queries;
import com.faaya.moneyprojects.utils.Utils;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HoyActivity extends AppCompatActivity {

    public static final String ELEMENTO = "ELEMENTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoy);

        final Queries queries = new Queries(this);

        RecyclerView recyclerView = findViewById(R.id.hoyRecyclerView);
        List<Elementos> elementos = queries.getAllElementos();
        filterElementos(queries, elementos);
        ShowEdit<Elementos> showEdit = new ShowEdit<Elementos>() {
            @Override
            public void show(Elementos elementos) {
                Intent intent = new Intent(HoyActivity.this, HoyListActivity.class);
                intent.putExtra(ELEMENTO, elementos);
                startActivity(intent);
                finish();
            }
        };
        HoyAdapter adapter = new HoyAdapter(elementos, showEdit);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void filterElementos(Queries queries, List<Elementos> allElementos) {
        for (Calendar calendar : queries.getAllCalendarByDate(Utils.getPurgeCalendar())) {
            for (Elementos elemento : allElementos) {
                if (elemento.getId().longValue() == calendar.getElemento().longValue()) {
                    allElementos.remove(elemento);
                    break;
                }
            }
        }
    }
}