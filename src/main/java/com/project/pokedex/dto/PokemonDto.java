package com.project.pokedex.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.pokedex.client.dto.PokemonInfoResponse;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonDto {
    private String name;
    private Boolean isLegendary;
    private String habitat;

    @Setter
    private String description;
    // TODO: Add more attributes if necessary
}
