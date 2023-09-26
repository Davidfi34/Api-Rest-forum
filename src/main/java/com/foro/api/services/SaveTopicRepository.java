package com.foro.api.services;

import com.foro.api.infra.errors.IntegrityValidation;
import com.foro.api.models.course.Course;
import com.foro.api.models.Status;
import com.foro.api.models.topic.DTO.TopicDataResponse;
import com.foro.api.models.topic.DTO.TopicRegistration;
import com.foro.api.models.topic.Topic;
import com.foro.api.models.user.User;
import com.foro.api.repository.CourseRepository;
import com.foro.api.repository.TopicRepository;
import com.foro.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SaveTopicRepository {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TopicRepository topicRepository;


    public TopicDataResponse saveTopic(TopicRegistration topicRegistration){

        if (!userRepository.findById(topicRegistration.id_user()).isPresent() ){
            throw new IntegrityValidation("user not found");
        }

        if (topicRegistration.id_course() != null && !courseRepository.existsById(topicRegistration.id_course()) ){
            throw new IntegrityValidation("course not found");
        }

        User user = userRepository.findById(topicRegistration.id_user()).get();
        Course course = courseRepository.findById(topicRegistration.id_course()).get();

        Topic topic = new Topic(null,topicRegistration.title(),topicRegistration.message(),
                LocalDateTime.now(), Status.NO_RESPONDED,user, course);

        topicRepository.save(topic);

        return new TopicDataResponse(topic);

    }


}
