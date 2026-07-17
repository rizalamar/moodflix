package com.rizalamar.moodflix.dto.dashboard;

import com.rizalamar.moodflix.dto.MovieResponse;
import com.rizalamar.moodflix.dto.anime.AnimeResponse;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

@Builder
@Jacksonized
public record DashboardResponse(
        String mood,
        MovieResponse movies,
        AnimeResponse anime
) {
}
