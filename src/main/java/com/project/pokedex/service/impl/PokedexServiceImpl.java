package com.project.pokedex.service.impl;

import com.project.pokedex.client.FunTranslationClient;
import com.project.pokedex.client.PokeClient;
import com.project.pokedex.client.dto.PokemonInfoResponse;
import com.project.pokedex.client.dto.TranslationResponse;
import com.project.pokedex.dto.PokemonDto;
import com.project.pokedex.mapper.PokemonMapper;
import com.project.pokedex.service.PokedexService;
import org.springframework.stereotype.Service;

@Service
public class PokedexServiceImpl implements PokedexService {

    private final PokeClient pokeClient;
    private final FunTranslationClient translationClient;

    private final PokemonMapper pokemonMapper;

    public PokedexServiceImpl(PokeClient pokeClient, FunTranslationClient translationClient, PokemonMapper pokemonMapper) {
        this.pokeClient = pokeClient;
        this.translationClient = translationClient;
        this.pokemonMapper = pokemonMapper;
    }

    @Override
    public PokemonDto fetchPokemonInfo(String pokemonName) {
        PokemonInfoResponse pokemonInfo = pokeClient.fetchPokemonInfoByName(pokemonName);
        return pokemonMapper.toDto(pokemonInfo);
    }

    @Override
    public PokemonDto fetchPokemonTranslatedInfo(String pokemonName) {
        PokemonInfoResponse pokemonInfo = pokeClient.fetchPokemonInfoByName(pokemonName);
        PokemonDto pokemonDto = pokemonMapper.toDto(pokemonInfo);

        TranslationResponse translatedDescriptionResponse = translationClient.translateTextToYodaFormat(pokemonDto.getDescription());
        if (translatedDescriptionResponse.getContents() != null) {
            String translatedDescription = translatedDescriptionResponse.getContents().getTranslated();
            pokemonDto.setDescription(translatedDescription);
        }

        return pokemonDto;
    }
}
