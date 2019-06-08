package pe.edu.cibertec.pokeapi;

import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    //Creamos una instancia de forma global de retrofit
   private static final String TAG = "POKEDEX";
    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private ListaPokemonAdapter listaPokemonAdapter;
    private int offset;
    private boolean aptoParaCargar;
//    private ImageButton fotoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        fotoImageView = (ImageButton) findViewById(R.id.fotoImageView);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaPokemonAdapter = new ListaPokemonAdapter(this);
        recyclerView.setAdapter(listaPokemonAdapter);
        recyclerView.setHasFixedSize(true);

//        final LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        recyclerView.setLayoutManager(layoutManager);

        final GridLayoutManager layoutManager = new GridLayoutManager(this,3);
        recyclerView.setLayoutManager(layoutManager);


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0)
                {
                    int visibleItemCount = layoutManager.getItemCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
                    if(aptoParaCargar)
                    {
                        if((visibleItemCount+pastVisibleItems)>= totalItemCount)
                        {
                            Log.i(TAG,"Llegamos al final.");
                            aptoParaCargar = false;
                            offset += 20;
                            obtenerDatos(offset);
                        }
                    }

                }
            }
        });

        //Obteniendo la informacion de los pokemon del servicio
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        //Log.i("mensaje","Hola Mundo");

        offset = 0;

        obtenerDatos(offset);
    }

    private void obtenerDatos(int offset) {

//        Toast.makeText(this,"Hola Mundo",Toast.LENGTH_SHORT).show();

        PokemonInterface pokemonInterface = retrofit.create(PokemonInterface.class);
        Call<PokemonRespuesta> methodosearch = pokemonInterface.searchPokemon(0,offset);
        methodosearch.enqueue(new Callback<PokemonRespuesta>() {
            @Override
            public void onResponse(Call<PokemonRespuesta> call, Response<PokemonRespuesta> response) {
                aptoParaCargar = true;
                if(response.isSuccessful())
                {
                    PokemonRespuesta pokemonRespuesta = response.body();
                    ArrayList<Pokemon> listaPokemon = pokemonRespuesta.getResults();

                    listaPokemonAdapter.adicionarListaPokemon(listaPokemon);

//                    for(int i=0;i<listaPokemon.size();i++)
//                    {
//                        Pokemon p = listaPokemon.get(i);
//                        Log.i(TAG,"Pokemon: "+p.getName());
//                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonRespuesta> call, Throwable t) {
                aptoParaCargar = true;
                Log.i(TAG,"Mensaje: "+t.getMessage());
            }
        });
//        Toast.makeText(MainActivity.this,"Hola Mundo",Toast.LENGTH_SHORT).show();

    }
}
