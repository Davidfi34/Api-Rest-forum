package com.foro.api.repository;

import com.foro.api.models.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByActiveTrue(Pageable pages);//ONLY ACTIVE : TR
}
