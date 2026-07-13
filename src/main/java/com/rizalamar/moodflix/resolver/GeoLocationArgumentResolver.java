package com.rizalamar.moodflix.resolver;

import com.rizalamar.moodflix.annotation.GeoLocationParam;
import com.rizalamar.moodflix.dto.geo_location.GeoLocation;
import jakarta.servlet.http.HttpServletRequest;
import org.jspecify.annotations.Nullable;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class GeoLocationArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(GeoLocation.class) && parameter.hasParameterAnnotation(GeoLocationParam.class);
    }

    @Override
    public @Nullable Object resolveArgument(
            MethodParameter parameter,
            @Nullable ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            @Nullable WebDataBinderFactory binderFactory
    ) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");

        if(lat == null || lon == null){
            throw new IllegalArgumentException("Latitude and Longitude are required");
        }

        return GeoLocation.builder()
                .lat(Double.parseDouble(lat))
                .lon(Double.parseDouble(lon))
                .build();
    }
}
