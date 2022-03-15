package com.example.moneyprojects;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneyprojects.beans.Elementos;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GruaAdapter extends RecyclerView.Adapter<GruaAdapter.ViewHolder> {

    private List<Elementos> elementosList;

    public GruaAdapter(List<Elementos> elementosList) {
        System.out.println("tamaño:" + elementosList.size());
        this.elementosList = elementosList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.grua_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Elementos elementos = elementosList.get(position);
        holder.name.setText(elementos.getName());
        holder.clasificacion.setText(elementos.getClasificacion());
        holder.costo.setText("$" + elementos.getCosto().toString());
    }

    @Override
    public int getItemCount() {
        return elementosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView clasificacion;
        public TextView costo;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            clasificacion = itemView.findViewById(R.id.textViewClasificacion);
            costo = itemView.findViewById(R.id.textViewCosto);
        }

    }
}
