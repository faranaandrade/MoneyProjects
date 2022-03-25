package com.faaya.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.faaya.moneyprojects.beans.Elementos;
import com.faaya.moneyprojects.beans.FullCalendar;
import com.faaya.moneyprojects.utils.Utils;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HoyAdapter extends RecyclerView.Adapter<HoyAdapter.ViewHolder> {

    private List<Elementos> items;
    private ShowEdit<Elementos> showEdit;

    public HoyAdapter(List<Elementos> elementosList, ShowEdit<Elementos> showEdit) {
        this.items = elementosList;
        this.showEdit = showEdit;
    }

    @NonNull
    @NotNull
    @Override
    public HoyAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.hoy_layout, parent, false);
        HoyAdapter.ViewHolder viewHolder = new HoyAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(HoyAdapter.ViewHolder holder, int position) {
        Elementos elementos = items.get(position);
        holder.clasificacion.setText(Utils.cutString(elementos.getClasificacion(), 17));
        holder.nameElemento.setText(Utils.cutString(elementos.getName(), 17));
        holder.imageButtonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEdit.show(items.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageButton imageButtonSearch;
        public TextView clasificacion;
        public TextView nameElemento;

        public ViewHolder(View itemView) {
            super(itemView);
            nameElemento = itemView.findViewById(R.id.nameElemento);
            clasificacion = itemView.findViewById(R.id.textViewClasificacion);
            imageButtonSearch = itemView.findViewById(R.id.imageButtonSearch);
        }

    }
}
