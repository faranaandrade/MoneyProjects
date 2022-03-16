package com.example.moneyprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.example.moneyprojects.beans.Elementos;
import com.example.moneyprojects.beans.Obras;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddObrasFragment extends DialogFragment {

    private static Obras obrasEdit;
    private static ObrasManager manager;

    public AddObrasFragment() {

    }

    public static AddObrasFragment newInstance(String title, Obras obrasEdit, ObrasManager obrasManager) {
        AddObrasFragment.obrasEdit = obrasEdit;
        AddObrasFragment.manager = obrasManager;
        AddObrasFragment frag = new AddObrasFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_obra, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        EditText nombre = view.findViewById(R.id.editTextNombre);
        EditText documento = view.findViewById(R.id.editTextDocumento);
        Switch terminada = view.findViewById(R.id.switchTerminada);
        Button okButton = view.findViewById(R.id.buttonAddObra);
        Button eliminarObraButton = view.findViewById(R.id.eliminarObra);

        if (obrasEdit != null) {
            nombre.setText(obrasEdit.getName());
            if (obrasEdit.getDocumento() != null && !obrasEdit.getDocumento().isEmpty()) {
                documento.setText(obrasEdit.getDocumento());
            }
            if (obrasEdit.getTerminada().intValue() == Obras.TERMINADA) {
                terminada.setChecked(true);
            }
            okButton.setText("EDITAR");
            eliminarObraButton.setVisibility(View.VISIBLE);
        } else {
            eliminarObraButton.setVisibility(View.GONE);
        }

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (obrasEdit == null) {
                    obrasEdit = new Obras();
                }
                obrasEdit.setName(nombre.getText().toString());
                obrasEdit.setDocumento(documento.getText().toString());
                if (terminada.isChecked()) {
                    obrasEdit.setTerminada(Obras.TERMINADA);
                } else {
                    obrasEdit.setTerminada(Obras.SIN_TERMINAR);
                }
                if (isValid(obrasEdit)) {
                    manager.add(obrasEdit);
                    dismiss();
                } else {
                    Snackbar.make(view, "Se necesita nombre", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });


        eliminarObraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.delete(obrasEdit.getId());
                dismiss();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private boolean isValid(Obras obras) {
        return obras.getName() != null && !obras.getName().isEmpty();
    }

}