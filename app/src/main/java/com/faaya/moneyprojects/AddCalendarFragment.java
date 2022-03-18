package com.faaya.moneyprojects;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.faaya.moneyprojects.beans.Elementos;
import com.faaya.moneyprojects.beans.FullCalendar;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddCalendarFragment extends DialogFragment {

    private static FullCalendar calendarEdit;
    private static CalendarManager manager;
    private static Long date;
    ImageButton obraButton;
    ImageButton elementoButton;
    EditText costoEditText;
    Button okButton;
    Button eliminarObraButton;
    TextView textViewObra;
    TextView textViewElemento;
    TextView textViewCosto;

    public AddCalendarFragment() {

    }

    public static AddCalendarFragment newInstance(String title, FullCalendar calendarEdit, CalendarManager manager, Long date) {
        AddCalendarFragment.calendarEdit = calendarEdit;
        AddCalendarFragment.manager = manager;
        AddCalendarFragment.date = date;
        AddCalendarFragment frag = new AddCalendarFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_calendar, container);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewCosto = view.findViewById(R.id.textViewCosto);

        obraButton = view.findViewById(R.id.imageButtonObras);
        elementoButton = view.findViewById(R.id.imageButtonElementos);

        textViewElemento = view.findViewById(R.id.textViewElemento);
        textViewObra = view.findViewById(R.id.textViewObra);

        costoEditText = view.findViewById(R.id.editTextCCosto);
        okButton = view.findViewById(R.id.buttonAddObra);
        eliminarObraButton = view.findViewById(R.id.eliminarObra);

        if (calendarEdit != null) {
            if (calendarEdit.getCosto() != null) {
                costoEditText.setText(calendarEdit.getCosto().toString());
            }
            if (calendarEdit.getId() == null && calendarEdit.getElemento() != null) {
                Elementos elemento = calendarEdit.getElemento();
                if (elemento.getTipo().equals(Elementos.CUADRILLA)) {
                    Double costo = elemento.getCosto() / 5;
                    costoEditText.setText(costo.toString());
                } else {
                    costoEditText.setText(elemento.getCosto().toString());
                }
            }
            if (calendarEdit.getObra() != null) {
                textViewObra.setText(calendarEdit.getObra().getName());
            }

            if (calendarEdit.getElemento() != null) {
                textViewElemento.setText(calendarEdit.getElemento().getName());
            }

            if (calendarEdit.getId() != null) {
                eliminarObraButton.setVisibility(View.VISIBLE);
                costoEditText.setVisibility(View.VISIBLE);
                textViewCosto.setVisibility(View.VISIBLE);
                okButton.setText("EDITAR");
            } else {
                eliminarObraButton.setVisibility(View.GONE);
                costoEditText.setVisibility(View.GONE);
                textViewCosto.setVisibility(View.GONE);
            }
        } else {
            eliminarObraButton.setVisibility(View.GONE);
            costoEditText.setVisibility(View.GONE);
            textViewCosto.setVisibility(View.GONE);
        }

        obraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillBean();
                manager.goObras(calendarEdit);
            }
        });

        elementoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillBean();
                manager.goElementos(calendarEdit);
            }
        });

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillBean();
                if (isValid(calendarEdit)) {
                    manager.add(calendarEdit);
                    dismiss();
                } else {
                    Snackbar.make(view, "Se necesitan datos completos", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            }
        });


        eliminarObraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manager.delete(calendarEdit.getId());
                dismiss();
            }
        });

        String title = getArguments().getString("title", "Enter Name");
        getDialog().setTitle(title);
        //getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void fillBean() {
        if (calendarEdit == null) {
            calendarEdit = new FullCalendar();
        }
        calendarEdit.setDate(date);
        if (!costoEditText.getText().toString().isEmpty()) {
            calendarEdit.setCosto(Double.parseDouble(costoEditText.getText().toString()));
        }
    }

    private boolean isValid(FullCalendar calendar) {
        return calendar.getObra() != null && calendar.getElemento() != null && calendar.getCosto() != null;
    }

}