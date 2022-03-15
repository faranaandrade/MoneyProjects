package com.example.moneyprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCuadrillaFragment extends DialogFragment {

    private static CuadrillaManager manager;

    public AddCuadrillaFragment() {

    }

    public static AddCuadrillaFragment newInstance(String title, CuadrillaManager cuadrillaManager) {
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



        Button okButton = view.findViewById(R.id.buttonAddCuadrilla);
        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.add();
                dismiss();
            }
        });

        Button eliminarCuadrillaButton = view.findViewById(R.id.eliminarCuadrilla);
        eliminarCuadrillaButton.setOnClickListener(new View.OnClickListener() {
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