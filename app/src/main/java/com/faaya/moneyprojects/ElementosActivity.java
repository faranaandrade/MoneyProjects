package com.faaya.moneyprojects;

import android.os.Bundle;
import android.view.View;

import com.faaya.moneyprojects.beans.Elementos;
import com.faaya.moneyprojects.database.Queries;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ElementosActivity extends AppCompatActivity {

    private List<Elementos> allCuadrillas;
    private List<Elementos> allGruas;
    private CuadrillaAdapter cuadrillaAdapter;
    private GruaAdapter gruaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_elementos);

        final Queries queries = new Queries(this);

        RecyclerView listViewCuadrilla = findViewById(R.id.cuadrillaList);
        allCuadrillas = queries.getAllCuadrillas();
        ShowEdit<Elementos> showEditCuadrilla = new ShowEdit<Elementos>() {
            @Override
            public void show(Elementos elementEdit) {
                CuadrillaManager cuadrillaManager = new CuadrillaManager() {
                    @Override
                    public void add(Elementos elementos) {
                        queries.saveOrUpdateElementos(elementos);
                        if (elementEdit == null) {
                            allCuadrillas.add(elementos);
                        }
                        cuadrillaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void delete(Long id) {
                        showCuadrillaDeleteAlert(id, queries, elementEdit);
                    }
                };
                AddCuadrillaFragment.newInstance("", elementEdit, cuadrillaManager).show(getSupportFragmentManager(), "fragment_edit_cuadrilla");
            }
        };
        cuadrillaAdapter = new CuadrillaAdapter(allCuadrillas, showEditCuadrilla, getResources().getColor(R.color.gray_1), getResources().getColor(R.color.teal_700));
        listViewCuadrilla.setAdapter(cuadrillaAdapter);
        listViewCuadrilla.setLayoutManager(new LinearLayoutManager(this));


        RecyclerView listViewGruas = findViewById(R.id.gruasList);
        allGruas = queries.getAllGruas();


        ShowEdit<Elementos> showEditGrua = new ShowEdit<Elementos>() {
            @Override
            public void show(Elementos elementEdit) {
                GruasManager gruasManager = new GruasManager() {
                    @Override
                    public void add(Elementos elementos) {
                        queries.saveOrUpdateElementos(elementos);
                        if (elementEdit == null) {
                            allGruas.add(elementos);
                        }
                        gruaAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void delete(Long id) {
                        showGruaDeleteAlert(id, queries, elementEdit);
                    }
                };
                AddGruasFragment.newInstance("", elementEdit, gruasManager).show(getSupportFragmentManager(), "fragment_edit_grua");
            }
        };
        gruaAdapter = new GruaAdapter(allGruas, showEditGrua, getResources().getColor(R.color.gray_1), getResources().getColor(R.color.teal_700));
        listViewGruas.setAdapter(gruaAdapter);
        listViewGruas.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("onlciker");

                ElementosManager elementosManager = new ElementosManager() {

                    @Override
                    public void addCuadrilla(View v) {
                        showEditCuadrilla.show(null);
                    }

                    @Override
                    public void addGrua(View v) {
                        showEditGrua.show(null);
                    }
                };
                AddElementosFragment fragment = AddElementosFragment.newInstance("", elementosManager);
                fragment.show(getSupportFragmentManager(), "fragment_edit_elementos");
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    private void showGruaDeleteAlert(Long id, Queries queries, Elementos elementEdit) {
        AddDeleteFragment fragment = AddDeleteFragment.newInstance("", new DeleteManager() {
            @Override
            public void delete() {
                queries.deleteAllReferenceAndElements(id.intValue());
                allGruas.remove(elementEdit);
                gruaAdapter.notifyDataSetChanged();
            }
        });
        fragment.show(getSupportFragmentManager(), "fragment_delete_elementos");
    }

    private void showCuadrillaDeleteAlert(Long id, Queries queries, Elementos elementEdit) {
        AddDeleteFragment fragment = AddDeleteFragment.newInstance("", new DeleteManager() {
            @Override
            public void delete() {
                queries.deleteAllReferenceAndElements(id.intValue());
                allCuadrillas.remove(elementEdit);
                cuadrillaAdapter.notifyDataSetChanged();
            }
        });
        fragment.show(getSupportFragmentManager(), "fragment_delete_elementos2");
    }


}