package com.rizalamar.moodflix.client;

import com.rizalamar.moodflix.dto.MovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class TmdbClient {
    private final RestClient.Builder builder;

    @Value("${app.api.tmdb.key}")
    private String apiKey;

    @Value("${app.api.openweathermap.url}")
    private String baseUrl;

    private RestClient restClient;

    public void init(){
        this.restClient = builder.baseUrl(baseUrl).build();
    }

    public MovieResponse discoverMovies(String genreId){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/discover/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("with_genre", genreId)
                        .queryParam("sort_by", "popularity.desc")
                        .build()
                ).retrieve()
                .body(MovieResponse.class);
    }
}
