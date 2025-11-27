package com.example.pokemonwatchlist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PokemonAdapter.OnPokemonClickListener {

    private static final String TAG = "MainActivity";
    private static final String KEY_POKEMON_LIST = "pokemon_list";

    private RecyclerView pokemonRecyclerView;
    private PokemonAdapter pokemonAdapter;
    private List<PokeModel> pokemonList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Restore the list from saved state if available
        if (savedInstanceState != null) {
            ArrayList<PokeModel> savedList = savedInstanceState.getParcelableArrayList(KEY_POKEMON_LIST);
            if (savedList != null) {
                pokemonList.addAll(savedList);
            }
        }

        // Setup RecyclerView
        pokemonRecyclerView = findViewById(R.id.pokemon_recycler_view);
        pokemonRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pokemonAdapter = new PokemonAdapter(pokemonList, this);
        pokemonRecyclerView.setAdapter(pokemonAdapter);

        // Setup Button
        Button addPokemonButton = findViewById(R.id.AddPokemon);
        addPokemonButton.setOnClickListener(v -> showAddPokemonDialog());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(KEY_POKEMON_LIST, new ArrayList<>(pokemonList));
    }

    private void showAddPokemonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add a Pokémon");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String pokemonNameOrId = input.getText().toString().toLowerCase().trim();
            if (!pokemonNameOrId.isEmpty()) {
                fetchPokemon(pokemonNameOrId);
            } else {
                Toast.makeText(MainActivity.this, "Enter a Pokémon name or ID.", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void fetchPokemon(String nameOrId) {
        ApiInterface service = RetroFitInstance.getRetrofitInstance().create(ApiInterface.class);
        Call<PokeModel> call = service.getPokemon(nameOrId);

        call.enqueue(new Callback<PokeModel>() {
            @Override
            public void onResponse(@NonNull Call<PokeModel> call, @NonNull Response<PokeModel> response) {
                if (response.isSuccessful()) {
                    PokeModel pokemon = response.body();
                    if (pokemon != null) {
                        pokemonList.add(pokemon);
                        pokemonAdapter.notifyItemInserted(pokemonList.size() - 1);
                    } else {
                        Toast.makeText(MainActivity.this, "Pokémon not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Pokémon not found", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error: " + response.code() + " " + response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PokeModel> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to fetch Pokémon", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onPokemonClick(int position) {
        Intent intent = new Intent(this, PokemonProfile.class);
        intent.putExtra("pokemon", pokemonList.get(position));
        startActivity(intent);
    }
}
