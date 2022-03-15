package com.example.moneyprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddElementosFragment extends DialogFragment {

    private static ElementosManager elementosManager;

    public AddElementosFragment() {

    }

    public static AddElementosFragment newInstance(String title, ElementosManager elementosManager) {
        AddElementosFragment.elementosManager = elementosManager;
        AddElementosFragment frag = new AddElementosFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_elementos, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        Button addCuadrillaButton = view.findViewById(R.id.addCuadrilla);
        addCuadrillaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementosManager.addCuadrilla();
                dismiss();
            }
        });

        Button addGruaButton = view.findViewById(R.id.addGrua);
        addGruaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                elementosManager.addGrua();
                dismiss();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

}