package com.rizalamar.moodflix.service;

import com.rizalamar.moodflix.client.JikanClient;
import com.rizalamar.moodflix.client.OpenWeatherClient;
import com.rizalamar.moodflix.client.TmdbClient;
import com.rizalamar.moodflix.dto.MovieResponse;
import com.rizalamar.moodflix.dto.anime.AnimeResponse;
import com.rizalamar.moodflix.dto.dashboard.DashboardResponse;
import com.rizalamar.moodflix.dto.weather.WeatherResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final OpenWeatherClient weatherClient;
    private final TmdbClient tmdbClient;
    private final JikanClient jikanClient;
    private final MoodMapper moodMapper;
    private final ValidationService validationService;

    public DashboardResponse getDashboardRecommendations(double lat, double lon) {
        validationService.validateGeolocation(lat, lon);

        // Get Weather
        WeatherResponse weather = weatherClient.getCurrentWeather(lat, lon);
        String weatherCondition = weather.data().getFirst().weathers().getFirst().main();

        String moodVibe = mapWeatherToMood(weatherCondition);
        Map<String, String> genresForMood = moodMapper.getGenresForMood(moodVibe);

        String tmdbGenreId = genresForMood.get("tmdb");
        String jikanGenreId = genresForMood.get("jikan");

        MovieResponse movieResponse = tmdbClient.discoverMovies(tmdbGenreId);
        AnimeResponse animeResponse = jikanClient.searchAnimeByGenre(jikanGenreId);

        return DashboardResponse.builder()
                .mood(moodVibe)
                .movies(movieResponse)
                .anime(animeResponse)
                .build();
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
