package com.rizalamar.moodflix.service;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MoodMapper {

    public Map<String, String> getGenresForMood(String mood){
        return switch (mood) {
            case "COZY" -> Map.of("tmdb", "10749", "jikan", "4");
            case "HIGH_ENERGY" -> Map.of("tmdb", "28", "jikan", "1");
            case "DARK" -> Map.of("tmdb", "53", "jikan", "8");
            default -> Map.of("tmdb", "16", "jikan", "16");
        };
    }
}
