package com.example.material_design3_maana.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.material_design3_maana.R;
import com.example.material_design3_maana.models.Pelicula;

import java.util.ArrayList;

public class Lista1Adapter extends RecyclerView.Adapter<Lista1Adapter.MyViewHolder> {

    ArrayList<Pelicula> peliculas;
    Context context;

    public Lista1Adapter(Context context, ArrayList<Pelicula> listaPeliculas) {
        this.context = context;
        this.peliculas = listaPeliculas;
    }

    @NonNull
    @Override
    public Lista1Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista1_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Lista1Adapter.MyViewHolder view, int position) {

        Pelicula item = this.peliculas.get(position);

        int escalafon  = position + 1;

        if(escalafon < 10)
            view.escalafon.setText("0" + escalafon);
        else
            view.escalafon.setText("" + escalafon);

        view.titulo.setText(item.titulo);
        view.imagen.setImageResource(context.getResources().getIdentifier(item.imagen, "mipmap", context.getPackageName()));

    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        ImageView imagen;
        TextView escalafon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);
            imagen = itemView.findViewById(R.id.imagen);
            escalafon = itemView.findViewById(R.id.escalafon);
        }
    }
}
