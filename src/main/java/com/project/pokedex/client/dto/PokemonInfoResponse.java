package com.project.pokedex.client.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PokemonInfoResponse {

    @JsonProperty("name")
    private String name;

    @JsonProperty("is_legendary")
    private Boolean isLegendary;

    @JsonProperty("habitat")
    private Habitat habitat;

    @JsonProperty("flavor_text_entries")
    private List<FlavorTextEntry> flavorTextEntries;

    // TODO: Add more attributes if necessary

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Habitat {
        @JsonProperty("name")
        private String name;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class FlavorTextEntry {
        @JsonProperty("flavor_text")
        private String flavorText;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Language {
        private String name;
    }
}
