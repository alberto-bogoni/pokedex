package com.project.pokedex.client;

import com.project.pokedex.client.dto.TranslationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class FunTranslationClient {

    @Value("${external-service.fun-translation-base-url}")
    private String BASE_URL;

    private final RestTemplate restTemplate;

    public FunTranslationClient(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
    }

    public TranslationResponse translateTextToYodaFormat(String text) {
        String url = UriComponentsBuilder
                .fromHttpUrl(BASE_URL + "/yoda.json")
                .queryParam("text", text)
                .toUriString();

        return restTemplate.getForObject(url, TranslationResponse.class);
    }
}
