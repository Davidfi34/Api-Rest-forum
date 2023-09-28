package com.foro.api.controllers;

import com.foro.api.models.course.Course;
import com.foro.api.models.course.DTO.CourseDataResponse;
import com.foro.api.models.course.DTO.CourseRegistration;
import com.foro.api.models.course.DTO.UpdateDataCourse;
import com.foro.api.models.response.DTO.DataResponse;
import com.foro.api.models.response.DTO.ResponseRegistration;
import com.foro.api.models.response.DTO.UpdateDataResponse;
import com.foro.api.models.response.Response;
import com.foro.api.repository.CourseRepository;
import com.foro.api.repository.ResponseRepository;
import com.foro.api.services.SaveResponseRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/course")
@SecurityRequirement(name = "bearer-key")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;


    @Operation(summary = "course registration method")
    @PostMapping
    @Transactional
    public ResponseEntity<CourseDataResponse> createCourse (@RequestBody @Valid CourseRegistration courseRegistration,
                                                            UriComponentsBuilder uriComponentsBuilder){

        Course course = new Course(null,courseRegistration.name(),courseRegistration.category(),true);
        courseRepository.save(course);
        CourseDataResponse courseDataResponse = new CourseDataResponse(course);
        URI url = uriComponentsBuilder.path("/course/{id}").buildAndExpand(courseDataResponse.id()).toUri();
        return ResponseEntity.created(url).body(courseDataResponse);
    }

    @Operation(summary = "method to obtain all the course")
    @GetMapping
    public ResponseEntity<Page<CourseDataResponse>> getAllCourse(@PageableDefault(size = 10) Pageable pages){
        return ResponseEntity.ok(courseRepository.findByActiveTrue(pages).map(CourseDataResponse::new));
    }

    @Operation(summary = "method get by ID course")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseDataResponse> getByIdCourse(@PathVariable Long id){
        Course course = courseRepository.getReferenceById(id);
        CourseDataResponse courseDataResponse = new CourseDataResponse(course);
        return ResponseEntity.ok(courseDataResponse);
    }

    @Operation(summary = "course update method")
    @PutMapping
    @Transactional
    public ResponseEntity<CourseDataResponse> updateResponse(@RequestBody @Valid UpdateDataCourse updateDataCourse) {
        Course course = courseRepository.getReferenceById(updateDataCourse.id());
        course.updateCourse(updateDataCourse);
        CourseDataResponse coursedataResponse = new CourseDataResponse(course);
        return ResponseEntity.ok(coursedataResponse);
    }

    @Operation(summary = "method of logical deletion of course")
    @DeleteMapping(path ="/{id}")
    @Transactional
    public ResponseEntity deleteCourse(@PathVariable Long id) {
        Course course = courseRepository.getReferenceById(id);
        course.deactivateCourse();
        return ResponseEntity.noContent().build();
    }


  /*


    @Operation(summary = "method of logical deletion of response")
    @DeleteMapping(path ="/{id}")
    @Transactional
    public ResponseEntity deleteResponse(@PathVariable Long id) {
        Response response = responseRepository.getReferenceById(id);
        response.deactivateResponse();
        return ResponseEntity.noContent().build();
    }*/

}

