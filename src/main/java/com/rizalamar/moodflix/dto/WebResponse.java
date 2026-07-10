package com.rizalamar.moodflix.dto;

import jakarta.persistence.Table;
import lombok.Builder;

@Builder
public record WebResponse<T>(
        T data,
        String message,
        Integer code
) {
    public static <T> WebResponse<T> success(T data){
        return new WebResponse<>(data, "OK", 200);
    }

    public static <T> WebResponse<T> error(String message, Integer code){
        return new WebResponse<>(null, message, code);
    }
}
