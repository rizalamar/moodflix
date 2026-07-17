package com.rizalamar.moodflix.dto.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record WeatherResponse(
        double lat,
        double lon,
        List<Data> data
) {
    
    @Builder
    @Jacksonized
    public record Data(
            double temp,

            @JsonProperty("weather")
            List<Weather> weathers
    ){}

    @Builder
    @Jacksonized
    public record Weather(
            String main,
            String description
    ){}
}
