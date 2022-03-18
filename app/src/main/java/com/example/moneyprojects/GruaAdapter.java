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

public class GruaAdapter extends RecyclerView.Adapter<GruaAdapter.ViewHolder> {

    private List<Elementos> elementosList;
    private ShowEdit<Elementos> showEdit;

    public GruaAdapter(List<Elementos> elementosList, ShowEdit<Elementos> showEdit) {
        this.showEdit = showEdit;
        this.elementosList = elementosList;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.grua_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the position of this Vh
                int position = viewHolder.getAdapterPosition();
                showEdit.show(elementosList.get(position));
            }
        });
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
