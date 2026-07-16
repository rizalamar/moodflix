package com.rizalamar.moodflix.client;

import com.rizalamar.moodflix.dto.weather.WeatherResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class OpenWeatherClient {
    private final RestClient.Builder builder;

    @Value("${app.api.openweathermap.key}")
    private String apiKey;

    @Value("${app.api.openweathermap.url}")
    private String baseUrl;

    private RestClient restClient;

    @PostConstruct
    public void init(){
        RestClient restClient = builder.baseUrl(baseUrl).build();
    }

    public WeatherResponse getCurrentWeather(double lat, double lon){
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/data/4.0.onecall/current")
                        .queryParam("lat", lat)
                        .queryParam("lon", lon)
                        .queryParam("appid", apiKey)
                        .queryParam("units", "metric")
                        .build()
                )
                .retrieve()
                .body(WeatherResponse.class);
    }
}
