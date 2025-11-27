package com.example.pokemonwatchlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.PokemonViewHolder> {

    private List<PokeModel> pokemonList;
    private OnPokemonClickListener onPokemonClickListener;

    public PokemonAdapter(List<PokeModel> pokemonList, OnPokemonClickListener onPokemonClickListener) {
        this.pokemonList = pokemonList;
        this.onPokemonClickListener = onPokemonClickListener;
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pokemon_list_item, parent, false);
        return new PokemonViewHolder(view, onPokemonClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        PokeModel pokemon = pokemonList.get(position);
        holder.pokemonNameTextView.setText(pokemon.getName());
    }

    @Override
    public int getItemCount() {
        return pokemonList.size();
    }

    public static class PokemonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView pokemonNameTextView;
        public OnPokemonClickListener onPokemonClickListener;

        public PokemonViewHolder(@NonNull View itemView, OnPokemonClickListener onPokemonClickListener) {
            super(itemView);
            pokemonNameTextView = itemView.findViewById(R.id.pokemon_name);
            this.onPokemonClickListener = onPokemonClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onPokemonClickListener.onPokemonClick(getAdapterPosition());
        }
    }

    public interface OnPokemonClickListener {
        void onPokemonClick(int position);
    }
}
