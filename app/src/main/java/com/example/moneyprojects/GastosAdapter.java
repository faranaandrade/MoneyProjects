package com.example.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.moneyprojects.beans.Calendar;
import com.example.moneyprojects.beans.Gastos;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GastosAdapter extends RecyclerView.Adapter<GastosAdapter.ViewHolder> {


    private List<Gastos> items;

    public GastosAdapter(List<Gastos> items) {
        this.items = items;
    }

    @NonNull
    @NotNull
    @Override
    public GastosAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.gastos_layout, parent, false);
        GastosAdapter.ViewHolder viewHolder = new GastosAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GastosAdapter.ViewHolder holder, int position) {
        Gastos item = items.get(position);
        holder.clasificacion.setText(item.getElementos().getName());
        holder.gasto.setText("$" + getTotalGasto(item.getCalendars()).toString());
    }

    private Double getTotalGasto(List<Calendar> calendarList) {
        Double total = new Double(0);
        for (Calendar calendar : calendarList) {
            total += calendar.getCosto();
        }
        return total;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView clasificacion;
        public TextView gasto;

        public ViewHolder(View itemView) {
            super(itemView);
            gasto = itemView.findViewById(R.id.textViewNameGasto);
            clasificacion = itemView.findViewById(R.id.textViewNameClasificacion);
        }

    }
}
