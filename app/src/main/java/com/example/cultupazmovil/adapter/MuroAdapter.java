package com.example.cultupazmovil.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cultupazmovil.R;
import com.example.cultupazmovil.ui.Muro;

import java.util.List;

public class MuroAdapter extends RecyclerView.Adapter<MuroAdapter.ViewHolder> {

    private List<Muro> verMuro;
    private Context context;

    public MuroAdapter(List<Muro> verMuro, Context context) {
        this.verMuro = verMuro;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recicler_muro, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Muro muro = verMuro.get(position);
        holder.tvTitulo.setText(muro.getTitulo());
    }

    @Override
    public int getItemCount() {
        return verMuro.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitulo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitulo = itemView.findViewById(R.id.tituloTextView);
        }
    }
}