package com.faaya.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faaya.moneyprojects.beans.Elementos;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CuadrillaAdapter extends RecyclerView.Adapter<CuadrillaAdapter.ViewHolder> {

    private List<Elementos> elementosList;
    private ShowEdit<Elementos> showEdit;

    public CuadrillaAdapter(List<Elementos> elementosList, ShowEdit<Elementos> showEdit) {
        this.elementosList = elementosList;
        this.showEdit = showEdit;
    }

    @NonNull
    @NotNull
    @Override
    public CuadrillaAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.cuadrilla_layout, parent, false);
        CuadrillaAdapter.ViewHolder viewHolder = new CuadrillaAdapter.ViewHolder(listItem);
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
            name = itemView.findViewById(R.id.textViewNameTipo);
            clasificacion = itemView.findViewById(R.id.textViewClasificacion);
            costo = itemView.findViewById(R.id.textViewCosto);
            pago = itemView.findViewById(R.id.textViewPago);
        }

    }
}
