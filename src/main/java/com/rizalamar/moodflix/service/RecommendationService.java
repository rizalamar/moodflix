package com.rizalamar.moodflix.service;

import com.rizalamar.moodflix.client.JikanClient;
import com.rizalamar.moodflix.client.OpenWeatherClient;
import com.rizalamar.moodflix.client.TmdbClient;
import com.rizalamar.moodflix.dto.weather.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final OpenWeatherClient weatherClient;
    private final TmdbClient tmdbClient;
    private final JikanClient jikanClient;

    public Object getDashboardRecommendations(double lat, double lon) {
        // Get Weather
        WeatherResponse weather = weatherClient.getCurrentWeather(lat, lon);
        String weatherCondition = weather.data().getFirst().weathers().getFirst().main();

        String moodVibe = mapWeatherToMood(weatherCondition);

        return "Vibe: " + moodVibe + " based on " + weatherCondition;
    }

    private String mapWeatherToMood(String weather){
        return switch (weather.toLowerCase()) {
            case "rain", "thunderstorm" -> "COZY";
            case "clear", "sunny" -> "HIGH_ENERGY";
            case "clouds", "mist" -> "DARK";
            default -> "NEUTRAL";
        };
    }
}
