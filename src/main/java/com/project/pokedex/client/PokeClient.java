package com.project.pokedex.client;

import com.project.pokedex.client.dto.PokemonInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class PokeClient {

    @Value("${external-service.poke-api-base-url}")
    private String BASE_URL;

    private RestTemplate restTemplate;

    public PokeClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PokemonInfoResponse fetchPokemonInfoByName(String name) {
        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL)
                .path("/pokemon-species/{name}")
                .buildAndExpand(name)
                .toUriString();

        return restTemplate.getForObject(url, PokemonInfoResponse.class);
    }
}
