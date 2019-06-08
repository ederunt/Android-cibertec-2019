package pe.edu.cibertec.pokeapi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PokemonInterface {
    @GET("pokemon")
    Call<PokemonRespuesta> searchPokemon(@Query("limit") int limit,@Query("offset") int offset);


}
