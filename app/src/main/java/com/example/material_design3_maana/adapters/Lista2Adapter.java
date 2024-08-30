package com.example.material_design3_maana.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.material_design3_maana.R;
import com.example.material_design3_maana.models.Pelicula;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lista2Adapter extends RecyclerView.Adapter<Lista2Adapter.MyViewHolder> {

    ArrayList<String> peliculas;
    Context context;

    public Lista2Adapter(Context context, ArrayList<String> listaPeliculas) {
        this.context = context;
        this.peliculas = listaPeliculas;
    }

    @NonNull
    @Override
    public Lista2Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carrusel_item_layout, parent, false);
        return new Lista2Adapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Lista2Adapter.MyViewHolder view, int position) {

        String nombrePelicula = this.peliculas.get(position);
        descargarDatosPelicula(context, nombrePelicula, view);

    }

    private void descargarDatosPelicula(Context context, String pelicula, Lista2Adapter.MyViewHolder view) {

        String apikey = "b51ff18b"; //Solicita una por correo en www.omdbapi.com
        String url = "https://www.omdbapi.com/?t=" + pelicula + "&apikey=" + apikey;

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String titulo = response.getString("Title");
                    String imageUrl = response.getString("Poster");

                    view.titulo.setText(titulo);

                    Glide.with(context)
                            .load(imageUrl)
                            .apply(new RequestOptions())
                            .into(view.imagen);

                    // int idImage = context.getResources().getIdentifier(item.imagen, "mipmap", context.getPackageName());
                    // view.imagen.setImageResource(idImage);

                } catch (JSONException e) {
                    Log.e("msg", "Error la descargar los datos de la película: " + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error al obtener los datos de www.omdbapi.com", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titulo;
        ImageView imagen;
        //TextView escalafon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);
            imagen = itemView.findViewById(R.id.imagen);
            //escalafon = itemView.findViewById(R.id.escalafon);
        }
    }
}
