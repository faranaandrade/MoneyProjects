package com.example.moneyprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.moneyprojects.beans.Elementos;
import com.example.moneyprojects.beans.Obras;
import com.example.moneyprojects.database.Queries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class ObrasActivity extends AppCompatActivity {

    private List<Obras> allObrasSinTerminar;
    private List<Obras> allObrasTerminadas;
    private ObrasAdapter obrasTerminadasAdapter;
    private ObrasAdapter obrasSinTerminarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_obras);

        final Queries queries = new Queries(this);

        WebGetter webGetter = new WebGetter() {
            @Override
            public void go(String url) {
                try {
                    Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( url ) );
                    startActivity( browse );
                } catch (ActivityNotFoundException e) {
                    View view = findViewById(android.R.id.content);
                    Snackbar.make(view, "No es una URL válida", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        };

        RecyclerView listViewCuadrilla = findViewById(R.id.obrasSinTerminarList);
        allObrasSinTerminar = queries.getAllObrasSinTerminar();



        RecyclerView listViewGruas = findViewById(R.id.obrasTerminadasList);
        allObrasTerminadas = queries.getAllObrasTerminadas();

        ShowEdit<Obras> showEdit = new ShowEdit<Obras>() {
            @Override
            public void show(Obras obraEdit) {
                ObrasManager manager = new ObrasManager() {
                    @Override
                    public void add(Obras obras) {
                        queries.saveOrUpdateObras(obras);
                        if (obraEdit != null) {
                            allObrasSinTerminar.remove(obraEdit);
                            allObrasTerminadas.remove(obraEdit);
                        }
                        if (obras.getTerminada() == Obras.SIN_TERMINAR) {
                            allObrasSinTerminar.add(obras);
                        } else {
                            allObrasTerminadas.add(obras);
                        }
                        obrasSinTerminarAdapter.notifyDataSetChanged();
                        obrasTerminadasAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void delete(Long id) {
                        queries.deleteAllReferenceAndObras(id.intValue());
                        if (obraEdit.getTerminada().intValue() == Obras.TERMINADA) {
                            allObrasTerminadas.remove(obraEdit);
                        } else {
                            allObrasSinTerminar.remove(obraEdit);
                        }
                        obrasTerminadasAdapter.notifyDataSetChanged();
                        obrasSinTerminarAdapter.notifyDataSetChanged();
                    }
                };
                AddObrasFragment.newInstance("", obraEdit, manager).show(getSupportFragmentManager(), "fragment_edit_obra");
            }
        };

        FloatingActionButton floatingActionButton = findViewById(R.id.fabObras);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEdit.show(null);
            }
        });

        obrasTerminadasAdapter = new ObrasAdapter(allObrasTerminadas, showEdit,webGetter);
        listViewGruas.setAdapter(obrasTerminadasAdapter);
        listViewGruas.setLayoutManager(new LinearLayoutManager(this));

         obrasSinTerminarAdapter = new ObrasAdapter(allObrasSinTerminar, showEdit,webGetter);
        listViewCuadrilla.setAdapter(obrasSinTerminarAdapter);
        listViewCuadrilla.setLayoutManager(new LinearLayoutManager(this));
    }
}