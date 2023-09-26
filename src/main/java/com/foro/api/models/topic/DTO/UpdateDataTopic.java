package com.foro.api.models.topic.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDataTopic(

        @NotNull
        Long id,
        @NotBlank
        String title,
        @NotBlank
        String message,
        @NotNull
        Long id_user,
        @NotNull
        Long id_course
) {


}
