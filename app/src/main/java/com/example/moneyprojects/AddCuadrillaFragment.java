package com.example.moneyprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.moneyprojects.beans.Elementos;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCuadrillaFragment extends DialogFragment {

    private static Elementos elementEdit;
    private static CuadrillaManager manager;

    public AddCuadrillaFragment() {

    }

    public static AddCuadrillaFragment newInstance(String title, Elementos elementEdit, CuadrillaManager cuadrillaManager) {
        AddCuadrillaFragment.elementEdit = elementEdit;

        AddCuadrillaFragment.manager = cuadrillaManager;
        AddCuadrillaFragment frag = new AddCuadrillaFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_cuadrilla, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText costo = view.findViewById(R.id.editTextCostoSemanal);
        EditText nombre = view.findViewById(R.id.editTextNombre);
        EditText clasificacion = view.findViewById(R.id.editTextClasificacion);
        Switch nomina = view.findViewById(R.id.switchNomina);
        Button okButton = view.findViewById(R.id.buttonAddCuadrilla);
        Button eliminarCuadrillaButton = view.findViewById(R.id.eliminarCuadrilla);

        if (elementEdit != null) {
            costo.setText(elementEdit.getCosto().toString());
            nombre.setText(elementEdit.getName());
            clasificacion.setText(elementEdit.getClasificacion());
            if (elementEdit.getNomina().intValue() == Elementos.NOMINA) {
                nomina.setChecked(true);
            }
            okButton.setText("EDITAR");
            eliminarCuadrillaButton.setVisibility(View.VISIBLE);
        } else {
            eliminarCuadrillaButton.setVisibility(View.GONE);
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (elementEdit == null) {
                    elementEdit = new Elementos();
                }
                elementEdit.setName(nombre.getText().toString());
                elementEdit.setClasificacion(clasificacion.getText().toString());
                if (!costo.getText().toString().isEmpty()) {
                    elementEdit.setCosto(Double.parseDouble(costo.getText().toString()));
                }
                elementEdit.setTipo(Elementos.CUADRILLA);
                if (nomina.isChecked()) {
                    elementEdit.setNomina(Elementos.NOMINA);
                } else {
                    elementEdit.setNomina(Elementos.DESTAJO);
                }
                if (isValid(elementEdit)) {
                    manager.add(elementEdit);
                    dismiss();
                } else {
                    Snackbar.make(view, "Se necesitan datos completos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });


        eliminarCuadrillaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.delete(elementEdit.getId());
                dismiss();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private boolean isValid(Elementos elementos) {
        return elementos.getName() != null && !elementos.getName().isEmpty() &&
                elementos.getCosto() != null && elementos.getCosto().doubleValue() > 0 &&
                elementos.getClasificacion() != null && !elementos.getClasificacion().isEmpty();
    }

}