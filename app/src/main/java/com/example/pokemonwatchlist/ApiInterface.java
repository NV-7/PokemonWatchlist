package com.example.pokemonwatchlist;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("pokemon/{name}")
    Call<PokeModel> getPokemon(@Path("name") String name);
}
