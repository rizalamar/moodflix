package com.rizalamar.moodflix.dto.weather;

import lombok.Builder;

import java.util.List;

@Builder
public record WeatherResponse(
        List<Weather> weathers,
        Main main
) {
    public record Weather(String main, String description){}
    public record Main(double temp){}
}
