package pe.edu.cibertec.pokeapi.View.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pe.edu.cibertec.pokeapi.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonFragment extends Fragment {


    public PokemonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon, container, false);
    }

}
