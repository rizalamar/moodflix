package com.rizalamar.moodflix.dto.geo_location;

import lombok.Builder;

@Builder
public record GeoLocation(
        double lat,
        double lon
) {
}
