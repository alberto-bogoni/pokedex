package com.project.pokedex.service;

import com.project.pokedex.dto.PokemonDto;

public interface PokedexService {

    PokemonDto fetchPokemonInfo(String pokemonName);
    PokemonDto fetchPokemonTranslatedInfo(String pokemonName);
}
