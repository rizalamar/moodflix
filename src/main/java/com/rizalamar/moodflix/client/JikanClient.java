package com.rizalamar.moodflix.client;

import com.rizalamar.moodflix.dto.anime.AnimeResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class JikanClient {

    private final RestClient.Builder builder;

    @Value("${app.api.jikan.url}")
    private String baseUrl;

    private RestClient restClient;

    @PostConstruct
    public void init(){
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public AnimeResponse searchAnimeByGenre(String genreId){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/anime")
                        .queryParam("genres", genreId)
                        .queryParam("order_by", "score")
                        .queryParam("sort", "desc")
                        .build()
                ).retrieve()
                .body(AnimeResponse.class);
    }

}
