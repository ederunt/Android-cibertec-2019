package pe.edu.cibertec.pokeapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ListaPokemonAdapter extends RecyclerView.Adapter<ListaPokemonAdapter.ViewHolder> {

    private ArrayList<Pokemon> dataset;
    private Context context;
    //private int Pos = 0;

    //Pokemon p = new Pokemon();

    public ListaPokemonAdapter(Context context)
    {
        this.context = context;
        dataset = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = null;
        ViewHolder vh;
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_pokemon,viewGroup,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListaPokemonAdapter.ViewHolder viewHolder, final int i) {
        //recorriendo la lista y ir llenando las tarjetas
        //Pokemon p = dataset.get(i);
        Pokemon p = dataset.get(i);
        String cod = ""+p.getNumber();
       // viewHolder.posicionTextView.setText(p.getNumber());
        viewHolder.nombreTextView.setText(p.getName());
        viewHolder.posicionTextView.setText(cod);





        Glide.with(context)
                // .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/+132.png")
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                //.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+p.getNumber()+".png")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                //.into(viewHolder.fotoImageView);
                .into(viewHolder.fotoImageView);

        viewHolder.fotoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PerfilPokemon.class);

                intent.putExtra("nombreperfil",viewHolder.nombreTextView.getText());
                intent.putExtra("posicion",viewHolder.posicionTextView.getText());
                //intent.putExtra("foto",viewHolder.fotoImageView.getImageAlpha());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void adicionarListaPokemon(ArrayList<Pokemon> listaPokemon) {
        dataset.addAll(listaPokemon);
        notifyDataSetChanged();
    }

    // esta clase solo se va a concentrar en un card
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView fotoImageView;
        private TextView nombreTextView;
        private TextView posicionTextView;

        public ViewHolder(View itemView)
        {
            super(itemView);
                fotoImageView = (ImageView) itemView.findViewById(R.id.fotoImageView);
                nombreTextView = (TextView) itemView.findViewById(R.id.nombreTextView);
                posicionTextView = (TextView) itemView.findViewById(R.id.posicionPokemon);

            posicionTextView.setVisibility(View.INVISIBLE);
        }
    }
}
