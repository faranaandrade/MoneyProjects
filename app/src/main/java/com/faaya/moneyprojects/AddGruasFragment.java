package com.faaya.moneyprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.faaya.moneyprojects.beans.Elementos;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddGruasFragment extends DialogFragment {

    private static Elementos elementEdit;
    private static GruasManager manager;

    public AddGruasFragment() {

    }

    public static AddGruasFragment newInstance(String title, Elementos elementEdit, GruasManager gruasManager) {
        AddGruasFragment.elementEdit = elementEdit;
        AddGruasFragment.manager = gruasManager;
        AddGruasFragment frag = new AddGruasFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_gruas, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText costo = view.findViewById(R.id.editTextCostoSemanalGrua);
        EditText nombre = view.findViewById(R.id.editTextNombreGrua);
        EditText clasificacion = view.findViewById(R.id.editTextClasificacionGrua);
        Button okButton = view.findViewById(R.id.buttonAddGrua);
        Button eliminarGruaButton = view.findViewById(R.id.eliminarGrua);

        if (elementEdit != null) {
            costo.setText(elementEdit.getCosto().toString());
            nombre.setText(elementEdit.getName());
            clasificacion.setText(elementEdit.getClasificacion());
            okButton.setText("EDITAR");
            eliminarGruaButton.setVisibility(View.VISIBLE);
        } else {
            eliminarGruaButton.setVisibility(View.GONE);
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
                elementEdit.setTipo(Elementos.GRUA);

                if (isValid(elementEdit)) {
                    manager.add(elementEdit);
                    dismiss();
                } else {
                    Snackbar.make(view, "Se necesitan datos completos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });


        eliminarGruaButton.setOnClickListener(new View.OnClickListener() {
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