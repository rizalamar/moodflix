package com.rizalamar.moodflix.dto.anime;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Builder
@Jacksonized
public record AnimeResponse(
        List<Anime> data
) {

    @Builder
    @Jacksonized
    public record Anime(
            @JsonProperty("mal_id")
            Long malId,

            String title,

            Images images,

            String sysnopsis,

            Integer episodes,

            String status,

            double score
    ){}

    @Builder
    @Jacksonized
    public record Images(
            @JsonProperty("image_url")
            String imageUrl
    ){}
}
