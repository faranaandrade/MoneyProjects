package com.faaya.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.faaya.moneyprojects.beans.Obras;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HoyObrasAdapter extends RecyclerView.Adapter<HoyObrasAdapter.ViewHolder> {

    private List<Obras> items;
    private ShowEdit<Obras> showEdit;

    public HoyObrasAdapter(List<Obras> elementosList, ShowEdit<Obras> showEdit) {
        this.items = elementosList;
        this.showEdit = showEdit;
    }

    @NonNull
    @NotNull
    @Override
    public HoyObrasAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.hoy_obras_layout, parent, false);
        HoyObrasAdapter.ViewHolder viewHolder = new HoyObrasAdapter.ViewHolder(listItem);
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
    public void onBindViewHolder(HoyObrasAdapter.ViewHolder holder, int position) {
        Obras obras = items.get(position);
        holder.name.setText(obras.getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
        }

    }
}
