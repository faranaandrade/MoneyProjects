package com.example.moneyprojects;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.moneyprojects.databinding.ActivityElementosBinding;

public class ElementosActivity extends AppCompatActivity {

    private ActivityElementosBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_elementos);

        binding = ActivityElementosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setSupportActionBar(binding.appBarElementos.toolbar);

        binding.appBarElementos.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GruasManager gruasManager = new GruasManager() {
                    @Override
                    public void add() {
                        System.out.println("addGrua");
                    }

                    @Override
                    public void delete() {
                        System.out.println("deletegrua");
                    }
                };
                CuadrillaManager cuadrillaManager = new CuadrillaManager() {
                    @Override
                    public void add() {
                        System.out.println("addCuadrialla");
                    }

                    @Override
                    public void delete() {
                        System.out.println("dleteCuadrilla");
                    }
                };
                ElementosManager elementosManager = new ElementosManager(){

                    @Override
                    public void addCuadrilla() {
                        AddCuadrillaFragment fragment = AddCuadrillaFragment.newInstance("", cuadrillaManager);
                        fragment.show(getSupportFragmentManager(), "fragment_edit_internet");
                    }

                    @Override
                    public void addGrua() {
                        AddGruasFragment fragment = AddGruasFragment.newInstance("", gruasManager);
                        fragment.show(getSupportFragmentManager(), "fragment_edit_internet");
                    }
                };
                AddElementosFragment fragment = AddElementosFragment.newInstance("", elementosManager);
                fragment.show(getSupportFragmentManager(), "fragment_edit_elementos");
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }
}