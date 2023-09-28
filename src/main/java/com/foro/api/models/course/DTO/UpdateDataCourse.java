package com.foro.api.models.course.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateDataCourse(
        @NotNull
        Long id,
        @NotBlank
        String name,
        @NotBlank
        String category,
        Boolean active

) {
}
