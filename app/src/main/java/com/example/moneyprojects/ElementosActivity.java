package com.example.moneyprojects;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.moneyprojects.beans.Elementos;
import com.example.moneyprojects.database.Queries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ElementosActivity extends AppCompatActivity {

    private List<Elementos> allCuadrillas;
    private List<Elementos> allGruas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_elementos);

        final Queries queries = new Queries(this);

        RecyclerView listViewCuadrilla = findViewById(R.id.cuadrillaList);
        allCuadrillas = queries.getAllCuadrillas();
        CuadrillaAdapter cuadrillaAdapter = new CuadrillaAdapter(allCuadrillas);
        listViewCuadrilla.setAdapter(cuadrillaAdapter);
        listViewCuadrilla.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView listViewGruas = findViewById(R.id.gruasList);
        allGruas = queries.getAllGruas();
        GruaAdapter gruaAdapter = new GruaAdapter(allGruas);
        listViewGruas.setAdapter(gruaAdapter);
        listViewGruas.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("onlciker");

                ElementosManager elementosManager = new ElementosManager(){

                    @Override
                    public void addCuadrilla(View v) {
                        CuadrillaManager cuadrillaManager = new CuadrillaManager() {
                            @Override
                            public void add(Elementos elementos) {
                                queries.saveOrUpdateElementos(elementos);
                                allCuadrillas.add(elementos);
                                cuadrillaAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void delete(Long id) {
                                System.out.println("deleteCuadrilla");
                            }
                        };
                        AddCuadrillaFragment.newInstance("", cuadrillaManager).show(getSupportFragmentManager(), "fragment_edit_internet");
                    }

                    @Override
                    public void addGrua(View v) {
                        GruasManager gruasManager = new GruasManager() {
                            @Override
                            public void add(Elementos elementos) {
                                queries.saveOrUpdateElementos(elementos);
                                allGruas.add(elementos);
                                gruaAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void delete(Long id) {
                                System.out.println("deleteGrua");
                            }
                        };
                        AddGruasFragment.newInstance("", gruasManager).show(getSupportFragmentManager(), "fragment_edit_internet");
                    }
                };
                AddElementosFragment fragment = AddElementosFragment.newInstance("", elementosManager);
                fragment.show(getSupportFragmentManager(), "fragment_edit_elementos");
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });




        System.out.println("finFinal");
    }
}