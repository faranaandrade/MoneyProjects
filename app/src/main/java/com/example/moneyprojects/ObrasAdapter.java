package com.example.moneyprojects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.moneyprojects.beans.Obras;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ObrasAdapter extends RecyclerView.Adapter<ObrasAdapter.ViewHolder> {

    private List<Obras> items;
    private ShowEdit<Obras> showEdit;
    private ShowEdit<Obras> showCostos;
    private WebGetter webGetter;

    public ObrasAdapter(List<Obras> elementosList, ShowEdit<Obras> showEdit, ShowEdit<Obras> showCostos, WebGetter webGetter) {
        this.items = elementosList;
        this.showEdit = showEdit;
        this.showCostos = showCostos;
        this.webGetter = webGetter;
    }

    @NonNull
    @NotNull
    @Override
    public ObrasAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.obras_layout, parent, false);
        ObrasAdapter.ViewHolder viewHolder = new ObrasAdapter.ViewHolder(listItem);
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
    public void onBindViewHolder(ObrasAdapter.ViewHolder holder, int position) {
        Obras obras = items.get(position);
        holder.name.setText(obras.getName());
        if (obras.getDocumento() == null || obras.getDocumento().isEmpty()) {
            holder.buttonDocumento.setVisibility(View.GONE);
        } else {
            holder.buttonDocumento.setVisibility(View.VISIBLE);
            holder.buttonDocumento.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    webGetter.go(obras.getDocumento());
                }
            });
        }
        holder.buttonCosto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCostos.show(obras);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageButton buttonDocumento;
        public Button buttonCosto;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            buttonDocumento = itemView.findViewById(R.id.imageButtonDocumentos);
            buttonCosto = itemView.findViewById(R.id.buttonCosto);
        }

    }
}
