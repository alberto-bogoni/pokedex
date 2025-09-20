package com.project.pokedex.controller;

import com.project.pokedex.dto.PokemonDto;
import com.project.pokedex.service.PokedexService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PokedexController {

    private PokedexService pokedexService;

    public PokedexController(PokedexService pokedexService) {
        this.pokedexService = pokedexService;
    }

    @GetMapping("/pokemon/{pokemonName}")
    public PokemonDto fetchPokemonInfo(@PathVariable String pokemonName) {
       return pokedexService.fetchPokemonInfo(pokemonName);
    }

    @GetMapping("/pokemon/translated/{pokemonName}")
    public PokemonDto fetchPokemonTranslatedInfo(@PathVariable String pokemonName) {
        return pokedexService.fetchPokemonTranslatedInfo(pokemonName);
    }
}
