package com.example.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.moneyprojects.beans.NameGetter;
import com.example.moneyprojects.beans.Obras;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GenericListAdapter<T extends NameGetter> extends RecyclerView.Adapter<GenericListAdapter.ViewHolder> {

    private List<T> items;
    private GoBack<T> goBack;

    public GenericListAdapter(List<T> elementosList, GoBack<T> goBack) {
        this.items = elementosList;
        this.goBack = goBack;
    }

    @NonNull
    @NotNull
    @Override
    public GenericListAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.generic_list_layout, parent, false);
        GenericListAdapter.ViewHolder viewHolder = new GenericListAdapter.ViewHolder(listItem);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the position of this Vh
                int position = viewHolder.getAdapterPosition();
                goBack.comeBack(items.get(position));
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenericListAdapter.ViewHolder holder, int position) {
        NameGetter item = items.get(position);
        holder.name.setText(item.getName());
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
