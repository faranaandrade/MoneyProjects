package com.example.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneyprojects.beans.Elementos;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CuadrillaAdapter extends RecyclerView.Adapter<CuadrillaAdapter.ViewHolder> {

    private List<Elementos> elementosList;

    public CuadrillaAdapter(List<Elementos> elementosList) {
        this.elementosList = elementosList;
    }

    @NonNull
    @NotNull
    @Override
    public CuadrillaAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cuadrilla_layout, parent, false);
        CuadrillaAdapter.ViewHolder viewHolder = new CuadrillaAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CuadrillaAdapter.ViewHolder holder, int position) {
        Elementos elementos = elementosList.get(position);
        holder.name.setText(elementos.getName());
        holder.clasificacion.setText(elementos.getClasificacion());
        holder.costo.setText("$" + elementos.getCosto().toString());
        if (elementos.getNomina().intValue() == Elementos.DESTAJO) {
            holder.pago.setText("DESTAJO");
        } else {
            holder.pago.setText("NOMINA");
        }

    }

    @Override
    public int getItemCount() {
        return elementosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView clasificacion;
        public TextView costo;
        public TextView pago;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            clasificacion = itemView.findViewById(R.id.textViewClasificacion);
            costo = itemView.findViewById(R.id.textViewCosto);
            pago = itemView.findViewById(R.id.textViewPago);
        }

    }
}
