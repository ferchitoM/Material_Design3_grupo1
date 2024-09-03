package com.example.material_design3_maana;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

public class PeliculaActivity extends AppCompatActivity {

    TextView titulo;
    TextView descripcion;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pelicula);

        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcion);
        imagen = findViewById(R.id.imagen);

        //Obtenemos la información de la pelicula
        Intent intent = getIntent();
        String detallePelicula = intent.getStringExtra("detallePelicula");

        //Convertimos el Json en un objeto
        InfoPelicula info = new Gson().fromJson(detallePelicula, InfoPelicula.class);

        //Mostramos la información de la pelicula
        titulo.setText(info.Title);
        descripcion.setText(info.Plot);
        Glide.with(this)
                .load(info.Poster)
                .apply(new RequestOptions())
                .into(imagen); //donde deseamos que se muestre la imagenUrl
    }

    public class InfoPelicula {
        String Title;
        String Plot;
        String Poster;

        public InfoPelicula(String titulo, String descripcion, String imagen) {
            this.Title = titulo;
            this.Plot = descripcion;
            this.Poster = imagen;
        }
    }
}