package com.example.moneyprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddGruasFragment extends DialogFragment {

    private static GruasManager manager;

    public AddGruasFragment() {

    }

    public static AddGruasFragment newInstance(String title, GruasManager gruasManager) {
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



        Button addCuadrillaButton = view.findViewById(R.id.buttonAddGrua);
        addCuadrillaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.add();
                dismiss();
            }
        });

        Button addGruaButton = view.findViewById(R.id.eliminarGrua);
        addGruaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.delete();
                dismiss();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}