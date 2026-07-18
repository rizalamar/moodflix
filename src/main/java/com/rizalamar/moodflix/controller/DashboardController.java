package com.rizalamar.moodflix.controller;

import com.rizalamar.moodflix.annotation.GeoLocationParam;
import com.rizalamar.moodflix.dto.WebResponse;
import com.rizalamar.moodflix.dto.dashboard.DashboardResponse;
import com.rizalamar.moodflix.dto.geo_location.GeoLocation;
import com.rizalamar.moodflix.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final RecommendationService recommendationService;

    @GetMapping("/dashboard")
    public WebResponse<DashboardResponse> getDashboard(
            @GeoLocationParam GeoLocation geo
            ) {
        DashboardResponse dashboardRecommendations = recommendationService.getDashboardRecommendations(geo.lat(), geo.lon());

        return WebResponse.<DashboardResponse>builder()
                .data(dashboardRecommendations)
                .build();
    }
}
