package com.example.pokemonwatchlist;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class PokemonProfile extends AppCompatActivity {

    private PokeModel pokemon;
    private TextView pokemonNameTv;

    private TextView pokemonHeightTv;
    private TextView pokemonWeightTv;
    private TextView pokemonAbilitiesTv;
    private TextView pokemonMoveTv;
    private TextView pokemonIdTv;
   private TextView pokemonBaseExpTv;
   private ImageView pokemonImg;
   private Button backBtn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_profile);

        pokemon = getIntent().getParcelableExtra("pokemon");
        pokemonNameTv = findViewById(R.id.pokemonName);
        pokemonHeightTv = findViewById(R.id.pokemonHeight);
        pokemonWeightTv = findViewById(R.id.pokemonWeight);
        pokemonAbilitiesTv = findViewById(R.id.pokemonAbility);
        pokemonMoveTv = findViewById(R.id.pokemonMove);
        pokemonIdTv = findViewById(R.id.pokemonId);
        pokemonBaseExpTv = findViewById(R.id.baseExp);
        pokemonImg = findViewById(R.id.pokemonSprite);
        backBtn = findViewById(R.id.backBtn);
        
        backBtn.setOnClickListener(v -> finish());



        if (pokemon != null) {
            pokemonNameTv.setText(pokemon.getName());
            pokemonHeightTv.setText(String.format("Height: %d", pokemon.getHeight()));
            pokemonWeightTv.setText(String.format("Weight: %d", pokemon.getWeight()));
            pokemonIdTv.setText(String.format("#%d", pokemon.getId()));
            pokemonBaseExpTv.setText(String.format("Base EXP: %d", pokemon.getBaseXP()));

            // Safely build and set the abilities string
            if (pokemon.getAbilities() != null && !pokemon.getAbilities().isEmpty()) {
                StringBuilder abilitiesBuilder = new StringBuilder();
                for (PokeModel.AbilityEntry abilityEntry : pokemon.getAbilities()) {
                    if (abilitiesBuilder.length() > 0) {
                        abilitiesBuilder.append(", ");
                    }
                    abilitiesBuilder.append(abilityEntry.getAbility().getName());
                }
                pokemonAbilitiesTv.setText("Abilities: " + abilitiesBuilder.toString());
            } else {
                pokemonAbilitiesTv.setText("Abilities: Not available");
            }

            // Safely build and set the moves string
            if (pokemon.getMoves() != null && !pokemon.getMoves().isEmpty()) {
                StringBuilder movesBuilder = new StringBuilder();
                int moveLimit = Math.min(pokemon.getMoves().size(), 5); // Limit to 5 moves
                for (int i = 0; i < moveLimit; i++) {
                    if (movesBuilder.length() > 0) {
                        movesBuilder.append(", ");
                    }
                    movesBuilder.append(pokemon.getMoves().get(i).getMove().getName());
                }
                pokemonMoveTv.setText("Moves: " + movesBuilder.toString());
            } else {
                pokemonMoveTv.setText("Moves: Not available");
            }

            // Load image from URL with Glide
            if (pokemon.getOfficialArtwork() != null) {
                Glide.with(this)
                        .load(pokemon.getOfficialArtwork())
                        .into(pokemonImg);
            }
        }
    }
}
