package com.foro.api.models.topic.DTO;

import com.foro.api.models.topic.Topic;

import java.time.LocalDateTime;

public record GetTopics(Long id, String title, String message, LocalDateTime data, Long id_user, Long id_course) {


    public GetTopics(Topic topic){
        this(topic.getId(),topic.getTitle(),topic.getMessage(),
                topic.getData(),topic.getUser().getId(),topic.getCourse().getId());
    }
}
