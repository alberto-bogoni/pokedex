package com.project.pokedex.mapper;

import com.project.pokedex.client.dto.PokemonInfoResponse;
import com.project.pokedex.dto.PokemonDto;
import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {

    public PokemonDto toDto(PokemonInfoResponse response) {
        if (response == null) {
            return null;
        }

        String description = "ND";
        if (response.getFlavorTextEntries() != null && !response.getFlavorTextEntries().isEmpty()) {
            description = response.getFlavorTextEntries().get(0).getFlavorText();
            description = description
                    .replace("\n", " ")
                    .replace("\f", " ");
        }

        String habitat = response.getHabitat() != null ? response.getHabitat().getName() : "ND";

        return new PokemonDto(
                response.getName(),
                response.getIsLegendary(),
                habitat,
                description
        );
    }
}
