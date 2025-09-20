package com.project.pokedex;

import com.project.pokedex.client.FunTranslationClient;
import com.project.pokedex.client.PokeClient;
import com.project.pokedex.client.dto.PokemonInfoResponse;
import com.project.pokedex.client.dto.TranslationResponse;
import com.project.pokedex.dto.PokemonDto;
import com.project.pokedex.mapper.PokemonMapper;
import com.project.pokedex.service.impl.PokedexServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PokedexServiceImplTest {

    @Mock
    private PokeClient pokeClient;

    @Mock
    private FunTranslationClient translationClient;

    @Mock
    private PokemonMapper pokemonMapper;

    @InjectMocks
    private PokedexServiceImpl pokedexService;

    private PokemonInfoResponse pokemonInfoResponse;
    private PokemonDto pokemonDto;
    private TranslationResponse translationResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        pokemonInfoResponse = new PokemonInfoResponse();
        pokemonInfoResponse.setName("ditto");
        pokemonInfoResponse.setIsLegendary(false);
        pokemonInfoResponse.setHabitat(new PokemonInfoResponse.Habitat("urban"));
        pokemonInfoResponse.setFlavorTextEntries(List.of(
                new PokemonInfoResponse.FlavorTextEntry("Capable of copying an enemy's genetic code.")
        ));

        pokemonDto = new PokemonDto("ditto", false, "urban", "Capable of copying an enemy's genetic code.");
        TranslationResponse.Contents contents = new TranslationResponse.Contents("Copying enemy's genetic code, capable it is.");
        translationResponse = new TranslationResponse(contents);
    }

    @Test
    void testFetchPokemonInfo() {
        when(pokeClient.fetchPokemonInfoByName("ditto")).thenReturn(pokemonInfoResponse);
        when(pokemonMapper.toDto(pokemonInfoResponse)).thenReturn(pokemonDto);

        PokemonDto result = pokedexService.fetchPokemonInfo("ditto");

        assertNotNull(result);
        assertEquals("ditto", result.getName());
        assertFalse(result.getIsLegendary());
        assertEquals("urban", result.getHabitat());
        assertEquals("Capable of copying an enemy's genetic code.", result.getDescription());
    }

    @Test
    void testFetchPokemonTranslatedInfo() {
        when(pokeClient.fetchPokemonInfoByName("ditto")).thenReturn(pokemonInfoResponse);
        when(pokemonMapper.toDto(pokemonInfoResponse)).thenReturn(pokemonDto);
        when(translationClient.translateTextToYodaFormat(pokemonDto.getDescription())).thenReturn(translationResponse);

        PokemonDto result = pokedexService.fetchPokemonTranslatedInfo("ditto");

        assertNotNull(result);
        assertEquals("ditto", result.getName());
        assertEquals("Copying enemy's genetic code, capable it is.", result.getDescription());
    }

    @Test
    void testFetchPokemonInfoWithNoFlavorText() {
        pokemonInfoResponse.setFlavorTextEntries(Collections.emptyList());
        PokemonDto dtoWithoutFlavorText = new PokemonDto("ditto", false, "urban", "ND");

        when(pokeClient.fetchPokemonInfoByName("ditto")).thenReturn(pokemonInfoResponse);
        when(pokemonMapper.toDto(pokemonInfoResponse)).thenReturn(dtoWithoutFlavorText);

        PokemonDto result = pokedexService.fetchPokemonInfo("ditto");

        assertNotNull(result);
        assertEquals("ND", result.getDescription());
    }

    @Test
    void testFetchPokemonTranslatedInfoWithNullTranslation() {
        TranslationResponse nullTranslation = new TranslationResponse(null);

        when(pokeClient.fetchPokemonInfoByName("ditto")).thenReturn(pokemonInfoResponse);
        when(pokemonMapper.toDto(pokemonInfoResponse)).thenReturn(pokemonDto);
        when(translationClient.translateTextToYodaFormat(pokemonDto.getDescription())).thenReturn(nullTranslation);

        PokemonDto result = pokedexService.fetchPokemonTranslatedInfo("ditto");

        assertEquals(pokemonDto.getDescription(), result.getDescription());
    }

    // --- Additional tests that would be useful ---
    //  Test behavior when pokeClient throws an exception (e.g., Pokemon not found)
    //  Test behavior when translationClient throws an exception (API down or 429 rate limit)
    //  Test Pokemon with null habitat
}
