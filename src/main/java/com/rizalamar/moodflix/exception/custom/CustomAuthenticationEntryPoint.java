package com.rizalamar.moodflix.exception.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rizalamar.moodflix.dto.WebResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        WebResponse<Object> webResponse = WebResponse.builder()
                .data(null)
                .message(authException.getMessage())
                .code(HttpStatus.UNAUTHORIZED.value())
                .build();

        response.getWriter().write(objectMapper.writeValueAsString(webResponse));
    }
}
