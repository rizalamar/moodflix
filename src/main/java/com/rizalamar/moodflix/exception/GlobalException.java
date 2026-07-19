package com.rizalamar.moodflix.exception;

import com.rizalamar.moodflix.dto.WebResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(ResponseStatusException.class)
    public WebResponse<String> handleResponseStatusException(ResponseStatusException exception) {
        return WebResponse.<String>builder()
                .data(null)
                .message(exception.getMessage())
                .code(exception.getStatusCode().value())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public WebResponse<String> handleGeneralException(Exception exception){
        return WebResponse.<String>builder()
                .data(null)
                .message(exception.getMessage())
                .code(500)
                .build();
    }
}
