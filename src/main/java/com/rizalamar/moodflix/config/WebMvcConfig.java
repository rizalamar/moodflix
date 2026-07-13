package com.rizalamar.moodflix.config;

import com.rizalamar.moodflix.resolver.CurrentUserArgumentResolver;
import com.rizalamar.moodflix.resolver.GeoLocationArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final GeoLocationArgumentResolver geoLocationArgumentResolver;
    private final CurrentUserArgumentResolver currentUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(geoLocationArgumentResolver);
        resolvers.add(currentUserArgumentResolver);
    }
}
