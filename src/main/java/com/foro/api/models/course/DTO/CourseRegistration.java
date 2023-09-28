package com.foro.api.models.course.DTO;

import jakarta.validation.constraints.NotBlank;

public record CourseRegistration(
        @NotBlank
        String name,
        @NotBlank
        String category
) {
}
