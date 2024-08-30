package com.example.material_design3_maana;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.material_design3_maana.adapters.Lista1Adapter;
import com.example.material_design3_maana.adapters.Lista2Adapter;
import com.example.material_design3_maana.models.Pelicula;
import com.google.android.material.carousel.CarouselLayoutManager;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView lista1;
    ArrayList<Pelicula> peliculasTop;
    Lista1Adapter adapter1;

    RecyclerView lista2;
    ArrayList<String> peliculasRecientes;
    Lista2Adapter adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        peliculasTop = new ArrayList<>();
        peliculasTop.add( new Pelicula("Furiosa: Mad Max saga", "movie1"));
        peliculasTop.add( new Pelicula("Mi villano favorito 4", "movie2"));
        peliculasTop.add( new Pelicula("Deadpool & Wolverine", "movie3"));

        //Configuramos la lista de peliculas Top 10
        lista1 = findViewById(R.id.lista1);
        lista1.setLayoutManager( new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false) );
        adapter1 = new Lista1Adapter(this, peliculasTop);
        lista1.setAdapter(adapter1);

        peliculasRecientes = new ArrayList<>();
        peliculasRecientes.add("Minions"); //1
        peliculasRecientes.add("Kung Fu Panda 4"); //2
        peliculasRecientes.add("Godzilla x Kong"); //3
        peliculasRecientes.add("Alien romulus"); //4
        peliculasRecientes.add("Deadpool & Wolverine"); //5
        peliculasRecientes.add("Dune: Part Two"); //6
        peliculasRecientes.add("Kingdom of the Planet"); //7
        peliculasRecientes.add("The Fall Guy"); //8
        peliculasRecientes.add("Despicable Me 4"); //9
        peliculasRecientes.add("Madame Web"); //10

        //Configuramos el carrusel de peliculas recientes
        lista2 = findViewById(R.id.lista2);
        lista2.setLayoutManager(new CarouselLayoutManager());
        adapter2 = new Lista2Adapter(this, peliculasRecientes);
        lista2.setAdapter(adapter2);


    }
}