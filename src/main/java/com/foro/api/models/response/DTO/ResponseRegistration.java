package com.foro.api.models.response.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseRegistration(

        @NotBlank
        String message,
        @NotNull
        Long id_user_resp,
        @NotNull
        Long id_topic_resp

) {
}
