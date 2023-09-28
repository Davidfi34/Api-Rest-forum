package com.foro.api.models.course;

import com.foro.api.models.course.DTO.CourseDataResponse;
import com.foro.api.models.course.DTO.CourseRegistration;
import com.foro.api.models.course.DTO.UpdateDataCourse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Boolean active;


    public void updateCourse(UpdateDataCourse request) {

      if (request.name() != null)  this.name = request.name();
      if (request.category() != null)  this.category = request.category();
      if (request.active() != null) this.active = request.active();
    }

    public void deactivateCourse() {
        this.active = false;
    }
}
