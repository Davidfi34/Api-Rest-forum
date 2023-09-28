package com.foro.api.models.course.DTO;

import com.foro.api.models.course.Course;

public record CourseDataResponse(Long id, String name, String category) {

    public CourseDataResponse(Course course){
        this(course.getId(),course.getName(),course.getCategory());
    }
}
