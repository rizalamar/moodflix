package com.rizalamar.moodflix.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record MovieResponse(
        List<Movie> results
) {

    @Builder
    @Jacksonized
    public record Movie(
            Long id,
            String title,
            String overview,

            @JsonProperty("poster_path")
            String posterPath,

            @JsonProperty("vote_average")
            double voteAverage
    ){}
}
