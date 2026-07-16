package com.rizalamar.moodflix.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record WeatherResponse(
        double lat,
        double lon,
        List<Data> data
) {
    public record Data(
            double temp,

            @JsonProperty("weather")
            List<Weather> weathers
    ){}

    public record Weather(
            String main,
            String description
    ){}
}
