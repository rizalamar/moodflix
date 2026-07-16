package com.rizalamar.moodflix.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Table;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record WebResponse<T>(
        T data,
        String message,
        Integer code
) {
}
