package com.foro.api.models.response.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDataResponse(

        @NotNull
        Long id,
        @NotBlank
        String message,
        @NotNull
        Long id_user_resp,
        @NotNull
        Long id_course_resp
) {
}
