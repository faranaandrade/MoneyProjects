package com.faaya.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.faaya.moneyprojects.beans.FullCalendar;
import com.faaya.moneyprojects.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder> {

    private List<FullCalendar> items;
    private ShowEdit<FullCalendar> showEdit;

    public CalendarAdapter(List<FullCalendar> elementosList, ShowEdit<FullCalendar> showEdit) {
        this.items = elementosList;
        this.showEdit = showEdit;
    }

    @NonNull
    @NotNull
    @Override
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.calendar_layout, parent, false);
        CalendarAdapter.ViewHolder viewHolder = new CalendarAdapter.ViewHolder(listItem);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the position of this Vh
                int position = viewHolder.getAdapterPosition();
                showEdit.show(items.get(position));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CalendarAdapter.ViewHolder holder, int position) {
        FullCalendar calendar = items.get(position);
        holder.nameElemento.setText(Utils.cutString(calendar.getElemento().getName(), 31));
        holder.clasificacion.setText(Utils.cutString(calendar.getElemento().getClasificacion(),16));
        holder.obra.setText(Utils.cutString(calendar.getObra().getName(), 16));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView obra;
        public TextView clasificacion;
        public TextView nameElemento;

        public ViewHolder(View itemView) {
            super(itemView);
            obra = itemView.findViewById(R.id.textViewNameObra);
            clasificacion = itemView.findViewById(R.id.textViewNameClasificacion);
            nameElemento = itemView.findViewById(R.id.nameElemento);
        }

    }
}
