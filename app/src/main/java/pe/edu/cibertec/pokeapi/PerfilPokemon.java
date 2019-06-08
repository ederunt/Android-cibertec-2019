package pe.edu.cibertec.pokeapi;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class PerfilPokemon extends AppCompatActivity {

    TextView nombreperfil;

    ImageView fotoPokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_pokemon2);



        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            String NombrePoke;
            String posicion;
            NombrePoke = extras.getString("nombreperfil");
            posicion = extras.getString("posicion");

            nombreperfil = (TextView) findViewById(R.id.nombreperfilTextViews);
            fotoPokemon = (ImageView) findViewById(R.id.fotoPerfil);
            //posicionPokemon = (TextView) findViewById(R.id.posicionPokemon);

            Glide.with(this)
                    // .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/+132.png")
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+posicion+".png")
                    //.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    //.into(viewHolder.fotoImageView);
                    .into(fotoPokemon);

//            fotoPokemon = (ImageView) findViewById(R.id.fotoPerfil);

//            fotoPokemon.setTextAlignment(imagepoke);

            //posicionPokemon.setText(po2sicion);
            //Toast.makeText(this,"codigo: "+posicion,Toast.LENGTH_SHORT).show();
            nombreperfil.setText(NombrePoke);

        }


    }
}
